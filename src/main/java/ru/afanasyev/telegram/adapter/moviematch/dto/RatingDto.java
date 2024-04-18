package ru.afanasyev.telegram.adapter.moviematch.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Рейтинг")
public class RatingDto {
    @Schema(name = "Рейтинг кинопоиска")
    private String kp;
    @Schema(name = "Рейтинг imdb")
    private String imdb;
    private String filmCritics;
    private String russianFilmCritics;
}
