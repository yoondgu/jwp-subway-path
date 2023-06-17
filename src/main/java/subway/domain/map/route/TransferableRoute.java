package subway.domain.map.route;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.jgrapht.GraphPath;
import subway.domain.entity.Line;
import subway.domain.entity.Station;
import subway.domain.map.graph.edge.LineClassifiableSectionEdge;
import subway.domain.vo.Distance;

public class TransferableRoute implements Route {

    private final GraphPath<Station, LineClassifiableSectionEdge> path;

    public TransferableRoute(final GraphPath<Station, LineClassifiableSectionEdge> path) {
        this.path = path;
    }

    public Distance totalDistance() {
        return new Distance((int) path.getWeight());
    }

    public List<Station> stations() {
        return new ArrayList<>(path.getVertexList());
    }

    public List<Line> lines() {
        return path.getEdgeList()
                .stream()
                .map(LineClassifiableSectionEdge::getLine)
                .collect(Collectors.toList());
    }
}
