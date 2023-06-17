package subway.domain.fare;

import subway.domain.entity.Passenger;
import subway.domain.map.route.Route;
import subway.domain.vo.Fare;

public interface FareCalculator {

    Fare calculate(final Route route, final Passenger passenger);
}
