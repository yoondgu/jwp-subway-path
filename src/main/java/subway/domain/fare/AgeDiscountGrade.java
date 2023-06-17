package subway.domain.fare;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;
import subway.domain.vo.Fare;

public enum AgeDiscountGrade {

    GENERAL(19, null, fare -> fare),
    YOUTH(13, 18, fare -> discount(fare, 0.8)),
    CHILD(6, 12, fare -> discount(fare, 0.5)),
    INFANT(0, 5, fare -> new Fare(0));

    private static final Fare DEDUCT_AMOUNT = new Fare(350);
    private final Integer min;
    private final Integer max;
    private final Function<Fare, Fare> discountPolicy;

    AgeDiscountGrade(final Integer min, final Integer max, final Function<Fare, Fare> discountPolicy) {
        this.min = min;
        this.max = max;
        this.discountPolicy = discountPolicy;
    }

    private static Fare discount(Fare fare, double discountRate) {
        return fare.minus(DEDUCT_AMOUNT).multiply(discountRate);
    }

    public static AgeDiscountGrade find(int age) {
        return Arrays.stream(values())
                .filter(ageGrade -> ageGrade.isWithIn(age))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 연령 등급이 존재하지 않습니다."));
    }

    private boolean isWithIn(int age) {
        if (Objects.nonNull(min) && age < min) {
            return false;
        }
        if (Objects.nonNull(max) && age > max) {
            return false;
        }
        return true;
    }

    public Fare discountFare(Fare fare) {
        return discountPolicy.apply(fare);
    }
}
