package subway.domain.fare;

import subway.domain.entity.Passenger;
import subway.domain.map.route.Route;
import subway.domain.vo.Fare;

public class AdditionalFareCalculator extends FareCalculatorDecorator {

    private final RouteSurchargePolicy routeSurchargePolicy;
    private final PassengerDiscountPolicy passengerDiscountPolicy;

    public AdditionalFareCalculator(final FareCalculator fareCalculator,
                                    final RouteSurchargePolicy routeSurchargePolicy,
                                    final PassengerDiscountPolicy passengerDiscountPolicy) {
        super(fareCalculator);
        this.routeSurchargePolicy = routeSurchargePolicy;
        this.passengerDiscountPolicy = passengerDiscountPolicy;
    }

    @Override
    public Fare calculate(final Route route, final Passenger passenger) {
        final Fare basicFare = super.calculate(route, passenger);
        final Fare surChargedFare = routeSurchargePolicy.surcharge(basicFare, route);
        return passengerDiscountPolicy.discount(surChargedFare, passenger);
    }
}
