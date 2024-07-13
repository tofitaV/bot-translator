package Utils;

import Models.Message;
import Models.MessageToChatGPT;

import java.util.List;

public class MessageBuilder {

    public static MessageToChatGPT createMessageBody(String model, String targetLanguage, String text) {
        return MessageToChatGPT.builder()
                .model(model)
                .messages(List.of(Message.builder()
                        .content("Translate the following word to " + targetLanguage + " with transcription and add few synonyms: " + text + ". " +
                                "Your output format should be stricter like this: 'Hello /heˈloʊ/ - привіт'")
                        .build()))
                .build();
    }
}
