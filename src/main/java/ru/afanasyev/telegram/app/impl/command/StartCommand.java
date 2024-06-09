package ru.afanasyev.telegram.app.impl.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.afanasyev.telegram.app.api.CommandHandler;
import ru.afanasyev.telegram.app.api.MessageContext;
import ru.afanasyev.telegram.app.impl.KeyboardFactory;
import ru.afanasyev.telegram.app.impl.lang.LanguageService;
import ru.afanasyev.telegram.domain.Command;

import static ru.afanasyev.telegram.domain.Message.START;

@Component
@RequiredArgsConstructor
public class StartCommand implements CommandHandler {
    private final LanguageService languageService;
    private final KeyboardFactory keyboardFactory;

    @Override
    public void handle(MessageContext context, AbsSender sender) throws TelegramApiException {
        SendMessage sendMessage = getDefaultMessage(context, languageService, START);
        sendMessage.setReplyMarkup(keyboardFactory.getInitialKeyboard());
        sender.execute(sendMessage);
    }

    @Override
    public String supports() {
        return Command.START.getValue();
    }
}
