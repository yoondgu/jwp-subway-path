package subway.domain;

import java.util.List;
import subway.domain.entity.Station;
import subway.domain.vo.Distance;

public interface Route {

    Distance totalDistance();

    List<Station> stations();

    Fare additionalCharge();
}
