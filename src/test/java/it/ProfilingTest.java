package it;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.afanasyev.telegram.app.api.MessageContext;
import ru.afanasyev.telegram.app.impl.command.StartCommand;
import ru.afanasyev.telegram.app.impl.profiling.ProfileService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static ru.afanasyev.telegram.domain.Language.RUSSIAN;

class ProfilingTest extends IntegrationTest {
    @SpyBean
    private ProfileService profileService;
    @Autowired
    private StartCommand startCommand;

    @Test
    void profileCommandHandler() throws TelegramApiException {
        MessageContext context = new MessageContext();
        context.setChatId("chatId");
        context.setLanguage(RUSSIAN);

        startCommand.handle(context, new AbsSenderMock());

        verify(profileService, times(1)).profile(any());
    }
}