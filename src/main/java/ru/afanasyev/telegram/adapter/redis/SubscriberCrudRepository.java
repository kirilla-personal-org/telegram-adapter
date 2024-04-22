package ru.afanasyev.telegram.adapter.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.afanasyev.telegram.domain.Subscriber;

@Repository
public interface SubscriberCrudRepository extends CrudRepository<Subscriber, String> {}