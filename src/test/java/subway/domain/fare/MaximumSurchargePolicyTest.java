package subway.domain.fare;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import subway.domain.entity.Line;
import subway.domain.vo.Fare;

class MaximumSurchargePolicyTest {

    private final MaximumSurchargePolicy maximumSurchargePolicy = new MaximumSurchargePolicy();

    @DisplayName("이용하는 노선 중 가장 높은 추가 요금만을 적용한다.")
    @Test
    void surcharge() {
        final Fare basicFare = new Fare(3000);
        final List<Line> lines = List.of(
                new Line("추가요금_0원", "orange", 0),
                new Line("추가요금_100원", "green", 100),
                new Line("추가요금_300원", "blue", 300)
        );

        final Fare surcharged = maximumSurchargePolicy.surcharge(basicFare, new FakeRouteLineProvider(lines));
        assertThat(surcharged.getValue())
                .isEqualTo(3300);
    }

    @DisplayName("이용하는 노선이 모두 추가 요금이 없으면 기본 요금 그대로이다.")
    @Test
    void surchargeNoAdditionalCharge() {
        final Fare basicFare = new Fare(3000);
        final List<Line> lines = List.of(
                new Line("추가요금_0원", "orange", 0),
                new Line("추가요금_0원2", "green", 0),
                new Line("추가요금_0원3", "blue", 0)
        );

        final Fare surcharged = maximumSurchargePolicy.surcharge(basicFare, new FakeRouteLineProvider(lines));
        assertThat(surcharged.getValue())
                .isEqualTo(3000);
    }
}
