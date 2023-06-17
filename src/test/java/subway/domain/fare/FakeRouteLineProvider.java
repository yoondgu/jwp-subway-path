package subway.domain.fare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import subway.domain.entity.Line;
import subway.domain.entity.Station;
import subway.domain.map.route.Route;
import subway.domain.vo.Distance;

public class FakeRouteLineProvider implements Route {

    private final List<Line> lines;

    public FakeRouteLineProvider(final List<Line> lines) {
        this.lines = lines;
    }

    @Override
    public Distance totalDistance() {
        return null;
    }

    @Override
    public List<Station> stations() {
        return Collections.emptyList();
    }

    @Override
    public List<Line> lines() {
        return new ArrayList<>(lines);
    }
}
