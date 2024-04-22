package ru.afanasyev.telegram.app.api;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.afanasyev.telegram.app.impl.lang.LanguageService;
import ru.afanasyev.telegram.domain.Message;

public interface CommandHandler {
    void handle(MessageContext context, AbsSender sender) throws TelegramApiException;

    String supports();

    default SendMessage getDefaultMessage(MessageContext context, LanguageService languageService, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(context.getChatId());
        sendMessage.setText(languageService.accept(message, context.getLanguage()));
        return sendMessage;
    }
}
