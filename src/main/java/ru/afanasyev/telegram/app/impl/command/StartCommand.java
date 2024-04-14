package ru.afanasyev.telegram.app.impl.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.afanasyev.telegram.app.api.MessageContext;
import ru.afanasyev.telegram.app.impl.lang.LanguageService;
import ru.afanasyev.telegram.app.api.CommandHandler;
import ru.afanasyev.telegram.domain.Message;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StartCommand implements CommandHandler {
    private final LanguageService languageService;

    @Override
    public SendMessage handle(MessageContext context) {
        SendMessage message = new SendMessage();
        message.setText(languageService.accept(Message.START, context));
        message.setReplyMarkup(getInitialKeyboard());
        return message;
    }

    @Override
    public String supports() {
        return "/start";
    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    private ReplyKeyboardMarkup getInitialKeyboard() {
        KeyboardRow row = new KeyboardRow();
        row.add("Pizza");
        row.add("Drink");
        return new ReplyKeyboardMarkup(List.of(row));
    }
}
