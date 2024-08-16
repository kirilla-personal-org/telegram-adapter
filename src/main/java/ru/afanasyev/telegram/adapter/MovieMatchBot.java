package ru.afanasyev.telegram.adapter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.afanasyev.telegram.app.api.CommandHandler;
import ru.afanasyev.telegram.app.api.MessageContext;
import ru.afanasyev.telegram.app.api.SendMessageOutbound;
import ru.afanasyev.telegram.domain.Language;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@Component
@Slf4j
public class MovieMatchBot extends TelegramLongPollingBot implements SendMessageOutbound {
    private final TelegramProperties telegramProperties;
    private final Map<String, CommandHandler> commands;

    public MovieMatchBot(TelegramProperties telegramProperties, List<CommandHandler> commandHandlers) {
        super(telegramProperties.getToken());
        this.telegramProperties = telegramProperties;
        this.commands = commandHandlers.stream().collect(toMap(CommandHandler::supports, Function.identity()));
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            handleCommand(update);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return telegramProperties.getBotUsername();
    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    private void handleCommand(Update update) throws TelegramApiException {
        MessageContext context = getContext(update);
        CommandHandler commandHandler = commands.get(context.getInput());
        if (commandHandler != null) {
            commandHandler.handle(context, this);
            return;
        }
        throw new IllegalArgumentException("Cannot resolve input command: " + context.getInput());
    }

    private MessageContext getContext(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        MessageContext messageContext = new MessageContext();
        messageContext.setInput(getInput(update));
        messageContext.setChatId(chatId);
        messageContext.setLanguage(Language.getByCode(update.getMessage().getFrom().getLanguageCode()));
        messageContext.setUsername(update.getMessage().getFrom().getFirstName());
        return messageContext;
    }

    private String getInput(Update update) {
        String messageText = update.getMessage().getText();
        return messageText.split(" ")[0];
    }
}
