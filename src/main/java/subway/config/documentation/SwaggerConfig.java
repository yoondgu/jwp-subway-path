package subway.config.documentation;

import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Component
public class SwaggerConfig {

    @Bean
    public Docket lineApi() {
        return createDocket("노선", "/lines.*");

    }

    @Bean
    public Docket stationApi() {
        return createDocket("역", "/stations.*");

    }

    @Bean
    public Docket routeApi() {
        return createDocket("경로", "/routes.*");

    }

    private Docket createDocket(final String groupName, final String pathRegex) {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(groupName)
                .consumes(consumeContentTypes())
                .produces(produceContentTypes())
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("subway.ui"))
                .paths(PathSelectors.regex(pathRegex))
                .build();
    }

    private Set<String> consumeContentTypes() {
        return Set.of("application/json");
    }

    private Set<String> produceContentTypes() {
        return Set.of("application/json");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("지하철 정보 관리 및 경로 조회 서비스 API")
                .build();
    }
}
