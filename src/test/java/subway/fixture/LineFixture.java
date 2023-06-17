package subway.fixture;

import subway.domain.entity.Line;

public class LineFixture {

    public static final Line FIXTURE_LINE_1 = new Line(1L, "1호선", "blue", 0);
    public static final Line FIXTURE_LINE_1_ADDITIONAL_CHARGE_1000 = new Line(1L, "1호선", "blue", 1000);
    public static final Line FIXTURE_LINE_2 = new Line(2L, "2호선", "green", 0);
    public static final Line FIXTURE_LINE_3 = new Line(3L, "3호선", "orange", 0);
}
