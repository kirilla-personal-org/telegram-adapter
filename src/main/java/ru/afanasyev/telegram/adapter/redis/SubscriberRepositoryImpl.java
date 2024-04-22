package ru.afanasyev.telegram.adapter.redis;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.afanasyev.telegram.app.api.SubscriberRepository;
import ru.afanasyev.telegram.domain.Subscriber;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SubscriberRepositoryImpl implements SubscriberRepository {
    private final SubscriberCrudRepository repository;

    @Override
    public void save(Subscriber subscriber) {
        repository.save(subscriber);
    }

    @Override
    public Subscriber findById(Long id) {
        return null;
    }

    @Override
    public List<Subscriber> findAll() {
        return Lists.newArrayList(repository.findAll());
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
