package subway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import subway.domain.BasicFarePolicy;
import subway.domain.FarePolicy;

@Configuration
public class FareConfiguration {

    @Bean
    FarePolicy farePolicy() {
        return new BasicFarePolicy();
    }
}
