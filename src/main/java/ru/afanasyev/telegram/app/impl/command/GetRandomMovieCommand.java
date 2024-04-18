package ru.afanasyev.telegram.app.impl.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.afanasyev.telegram.app.api.BotUpdateException;
import ru.afanasyev.telegram.app.api.CommandHandler;
import ru.afanasyev.telegram.app.api.GetMovieOutbound;
import ru.afanasyev.telegram.app.api.MessageContext;
import ru.afanasyev.telegram.app.impl.lang.LanguageService;
import ru.afanasyev.telegram.domain.movie.Movie;
import ru.afanasyev.telegram.domain.movie.Rating;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static ru.afanasyev.telegram.domain.Message.GET_RANDOM_MOVIE_RESPONSE;

@Component
@RequiredArgsConstructor
public class GetRandomMovieCommand implements CommandHandler {
    private final GetMovieOutbound getMovieOutbound;
    private final LanguageService languageService;

    @Override
    public void handle(MessageContext context, AbsSender sender) throws TelegramApiException {
        Movie movie = getMovieOutbound.getRandom();
        SendPhoto sendPhoto = getSendPhoto(movie, context);
        sender.execute(sendPhoto);
    }

    @Override
    public String supports() {
        return "/get-movie";
    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    private SendPhoto getSendPhoto(Movie movie, MessageContext context) {
        try {
            InputStream input = new URL(movie.getPoster().getPreviewUrl()).openStream();
            Rating rating = movie.getRating();
            InputFile inputFile = new InputFile(input, movie.getName());
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(context.getChatId());
            sendPhoto.setPhoto(inputFile);
            sendPhoto.setCaption(String.format(languageService.accept(GET_RANDOM_MOVIE_RESPONSE, context),
                movie.getName(), rating.getKp(), rating.getImdb()));
            return sendPhoto;
        } catch (IOException e) {
            throw new BotUpdateException(e);
        }
    }
}
