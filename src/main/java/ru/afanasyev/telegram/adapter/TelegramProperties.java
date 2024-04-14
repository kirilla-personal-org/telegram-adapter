package ru.afanasyev.telegram.adapter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "telegram")
@Getter
@Setter
public class TelegramProperties {
    private String token;
    private String botUsername;
}
