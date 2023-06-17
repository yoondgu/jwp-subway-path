package subway.domain.fare;

import java.util.Collections;
import java.util.List;
import subway.domain.entity.Line;
import subway.domain.entity.Station;
import subway.domain.map.route.Route;
import subway.domain.vo.Distance;

public class FakeRouteDistanceProvider implements Route {

    private final Distance distance;

    public FakeRouteDistanceProvider(final Distance distance) {
        this.distance = distance;
    }

    @Override
    public Distance totalDistance() {
        return distance;
    }

    @Override
    public List<Station> stations() {
        return Collections.emptyList();
    }

    @Override
    public List<Line> lines() {
        return Collections.emptyList();
    }
}
