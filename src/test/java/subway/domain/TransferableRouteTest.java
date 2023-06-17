package subway.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static subway.fixture.LineFixture.FIXTURE_LINE_1_ADDITIONAL_CHARGE_1000;
import static subway.fixture.LineFixture.FIXTURE_LINE_2;
import static subway.fixture.LineFixture.FIXTURE_LINE_3;
import static subway.fixture.SectionFixture.ROUTED_SECTIONS_1_TRANSFER_2_AT_ST1;
import static subway.fixture.SectionFixture.ROUTED_SECTIONS_2_TRANSFER_1_AT_ST1_3_AT_ST9;
import static subway.fixture.SectionFixture.ROUTED_SECTIONS_3_TRANSFER_1_AT_ST2_TRANSFER_2_AT_ST9;
import static subway.fixture.StationFixture.FIXTURE_STATION_1;
import static subway.fixture.StationFixture.FIXTURE_STATION_9;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TransferableRouteTest {

    @DisplayName("해당 경로에 적용할 추가요금을 찾는다")
    @Test
    void additionalCharge() {
        SubwayMap subwayMap = new SubwayMap(MultiRoutedStations.from(Map.of(
                FIXTURE_LINE_1_ADDITIONAL_CHARGE_1000, RoutedStations.from(ROUTED_SECTIONS_1_TRANSFER_2_AT_ST1),
                FIXTURE_LINE_2, RoutedStations.from(ROUTED_SECTIONS_2_TRANSFER_1_AT_ST1_3_AT_ST9),
                FIXTURE_LINE_3, RoutedStations.from(ROUTED_SECTIONS_3_TRANSFER_1_AT_ST2_TRANSFER_2_AT_ST9)
        )));
        TransferableRoute route = subwayMap.findShortestRoute(FIXTURE_STATION_1, FIXTURE_STATION_9);

        assertThat(route.additionalCharge()).isEqualTo(new Fare(1000));
    }
}
