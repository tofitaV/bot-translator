package ThirdPatry;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

@Component
public class APIService {

    @Value("${openai.api.key}")
    private String apiKey;

    @Setter
    @Value("${api.service.ignoreException:false}")
    private boolean ignoreException;

    @Getter
    private RequestSpecification requestSpecification;

    @Getter
    private ResponseSpecification responseSpecification;

    @PostConstruct
    private void initialize() {
        initializeRequestSpecification();
        initializeResponseSpecification();
    }

    private void initializeResponseSpecification() {
        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(anyOf(
                        is(HttpStatus.OK.value()),
                        is(HttpStatus.ACCEPTED.value()),
                        is(HttpStatus.NO_CONTENT.value()),
                        is(HttpStatus.CREATED.value())))
                .build();
    }

    private void initializeRequestSpecification() {
        requestSpecification = new RequestSpecBuilder()
                .setRelaxedHTTPSValidation()
                .setBaseUri("https://api.openai.com/v1/")
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + apiKey)
                .build();
    }

    public <T> Response post(String url, T body) {
        Response response = given()
                .spec(requestSpecification)
                .body(body)
                .post(url);
        return handleResponse(response);
    }

    private Response handleResponse(Response response) {
        if (!ignoreException) {
            response.then().assertThat()
                    .spec(responseSpecification)
                    .extract();
        }
        return response;
    }

}
