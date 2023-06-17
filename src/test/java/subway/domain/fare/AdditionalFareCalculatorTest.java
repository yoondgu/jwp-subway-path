package subway.domain.fare;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import subway.domain.entity.Passenger;
import subway.domain.vo.Distance;
import subway.domain.vo.Fare;

@DisplayName("기본 요금 외 추가 계산 단위 테스트")
class AdditionalFareCalculatorTest {

    @DisplayName("전달받은 추가 요금, 승객 할인 정책에 따른 요금을 계산해 반환한다")
    @Test
    void calculate() {
        Fare additionalCharge = new Fare(1000);
        Fare passengerDiscount = new Fare(500);
        final BasicFareCalculator basicCalculator = new BasicFareCalculator();
        FareCalculator additionalCalculator = new AdditionalFareCalculator(
                basicCalculator,
                (fare, route) -> fare.plus(additionalCharge),
                ((fare, passenger) -> fare.minus(passengerDiscount))
        );

        Distance distance = new Distance(9);
        final FakeRouteDistanceProvider foundRoute = new FakeRouteDistanceProvider(distance);
        final Passenger passenger = new Passenger(30);

        final Fare expected = basicCalculator.calculate(foundRoute, passenger)
                .plus(additionalCharge)
                .minus(passengerDiscount);
        Assertions.assertThat(additionalCalculator.calculate(foundRoute, passenger))
                .isEqualTo(expected);
    }
}
