package subway.domain.fare;

import subway.domain.entity.Passenger;
import subway.domain.vo.Fare;

public interface PassengerDiscountPolicy {

    Fare discount(Fare fare, Passenger passenger);
}
