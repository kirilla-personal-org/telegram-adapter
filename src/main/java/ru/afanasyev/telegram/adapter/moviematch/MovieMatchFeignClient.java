package ru.afanasyev.telegram.adapter.moviematch;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ru.afanasyev.telegram.adapter.moviematch.dto.MovieDto;

@FeignClient(name = "movie-match", url = "${service.movie-match.api.url}")
public interface MovieMatchFeignClient {
    @GetMapping("/api/v1/external/movie/random")
    MovieDto getRandomMovie();
}