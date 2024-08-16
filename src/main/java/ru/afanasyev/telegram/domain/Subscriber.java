package ru.afanasyev.telegram.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serial;
import java.io.Serializable;

@RedisHash("Subscriber")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Subscriber implements Serializable {
    @Serial
    private static final long serialVersionUID = 6557880287057434306L;
    @Id
    private String chatId;
    private String username;
    private Boolean isActive;

    public Subscriber(String chatId, String username) {
        this.chatId = chatId;
        this.username = username;
    }
}
