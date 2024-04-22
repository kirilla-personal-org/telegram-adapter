package ru.afanasyev.telegram.app.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.afanasyev.telegram.app.api.SubscriberRepository;
import ru.afanasyev.telegram.app.api.SubscriberService;
import ru.afanasyev.telegram.domain.Subscriber;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class SubscriberServiceImpl implements SubscriberService {
    private final SubscriberRepository repository;

    public void logSubscribers() {
        List<Subscriber> subscribers = findAll();
        subscribers.forEach(System.out::println);
    }

    @Async
    @Override
    @Transactional
    public void save(Subscriber subscriber) {
        log.info("New subscriber: {}", subscriber);
        repository.save(subscriber);
        logSubscribers();
    }

    @Override
    public List<Subscriber> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(String id) {
        log.info("Deleting subscriber by id: {}", id);
        repository.deleteById(id);
        logSubscribers();
    }
}
