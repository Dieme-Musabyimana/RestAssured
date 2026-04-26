package restAssured.exercises.utils;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class GmailTokenManager {
    @Step("Step1: Renew token")
    public String getFreshAccessToken() {
        return given()
                .log().all()
                .baseUri("https://oauth2.googleapis.com")
                .contentType(ContentType.URLENC)
                .formParam("client_id", ConfigLoader.getInstance().getClientId())
                .formParam("client_secret", ConfigLoader.getInstance().getClientSecret())
                .formParam("refresh_token", ConfigLoader.getInstance().getRefreshToken())
                .formParam("grant_type", ConfigLoader.getInstance().getClientGrantType())
                .when()
                .post("/token")
                .then()
                .statusCode(200)
                .extract()
                .path("access_token");
    }
}
