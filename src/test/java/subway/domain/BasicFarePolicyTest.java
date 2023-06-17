package subway.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import subway.domain.entity.Station;
import subway.domain.fare.BasicFarePolicy;
import subway.domain.vo.Fare;
import subway.domain.vo.Age;
import subway.domain.entity.Passenger;
import subway.domain.map.route.Route;
import subway.domain.vo.Distance;

@DisplayName("요금 계산기 단위 테스트")
class BasicFarePolicyTest {

    @DisplayName("전달받은 이용 거리에 따른 요금을 계산해 반환한다")
    @ParameterizedTest
    @CsvSource(value = {"9:1250", "12:1350", "16:1450", "58:2150"}, delimiter = ':')
    void calculate(int distance, int expectedFare) {
        Fare result = new BasicFarePolicy().calculate(
                new FakeRoute(new Distance(distance)),
                new Passenger(Age.GENERAL));

        assertThat(result.getValue())
                .isEqualTo(expectedFare);
    }

    private class FakeRoute implements Route {

        private final Distance distance;

        public FakeRoute(final Distance distance) {
            this.distance = distance;
        }

        @Override
        public Distance totalDistance() {
            return distance;
        }

        @Override
        public List<Station> stations() {
            return Collections.emptyList();
        }

        @Override
        public Fare additionalCharge() {
            return new Fare(0);
        }
    }
}
