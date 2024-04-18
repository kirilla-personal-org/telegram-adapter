package ru.afanasyev.telegram.adapter.moviematch.dto;

import lombok.Data;

import java.util.List;

@Data
public class MovieDto {
    private String name;
    private PosterDto poster;
    private List<GenreDto> genres;
    private Integer year;
    private RatingDto rating;
    private Integer movieLength;
}
