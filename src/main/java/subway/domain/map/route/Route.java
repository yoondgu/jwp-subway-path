package subway.domain.map.route;

import java.util.List;
import subway.domain.vo.Fare;
import subway.domain.entity.Station;
import subway.domain.vo.Distance;

public interface Route {

    Distance totalDistance();

    List<Station> stations();

    Fare additionalCharge();
}
