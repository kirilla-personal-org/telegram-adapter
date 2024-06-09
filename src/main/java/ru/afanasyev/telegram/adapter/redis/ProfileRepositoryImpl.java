package ru.afanasyev.telegram.adapter.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.afanasyev.telegram.app.api.ProfileRepository;
import ru.afanasyev.telegram.domain.profile.ClassProfile;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProfileRepositoryImpl implements ProfileRepository {
    private final ProfileCrudRepository repository;

    @Override
    public void save(ClassProfile classProfile) {
        repository.save(classProfile);
    }

    @Override
    public Optional<ClassProfile> findByClassName(String className) {
        return repository.findById(className);
    }

    @Override
    public Iterable<ClassProfile> findAll() {
        return repository.findAll();
    }
}
