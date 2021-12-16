package api;

import SQL.SQLMethodsWithDbUtils;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Value;
import ru.netology.web.data.DataHelper;

import static io.restassured.RestAssured.given;

public class APImethods {

    private String extractedToken;

    public String getExtractedToken() {
        return extractedToken;
    }

    public void setExtractedToken(String extractedToken) {
        this.extractedToken = extractedToken;
    }


    private RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public void login() {
        given()
                .spec(requestSpec)
                .body(DataForAPI.getLoginInfo())
                .when()
                .post("/api/auth")
                .then()
                .statusCode(200);
    }

    public String verifyAndExtractToken() {
        String token = given()
                .spec(requestSpec)
                .body(DataForAPI.getVerificationInfo())
                .when()
                .post("/api/auth/verification")
                .then()
                .statusCode(200)
                .extract()
                .path("token");
        setExtractedToken(token);
        return token;
    }

    public int extractCardBalance(int cardIndex) {
        Response response = given()
                .spec(requestSpec)
                .header("Authorization", "Bearer " + getExtractedToken())
                .when()
                .get("/api/cards")
                .then()
                .statusCode(200)
                .extract()
                .response();

        int cardBalance1 = response.path("[0].balance");
        int cardBalance2 = response.path("[1].balance");
        if (cardIndex == 1) {
            return cardBalance1;
        } else {
            return cardBalance2;
        }
    }

    public void transferMoneyFrom2To1Card() {
        given()
                .spec(requestSpec)
                .header("Authorization", "Bearer " + getExtractedToken())
                .body(DataForAPI.getTransferInfo())
                .when()
                .post("api/transfer")
                .then()
                .statusCode(200);
    }

}
