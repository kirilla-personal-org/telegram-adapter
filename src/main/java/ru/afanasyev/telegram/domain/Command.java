package ru.afanasyev.telegram.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Command {
    START("/start"),
    RANDOM_MOVIE("/randommovie"),
    SUBSCRIBE("/subscribe"),
    UNSUBSCRIBE("/unsubscribe");

    private final String value;
}
