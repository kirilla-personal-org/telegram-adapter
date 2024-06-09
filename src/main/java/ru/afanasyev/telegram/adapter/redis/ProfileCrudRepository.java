package ru.afanasyev.telegram.adapter.redis;

import org.springframework.data.repository.CrudRepository;
import ru.afanasyev.telegram.domain.profile.ClassProfile;

public interface ProfileCrudRepository extends CrudRepository<ClassProfile, String> {
}
