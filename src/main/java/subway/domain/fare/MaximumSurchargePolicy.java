package subway.domain.fare;

import subway.domain.entity.Line;
import subway.domain.map.route.Route;
import subway.domain.vo.Fare;

public class MaximumSurchargePolicy implements RouteSurchargePolicy {

    @Override
    public Fare surcharge(final Fare fare, final Route route) {
        return fare.plus(findAdditionalChargeMax(route));
    }

    private Fare findAdditionalChargeMax(final Route route) {
        final long maximumAdditionalCharge = route.lines()
                .stream()
                .mapToLong(Line::getAdditionalCharge)
                .max()
                .orElse(0);
        return new Fare(maximumAdditionalCharge);
    }
}
