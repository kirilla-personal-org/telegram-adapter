package ru.afanasyev.telegram.adapter.redis;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.afanasyev.telegram.app.api.SubscriberRepository;
import ru.afanasyev.telegram.domain.Subscriber;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SubscriberRepositoryImpl implements SubscriberRepository {
    private final SubscriberCrudRepository repository;

    @Override
    public void save(Subscriber subscriber) {
        repository.save(subscriber);
    }

    @Override
    public Optional<Subscriber> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Subscriber> findAll() {
        return Lists.newArrayList(repository.findAll());
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<Subscriber> findAllActive() {
        // TODO optimize
        return Lists.newArrayList(repository.findAll()).stream()
            .filter(Subscriber::getIsActive)
            .collect(Collectors.toList());
    }
}
