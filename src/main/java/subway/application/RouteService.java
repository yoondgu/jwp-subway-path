package subway.application;

import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import subway.dao.LineDao;
import subway.dao.SectionDao;
import subway.dao.StationDao;
import subway.domain.entity.Line;
import subway.domain.entity.Passenger;
import subway.domain.entity.Station;
import subway.domain.exception.RequestDataNotFoundException;
import subway.domain.fare.FareCalculator;
import subway.domain.map.SubwayMap;
import subway.domain.map.graph.MultiRoutedStations;
import subway.domain.map.graph.RoutedStations;
import subway.domain.map.route.RouteInfo;
import subway.domain.map.route.TransferableRoute;
import subway.dto.RouteRequest;
import subway.dto.RouteResponse;

@Service
public class RouteService {

    private final FareCalculator fareCalculator;
    private final LineDao lineDao;
    private final SectionDao sectionDao;
    private final StationDao stationDao;

    public RouteService(final FareCalculator fareCalculator,
                        final LineDao lineDao,
                        final SectionDao sectionDao,
                        final StationDao stationDao) {
        this.fareCalculator = fareCalculator;
        this.lineDao = lineDao;
        this.sectionDao = sectionDao;
        this.stationDao = stationDao;
    }

    public RouteResponse findShortestRoute(final RouteRequest routeRequest) {
        final long sourceStationId = routeRequest.getSourceStationId();
        final long targetStationId = routeRequest.getTargetStationId();
        final Station sourceStation = stationDao.findById(sourceStationId)
                .orElseThrow(() -> new RequestDataNotFoundException("출발 역이 존재하지 않습니다."));
        final Station targetStation = stationDao.findById(targetStationId)
                .orElseThrow(() -> new RequestDataNotFoundException("도착 역이 존재하지 않습니다."));

        final SubwayMap subwayMap = new SubwayMap(MultiRoutedStations.from(findSectionsByLine()));
        final TransferableRoute route = subwayMap.findShortestRoute(sourceStation, targetStation);
        final RouteInfo routeInfo = RouteInfo.of(route,
                fareCalculator.calculate(route, new Passenger(routeRequest.getPassengerAge())));
        return RouteResponse.from(routeInfo);
    }

    private Map<Line, RoutedStations> findSectionsByLine() {
        return lineDao.findAll()
                .stream()
                .collect(Collectors.toMap(line -> line,
                        line -> RoutedStations.from(sectionDao.findByLineId(line.getId()))));
    }
}
