package subway.application;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import subway.dao.LineDao;
import subway.dao.SectionDao;
import subway.domain.map.graph.RoutedStations;
import subway.domain.entity.Line;
import subway.domain.entity.Section;
import subway.dto.LineRequest;
import subway.dto.LineResponse;
import subway.dto.StationResponse;

@Service
public class LineService {

    private final LineDao lineDao;
    private final SectionDao sectionDao;

    public LineService(final LineDao lineDao, final SectionDao sectionDao) {
        this.lineDao = lineDao;
        this.sectionDao = sectionDao;
    }

    public LineResponse saveLine(LineRequest request) {
        Line persistLine = lineDao.insert(
                new Line(request.getName(), request.getColor(), request.getAdditionalCharge()));
        return LineResponse.of(persistLine);
    }

    public List<LineResponse> findLineResponses() {
        return lineDao.findAll()
                .stream()
                .map(line -> LineResponse.of(line, createStationResponse(line)))
                .collect(Collectors.toList());
    }

    public LineResponse findLineResponseById(Long id) {
        Line persistLine = findLineById(id);
        return LineResponse.of(persistLine, createStationResponse(persistLine));
    }

    private List<StationResponse> createStationResponse(final Line persistLine) {
        List<Section> sections = sectionDao.findByLineId(persistLine.getId());
        RoutedStations routedStations = RoutedStations.from(sections);
        return StationResponse.from(routedStations.extractOrderedStations());
    }

    private Line findLineById(Long id) {
        return lineDao.findById(id);
    }

    public void updateLine(Long id, LineRequest lineUpdateRequest) {
        lineDao.update(new Line(id, lineUpdateRequest.getName(), lineUpdateRequest.getColor(),
                lineUpdateRequest.getAdditionalCharge()));
    }

    public void deleteLineById(Long id) {
        lineDao.deleteById(id);
    }
}
