package it;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import ru.afanasyev.telegram.adapter.MovieMatchBot;
import ru.afanasyev.telegram.adapter.SubscriberMessageScheduler;
import ru.afanasyev.telegram.fw.TelegramAdapterApplication;

@SpringBootTest(classes = {TelegramAdapterApplication.class})
@ActiveProfiles({"test"})
public abstract class IntegrationTest {
    @MockBean
    protected MovieMatchBot bot;
    @MockBean
    protected SubscriberMessageScheduler scheduler;
    @MockBean
    protected TelegramBotsApi api;
}
