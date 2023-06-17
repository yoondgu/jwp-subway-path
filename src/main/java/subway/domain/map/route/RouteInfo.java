package subway.domain.map.route;

import java.util.List;
import subway.domain.entity.Station;
import subway.domain.vo.Fare;
import subway.domain.vo.Distance;

public class RouteInfo {

    private final List<Station> stations;
    private final Distance totalDistance;
    private final Fare totalFare;

    private RouteInfo(final List<Station> stations, final Distance totalDistance, final Fare totalFare) {
        this.stations = stations;
        this.totalDistance = totalDistance;
        this.totalFare = totalFare;
    }

    public static RouteInfo of(TransferableRoute transferableRoute, Fare totalFare) {
        return new RouteInfo(
                transferableRoute.stations(),
                transferableRoute.totalDistance(),
                totalFare
        );
    }

    public List<Station> getStations() {
        return stations;
    }

    public Distance getTotalDistance() {
        return totalDistance;
    }

    public Fare getTotalFare() {
        return totalFare;
    }
}
