package ru.afanasyev.telegram.app.api;

import ru.afanasyev.telegram.domain.Subscriber;

import java.util.List;

public interface SubscriberService {
    void handleSubscribe(Subscriber subscriber);

    List<Subscriber> findAll();

    void handleUnsubscribe(String id);

    List<Subscriber> findAllActive();
}
