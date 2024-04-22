package ru.afanasyev.telegram.adapter;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import ru.afanasyev.telegram.adapter.moviematch.MovieMathAdapter;
import ru.afanasyev.telegram.app.api.BotUpdateException;
import ru.afanasyev.telegram.app.api.SubscriberService;
import ru.afanasyev.telegram.app.impl.lang.LanguageService;
import ru.afanasyev.telegram.domain.Language;
import ru.afanasyev.telegram.domain.Subscriber;
import ru.afanasyev.telegram.domain.movie.Movie;
import ru.afanasyev.telegram.domain.movie.Rating;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import static ru.afanasyev.telegram.domain.Message.GET_RANDOM_MOVIE_RESPONSE;

@Component
@RequiredArgsConstructor
@Slf4j
public class SubscriberMessageScheduler {
    private final MovieMatchBot bot;
    private final SubscriberService subscriberService;
    private final LanguageService languageService;
    private final MovieMathAdapter movieMathAdapter;

    @SneakyThrows
    @Scheduled(fixedDelayString = "${telegram-adapter.settings.movie-sending-delay}")
    public void sendRandomMovie() {
        Movie movie = movieMathAdapter.getRandom();
        List<Subscriber> subscribers = subscriberService.findAll();
        for (Subscriber subscriber : subscribers) {
            SendPhoto sendPhoto = getSendPhoto(movie, subscriber.getChatId(), Language.RUSSIAN);
            bot.execute(sendPhoto);
        }
    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    private SendPhoto getSendPhoto(Movie movie, String chatId, Language language) {
        try {
            InputStream input = new URL(movie.getPoster().getPreviewUrl()).openStream();
            InputFile inputFile = new InputFile(input, movie.getName());
            Rating rating = movie.getRating();
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(chatId);
            sendPhoto.setPhoto(inputFile);
            sendPhoto.setCaption(String.format(languageService.accept(GET_RANDOM_MOVIE_RESPONSE, language),
                movie.getName(), rating.getKp(), rating.getImdb()));
            return sendPhoto;
        } catch (IOException e) {
            throw new BotUpdateException(e);
        }
    }
}
