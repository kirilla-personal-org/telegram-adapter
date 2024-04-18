package ru.afanasyev.telegram.adapter.moviematch;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.afanasyev.telegram.adapter.moviematch.dto.MovieDomainMapper;
import ru.afanasyev.telegram.adapter.moviematch.dto.MovieDto;
import ru.afanasyev.telegram.app.api.GetMovieOutbound;
import ru.afanasyev.telegram.domain.movie.Movie;

@Component
@RequiredArgsConstructor
public class MovieMathAdapter implements GetMovieOutbound {
    private final MovieMatchFeignClient feignClient;
    private final MovieDomainMapper movieDomainMapper;

    @Override
    public Movie getRandom() {
        MovieDto movie = feignClient.getRandomMovie();
        return movieDomainMapper.mapToDomain(movie);
    }
}
