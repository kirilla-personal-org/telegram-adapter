package ru.afanasyev.telegram.app.impl.lang;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.afanasyev.telegram.app.api.LanguageAdapter;
import ru.afanasyev.telegram.app.impl.YamlPropertySourceFactory;
import ru.afanasyev.telegram.domain.Language;
import ru.afanasyev.telegram.domain.Message;

import java.util.Map;

import static ru.afanasyev.telegram.domain.Language.RUSSIAN;

@Getter
@Setter
@Configuration
@ConfigurationProperties("russian-lang")
@PropertySource(value = "classpath:/lang/russian-dictionary.yaml", factory = YamlPropertySourceFactory.class)
public class RussianLanguageAdapter implements LanguageAdapter {
    private Map<Message, String> dictionary;

    @Override
    public String accept(Message message) {
        return dictionary.get(message);
    }

    @Override
    public boolean support(Language language) {
        return RUSSIAN.equals(language);
    }
}
