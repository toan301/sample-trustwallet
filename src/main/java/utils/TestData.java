package utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:testdata.properties")
public class TestData {
    @Value("${passcode}")
    String passcode;

    @Bean("passcode")
    public String getPasscode() {
        return passcode;
    }
}
