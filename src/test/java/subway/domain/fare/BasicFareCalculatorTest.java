package subway.domain.fare;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import subway.domain.entity.Passenger;
import subway.domain.vo.Distance;
import subway.domain.vo.Fare;

@DisplayName("기본 요금 계산 단위 테스트")
class BasicFareCalculatorTest {

    @DisplayName("전달받은 이용 거리에 따른 요금을 계산해 반환한다")
    @ParameterizedTest
    @CsvSource(value = {"9:1250", "12:1350", "16:1450", "58:2150"}, delimiter = ':')
    void calculate(int distance, int expectedFare) {
        Fare result = new BasicFareCalculator().calculate(
                new FakeRouteDistanceProvider(new Distance(distance)),
                new Passenger(30));

        assertThat(result.getValue())
                .isEqualTo(expectedFare);
    }
}
