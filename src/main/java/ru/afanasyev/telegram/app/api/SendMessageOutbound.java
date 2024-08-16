package ru.afanasyev.telegram.app.api;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface SendMessageOutbound {
    Message execute(SendPhoto sendPhoto) throws TelegramApiException;
}
