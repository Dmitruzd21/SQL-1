import lombok.Value;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import ru.netology.web.data.DataHelper;

public class APITest {

    @Value
    public static class AuthInfo2 {
        private String login;
        private String code;
    }

    public static AuthInfo2 getAuthInfo2() {
        return new AuthInfo2("vasya", "599640");
    }


    private RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();


    @Test
    public void shouldLogin() {
        given()
                .spec(requestSpec)
                .body(DataHelper.getAuthInfo())
                .when()
                .post("/api/auth")
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldVerify (){
        given()
                .spec(requestSpec)
                .body(getAuthInfo2())
                .when()
                .post("/api/auth/verification")
                .then()
                .statusCode(200);
    }
}
