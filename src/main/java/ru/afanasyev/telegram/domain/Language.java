package ru.afanasyev.telegram.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Language {
    RUSSIAN("ru");

    private final String code;

    public static Language getByCode(String code) {
        return Arrays.stream(Language.values())
            .filter(language -> language.getCode().equals(code))
            .findFirst().orElse(RUSSIAN);
    }
}
