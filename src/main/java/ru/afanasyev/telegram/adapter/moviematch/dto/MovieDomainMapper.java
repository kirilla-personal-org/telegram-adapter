package ru.afanasyev.telegram.adapter.moviematch.dto;

import org.mapstruct.Mapper;
import ru.afanasyev.telegram.domain.movie.Movie;

@Mapper
public interface MovieDomainMapper {
    Movie mapToDomain(MovieDto source);
}
