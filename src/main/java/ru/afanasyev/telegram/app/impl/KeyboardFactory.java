package ru.afanasyev.telegram.app.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.afanasyev.telegram.domain.Command;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class KeyboardFactory {
    public ReplyKeyboardMarkup getInitialKeyboard() {
        KeyboardRow row = new KeyboardRow();
        List<String> commands = Arrays.stream(Command.values())
            .map(Command::getValue).toList();
        row.addAll(commands);
        return new ReplyKeyboardMarkup(List.of(row));
    }
}