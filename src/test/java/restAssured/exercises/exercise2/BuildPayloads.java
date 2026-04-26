package restAssured.exercises.exercise2;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BuildPayloads {
    @Test
    public void createColorPayload() {
        Map<String, Object> topParent = new HashMap<>();
        List<Map<String, Object>> colors = new ArrayList<>();
        Map<String, Object> blackColor = new HashMap<>();
        Map<String, Object> whiteColor = new HashMap<>();


        List<Integer> rgbaList = new ArrayList<>();
        rgbaList.add(255);
        rgbaList.add(255);
        rgbaList.add(255);
        rgbaList.add(1);

        blackColor.put("rgba", rgbaList);
        blackColor.put("hex", "#000");

        Map<String, Object> colorDetails = new HashMap<>();
        colorDetails.put("color", "black");
        colorDetails.put("category", "hue");
        colorDetails.put("type", "primary");
        colorDetails.put("code", blackColor);
        colors.add(colorDetails);


        List<Integer> whiteRgbaList = new ArrayList<>();
        whiteRgbaList.add(0);
        whiteRgbaList.add(0);
        whiteRgbaList.add(0);
        whiteRgbaList.add(1);
        whiteColor.put("rgba", whiteRgbaList);
        whiteColor.put("hex", "#FFF");

        Map<String, Object> whiteColorDetails = new HashMap<>();
        whiteColorDetails.put("color", "white");
        whiteColorDetails.put("category", "value");

        whiteColorDetails.put("code",whiteColor);
        colors.add(whiteColorDetails);

        topParent.put("colors", colors);


        Response res = given()
                .contentType("application/json")
               .body(topParent)
                .when()
                .post("https://postman-echo.com/post")
//                .post("https://dbb03ab2-3ba8-4956-8202-374b4bc6c326.mock.pstmn.io")
                .then().log().all().extract().response();
        res.then().body("json.colors[0].color", equalTo("black"));
//        res.then().body("colors[0].color", equalTo("black"));

    }

}


