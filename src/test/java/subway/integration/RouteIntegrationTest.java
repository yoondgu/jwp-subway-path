package subway.integration;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import subway.dto.RouteRequest;

@DisplayName("지하철 경로 관련 기능 통합 테스트")
@Sql(scripts = {"classpath:data.sql", "classpath:data-sections.sql"})
public class RouteIntegrationTest extends IntegrationTest {

    @DisplayName("출발역, 도착역에 대한 최단 경로, 경로 및 승객 연령에 따른 요금을 조회한다.")
    @ParameterizedTest
    @CsvSource(value = {"19:2250", "18:1520", "13:1520", "12:950", "6:950", "5:0", "0:0"}, delimiter = ':')
    void findShortestRoute(int age, int expectedFare) {
        // given, when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .contentType(ContentType.JSON)
                .body(new RouteRequest(9L, 13L, age))
                .get("/routes")
                .then().log().all()
                .extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.body().jsonPath().getList("stations.id"))
                .containsExactly(9, 12, 13);
        assertThat(response.body().jsonPath().getInt("totalDistance"))
                .isEqualTo(35);
        assertThat(response.body().jsonPath().getInt("totalFare"))
                .isEqualTo(expectedFare);
    }

    @DisplayName("잘못된 요청 정보로 조회 시 404 상태코드와 적절한 에러 메시지를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"100:1:출발 역이 존재하지 않습니다.",
            "1:100:도착 역이 존재하지 않습니다.",
            "1:1:출발 역과 도착 역이 동일한 경로를 찾을 수 없습니다.",
            "100:100:출발 역이 존재하지 않습니다."}, delimiter = ':')
    void findShortestRouteBadRequest(final int sourceStationId,
                                     final int targetStationId,
                                     final String errorMessage) {
        // given, when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .contentType(ContentType.JSON)
                .body(new RouteRequest(sourceStationId, targetStationId, 6))
                .get("/routes")
                .then().log().all()
                .extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.body().jsonPath().getString("message")).isEqualTo(errorMessage);
    }
}
