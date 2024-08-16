package ru.afanasyev.telegram.app.api;

import ru.afanasyev.telegram.domain.Subscriber;

import java.util.List;
import java.util.Optional;

public interface SubscriberRepository {
    void save(Subscriber subscriber);

    Optional<Subscriber> findById(String id);

    List<Subscriber> findAll();

    void deleteById(String id);

    List<Subscriber> findAllActive();
}
