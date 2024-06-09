package ru.afanasyev.telegram.domain.profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@RedisHash("ClassProfile")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClassProfile implements Serializable {
    @Serial
    private static final long serialVersionUID = 2240693400055036040L;

    @Id
    private String className;
    private Set<MethodProfile> methodProfiles;
}
