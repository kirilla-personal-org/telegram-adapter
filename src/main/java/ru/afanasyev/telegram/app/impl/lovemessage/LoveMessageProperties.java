package ru.afanasyev.telegram.app.impl.lovemessage;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.LocalDate;

@Getter
@Setter
@ConfigurationProperties(prefix = "love")
public class LoveMessageProperties {
    private String receiverId;
    private LocalDate lovelyDate;
    private String lovelyText;
    private String photoUrl;
}
