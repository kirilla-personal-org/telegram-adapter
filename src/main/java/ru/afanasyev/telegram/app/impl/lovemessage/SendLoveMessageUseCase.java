package ru.afanasyev.telegram.app.impl.lovemessage;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.afanasyev.telegram.app.api.BotUpdateException;
import ru.afanasyev.telegram.app.api.SendMessageOutbound;
import ru.afanasyev.telegram.domain.Language;
import ru.afanasyev.telegram.domain.movie.Movie;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class SendLoveMessageUseCase {
    private final SendMessageOutbound sendMessageOutbound;
    private final LoveMessageProperties properties;

    @Scheduled(cron = "")
    public void execute() throws TelegramApiException {
        if (LocalDate.now().equals(properties.getLovelyDate())) {
            SendPhoto sendPhoto = getSendPhoto();
            sendMessageOutbound.execute(sendPhoto);
        }
    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    private SendPhoto getSendPhoto() {
        try {
            InputStream input = new URL(properties.getPhotoUrl()).openStream();
            InputFile inputFile = new InputFile(input, "file");
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setChatId(properties.getReceiverId());
            sendPhoto.setPhoto(inputFile);
            sendPhoto.setCaption(properties.getLovelyText());
            return sendPhoto;
        } catch (IOException e) {
            throw new BotUpdateException(e);
        }
    }
}
