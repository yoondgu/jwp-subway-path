package subway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import subway.domain.fare.AdditionalFareCalculator;
import subway.domain.fare.AgeDiscountPolicy;
import subway.domain.fare.BasicFareCalculator;
import subway.domain.fare.FareCalculator;
import subway.domain.fare.MaximumSurchargePolicy;

@Configuration
public class FareConfiguration {

    @Bean
    FareCalculator farePolicy() {
        return new AdditionalFareCalculator(
                new BasicFareCalculator(),
                new MaximumSurchargePolicy(),
                new AgeDiscountPolicy());
    }
}
