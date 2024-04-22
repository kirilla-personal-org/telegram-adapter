package ru.afanasyev.telegram.fw.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {
    "ru.afanasyev.telegram.adapter.*"
})
public class FeignConfig {
}
