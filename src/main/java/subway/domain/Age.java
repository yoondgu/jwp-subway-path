package subway.domain;

import java.util.function.Function;

public enum Age {

    GENERAL(25, null, fare -> fare),
    YOUTH(14, 24, fare -> discount(fare, 0.8)),
    CHILD(null, 13, fare -> discount(fare, 0.5));

    private static final Fare DEDUCT_AMOUNT = new Fare(350);
    private final Integer min;
    private final Integer max;
    private final Function<Fare, Fare> discountPolicy;

    Age(final Integer min, final Integer max, final Function<Fare, Fare> discountPolicy) {
        this.min = min;
        this.max = max;
        this.discountPolicy = discountPolicy;
    }

    private static Fare discount(Fare fare, double discountRate) {
        return fare.minus(DEDUCT_AMOUNT).multiply(discountRate);
    }

    public Fare discountFare(Fare fare) {
        return discountPolicy.apply(fare);
    }
}
