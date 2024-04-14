package ru.afanasyev.telegram.app.api;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface CommandHandler {
    SendMessage handle(MessageContext context);

    String supports();
}
