package ru.afanasyev.telegram.domain.movie;

import lombok.Data;

@Data
public class Rating {
    private String kp;
    private String imdb;
    private String filmCritics;
    private String russianFilmCritics;
}
