package ru.afanasyev.telegram.app.api;

import ru.afanasyev.telegram.domain.Subscriber;

import java.util.List;

public interface SubscriberRepository {
    void save(Subscriber subscriber);

    Subscriber findById(Long id);

    List<Subscriber> findAll();

    void deleteById(String id);
}
