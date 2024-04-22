package ru.afanasyev.telegram.app.impl.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.afanasyev.telegram.app.api.CommandHandler;
import ru.afanasyev.telegram.app.api.MessageContext;
import ru.afanasyev.telegram.app.api.SubscriberService;
import ru.afanasyev.telegram.app.impl.lang.LanguageService;
import ru.afanasyev.telegram.domain.Command;
import ru.afanasyev.telegram.domain.Message;
import ru.afanasyev.telegram.domain.Subscriber;

import static ru.afanasyev.telegram.domain.Message.*;

@Component
@RequiredArgsConstructor
public class UnsubscribeCommand implements CommandHandler {
    private final LanguageService languageService;
    private final SubscriberService subscriberService;

    @Override
    public void handle(MessageContext context, AbsSender sender) throws TelegramApiException {
        SendMessage sendMessage = getDefaultMessage(context, languageService, UNSUBSCRIBE);
        sender.execute(sendMessage);
        subscriberService.delete(context.getChatId());
    }

    @Override
    public String supports() {
        return Command.UNSUBSCRIBE.getValue();
    }
}
