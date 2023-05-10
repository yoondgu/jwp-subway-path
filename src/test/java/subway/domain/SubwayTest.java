package subway.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static subway.domain.LineFixture.FIXTURE_LINE_1;
import static subway.domain.SectionFixture.SECTION_END;
import static subway.domain.SectionFixture.SECTION_MIDDLE_1;
import static subway.domain.SectionFixture.SECTION_MIDDLE_2;
import static subway.domain.SectionFixture.SECTION_MIDDLE_3;
import static subway.domain.SectionFixture.SECTION_START;
import static subway.domain.StationFixture.FIXTURE_STATION_2;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SubwayTest {

    private static final Subway FIXTURE_SUBWAY = Subway.of(FIXTURE_LINE_1, List.of(
            SECTION_START,
            SECTION_MIDDLE_1,
            SECTION_MIDDLE_2,
            SECTION_MIDDLE_3,
            SECTION_END
    ));

    @DisplayName("주어진 역의 왼쪽에 있는 구간을 구할 수 있다.")
    @Test
    void findLeftSection() {
        assertThat(FIXTURE_SUBWAY.findLeftSection(FIXTURE_STATION_2))
                .isEqualTo(SECTION_START);
    }

    @DisplayName("주어진 역의 오른쪽에 있는 구간을 구할 수 있다.")
    @Test
    void findRightSection() {
        assertThat(FIXTURE_SUBWAY.findRightSection(FIXTURE_STATION_2))
                .isEqualTo(SECTION_MIDDLE_1);
    }
}
