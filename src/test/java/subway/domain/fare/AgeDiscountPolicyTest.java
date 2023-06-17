package subway.domain.fare;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import subway.domain.entity.Passenger;
import subway.domain.vo.Fare;

class AgeDiscountPolicyTest {

    private final AgeDiscountPolicy ageDiscountPolicy = new AgeDiscountPolicy();

    @DisplayName("승객의 연령에 따라 요금 할인을 적용한다.")
    @ParameterizedTest
    @CsvSource(value = {"0:0", "1:0", "6:1325", "13:2120", "19:3000", "100:3000"}, delimiter = ':')
    void discount(int age, int expected) {
        final Fare basicFare = new Fare(3000);

        final Fare discounted = ageDiscountPolicy.discount(basicFare, new Passenger(age));
        
        assertThat(discounted.getValue())
                .isEqualTo(expected);
    }
}
