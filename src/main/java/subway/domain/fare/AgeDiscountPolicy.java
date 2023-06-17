package subway.domain.fare;

import subway.domain.entity.Passenger;
import subway.domain.vo.Fare;

public class AgeDiscountPolicy implements PassengerDiscountPolicy {

    @Override
    public Fare discount(final Fare fare, final Passenger passenger) {
        AgeDiscountGrade ageDiscountGrade = AgeDiscountGrade.find(passenger.getAge());
        return ageDiscountGrade.discountFare(fare);
    }
}
