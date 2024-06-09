package ru.afanasyev.telegram.app.api;

import ru.afanasyev.telegram.domain.profile.ClassProfile;

import java.util.Optional;

public interface ProfileRepository {
    void save(ClassProfile classProfile);

    Optional<ClassProfile> findByClassName(String className);

    Iterable<ClassProfile> findAll();
}
