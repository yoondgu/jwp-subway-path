package subway.domain.fare;

import subway.domain.map.route.Route;
import subway.domain.vo.Fare;

public interface RouteSurchargePolicy {

    Fare surcharge(Fare fare, Route route);
}
