package ru.afanasyev.telegram.app.api;

import org.springframework.scheduling.annotation.Async;
import ru.afanasyev.telegram.domain.Subscriber;

import java.util.List;

public interface SubscriberService {
    @Async
    void save(Subscriber subscriber);

    List<Subscriber> findAll();

    void delete(String id);
}
