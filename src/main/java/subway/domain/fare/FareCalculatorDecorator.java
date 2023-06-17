package subway.domain.fare;

import subway.domain.entity.Passenger;
import subway.domain.map.route.Route;
import subway.domain.vo.Fare;

public class FareCalculatorDecorator implements FareCalculator {

    private final FareCalculator fareCalculator;

    public FareCalculatorDecorator(final FareCalculator fareCalculator) {
        this.fareCalculator = fareCalculator;
    }

    @Override
    public Fare calculate(final Route route, final Passenger passenger) {
        return fareCalculator.calculate(route, passenger);
    }
}
