package com.example.bottranslator;

import ThirdPatry.APIService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class TelegramBotConfig {


    @Bean
    public TranslationBot myTelegramBot() throws TelegramApiException {
        TranslationBot bot = new TranslationBot();
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(bot);
        return bot;
    }

    @Bean
    public APIService apiService() {
        return new APIService();
    }

}
