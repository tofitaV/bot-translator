package com.example.bottranslator;

import Models.ChatGPTChatResponse;
import Models.MessageToChatGPT;
import ThirdPatry.APIService;
import Utils.MessageBuilder;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Slf4j
@Component
public class TranslationBot extends TelegramLongPollingBot {

    @Value("${telegram.bot.token}")
    private String botToken = "7366290282:AAGstk3jqjnNU4V1MPSnYhfPYliQZK1urc0";

    @Value("${telegram.bot.username}")
    private String botUsername = "your_pocket_translator_bot";

    private String translationLanguage = "Ukrainian";

    @Autowired
    private APIService apiService;
    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasChannelPost() && update.getChannelPost().hasText()) {
            String messageText = update.getChannelPost().getText();
            int messageId = update.getChannelPost().getMessageId();
            String chatId = String.valueOf(update.getChannelPost().getChat().getId());

            if (messageText.startsWith("/")) {
                changeLanguage(chatId, messageText, messageId);
            } else {
                handleTranslation(chatId, messageId, messageText);
            }
        }
    }

    private void changeLanguage(String chatId, String messageText, int messageId) {
        List<String> listOfLanguages = List.of("English", "French", "Ukrainian", "eng", "fr", "ua");
        translationLanguage = listOfLanguages.stream()
                .filter(language -> messageText.toLowerCase().contains(language.toLowerCase()))
                .findFirst().get();

        log.info("Language are changed to: " + translationLanguage);

        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(chatId);
        deleteMessage.setMessageId(messageId);

    }

    private void handleTranslation(String chatId, int messageId, String messageText) {
        String responseMessage = translate(messageText, translationLanguage);
        editMessage(chatId, messageId, responseMessage);
    }

    private String translate(String text, String targetLanguage) {
        MessageToChatGPT message = MessageBuilder.createMessageBody("gpt-3.5-turbo-0125", targetLanguage, text);
        Response response = apiService.post("chat/completions", message);

        if (response.getStatusCode() == 200) {
            ChatGPTChatResponse chatGPTChatResponse = response.as(ChatGPTChatResponse.class);
            return chatGPTChatResponse.getChoices().getFirst().getMessage().getContent().trim();
        } else {
            log.error("Error response from OpenAI API: {}", response.getBody().asString());
            return "Translation error";
        }
    }

    private void editMessage(String chatId, int messageId, String text) {
        EditMessageText message = new EditMessageText();
        message.setChatId(chatId);
        message.setMessageId(messageId);
        message.setText(text);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Error sending message", e);
        }
    }
}
