package ru.afanasyev.telegram.app.impl.lang;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.afanasyev.telegram.app.api.LanguageAdapter;
import ru.afanasyev.telegram.domain.Language;
import ru.afanasyev.telegram.domain.Message;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LanguageService {
    private final List<LanguageAdapter> languageAdapters;

    public String accept(Message message, Language language) {
        return getSupportedAdapter(language).accept(message);
    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    private LanguageAdapter getSupportedAdapter(Language language) {
        return languageAdapters.stream()
            .filter(languageAdapter -> languageAdapter.support(language))
            .findFirst().orElseThrow();
    }
}
