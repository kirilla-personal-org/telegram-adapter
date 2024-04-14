package ru.afanasyev.telegram.adapter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.afanasyev.telegram.app.api.CommandHandler;
import ru.afanasyev.telegram.app.api.MessageContext;
import ru.afanasyev.telegram.domain.Language;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@Component
@Slf4j
public class MovieMatchBot extends TelegramLongPollingBot {
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
            String chatId = update.getMessage().getChatId().toString();
            SendMessage message = handleCommand(update);
            message.setChatId(chatId);
            execute(message);
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

    private SendMessage handleCommand(Update update) {
        MessageContext context = getContext(update);
        CommandHandler commandHandler = commands.get(context.getInput());
        if (commandHandler != null) {
            return commandHandler.handle(context);
        }
        throw new IllegalArgumentException("Cannot resolve input command: " + context.getInput());
    }

    private MessageContext getContext(Update update) {
        MessageContext messageContext = new MessageContext();
        messageContext.setInput(getInput(update));
        messageContext.setLanguage(Language.getByCode(update.getMessage().getFrom().getLanguageCode()));
        return messageContext;
    }

    private String getInput(Update update) {
        String messageText = update.getMessage().getText();
        return messageText.split(" ")[0];
    }
}
