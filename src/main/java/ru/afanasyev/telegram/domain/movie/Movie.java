package ru.afanasyev.telegram.domain.movie;

import lombok.Data;

import java.util.List;

@Data
public class Movie {
    private String name;
    private Poster poster;
    private List<Genre> genres;
    private Integer year;
    private Rating rating;
    private Integer movieLength;
}
