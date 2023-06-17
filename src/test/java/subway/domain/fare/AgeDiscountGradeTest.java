package subway.domain.fare;

import static org.assertj.core.api.Assertions.assertThat;
import static subway.domain.fare.AgeDiscountGrade.CHILD;
import static subway.domain.fare.AgeDiscountGrade.GENERAL;
import static subway.domain.fare.AgeDiscountGrade.INFANT;
import static subway.domain.fare.AgeDiscountGrade.YOUTH;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import subway.domain.vo.Fare;

@DisplayName("연령 할인 등급 단위 테스트")
class AgeDiscountGradeTest {

    private static Stream<Arguments> ageAndAgeDiscountGrade() {
        return Stream.of(
                Arguments.of(0, AgeDiscountGrade.INFANT),
                Arguments.of(5, AgeDiscountGrade.INFANT),
                Arguments.of(6, CHILD),
                Arguments.of(12, CHILD),
                Arguments.of(13, AgeDiscountGrade.YOUTH),
                Arguments.of(18, AgeDiscountGrade.YOUTH),
                Arguments.of(19, AgeDiscountGrade.GENERAL)
        );
    }

    @DisplayName("전달받은 나이에 해당하는 연령 할인 등급을 반환한다.")
    @ParameterizedTest
    @MethodSource("ageAndAgeDiscountGrade")
    void find(int age, AgeDiscountGrade ageDiscountGrade) {

        assertThat(AgeDiscountGrade.find(age))
                .isEqualTo(ageDiscountGrade);
    }

    @DisplayName("어린이는 운임에서 350원을 공제한 금액의 50%를 할인한다.")
    @Test
    void discountChild() {
        Fare fare = new Fare(3000);

        final Fare discounted = CHILD.discountFare(fare);

        assertThat(discounted.getValue())
                .isEqualTo(1325);
    }

    @DisplayName("청소년은 운임에서 350원을 공제한 금액의 20%를 할인한다.")
    @Test
    void discountYouth() {
        Fare fare = new Fare(3000);

        final Fare discounted = YOUTH.discountFare(fare);

        assertThat(discounted.getValue())
                .isEqualTo(2120);
    }

    @DisplayName("일반 연령은 할인을 적용하지 않는다.")
    @Test
    void discountGeneral() {
        Fare fare = new Fare(3000);

        final Fare discounted = GENERAL.discountFare(fare);

        assertThat(discounted.getValue())
                .isEqualTo(3000);
    }

    @DisplayName("영유아는 운임을 0원으로 적용한다.")
    @Test
    void discountInfant() {
        Fare fare = new Fare(3000);

        final Fare discounted = INFANT.discountFare(fare);

        assertThat(discounted.getValue())
                .isEqualTo(0);
    }
}
