package ru.afanasyev.telegram.fw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "ru.afanasyev.telegram")
public class TelegramAdapterApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TelegramAdapterApplication.class, args);
    }
}