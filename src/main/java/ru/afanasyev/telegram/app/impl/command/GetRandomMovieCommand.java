package ru.afanasyev.telegram.app.impl.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.afanasyev.telegram.app.api.MessageContext;
import ru.afanasyev.telegram.app.api.CommandHandler;

@Component
public class GetRandomMovieCommand implements CommandHandler {
    @Override
    public SendMessage handle(MessageContext context) {
        return null;
    }

    @Override
    public String supports() {
        return "/get-movie";
    }
}
