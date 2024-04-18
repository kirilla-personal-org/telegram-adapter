package ru.afanasyev.telegram.app.api;

import lombok.Data;
import ru.afanasyev.telegram.domain.Language;

@Data
public class MessageContext {
    private Language language;
    private String input;
    private String chatId;
}
