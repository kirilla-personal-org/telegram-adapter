package ru.afanasyev.telegram.app.api;

import ru.afanasyev.telegram.domain.Language;
import ru.afanasyev.telegram.domain.Message;

public interface LanguageAdapter {
    String accept(Message message);

    boolean support(Language language);
}
