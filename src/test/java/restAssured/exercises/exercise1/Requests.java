package restAssured.exercises.exercise1;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class Requests extends BaseTest2 {
    @Test
    public void defineSpec() {

Response res = given()
        .spec(specification)
        .queryParam("limit", "5")
        . when()
        .get("users");
        res.then()
        .assertThat()
        .statusCode(200)
                .time(lessThan(2000L))
                .header("Content-Type", containsString("application/json"))
         .body("users",hasSize(greaterThan(0)))
                .body("users", hasSize(5))
              .log().ifValidationFails();

}
}

