package ru.afanasyev.telegram.domain.profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serial;
import java.io.Serializable;

@RedisHash("MethodProfile")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MethodProfile implements Serializable {
    @Serial
    private static final long serialVersionUID = -7686193252807320207L;

    @Id
    private String methodName;
    private Long calls;
}
