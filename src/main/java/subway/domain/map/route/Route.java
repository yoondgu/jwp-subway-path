package subway.domain.map.route;

import java.util.List;
import subway.domain.entity.Line;
import subway.domain.entity.Station;
import subway.domain.vo.Distance;

public interface Route {

    Distance totalDistance();

    List<Station> stations();

    List<Line> lines();
}
