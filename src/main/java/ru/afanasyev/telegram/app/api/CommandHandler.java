package ru.afanasyev.telegram.app.api;

import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface CommandHandler {
    void handle(MessageContext context, AbsSender sender) throws TelegramApiException;

    String supports();
}
