package ru.afanasyev.telegram.app.api;

public class BotUpdateException extends RuntimeException {
    public BotUpdateException(Exception e) {
        super(e);
    }
}
