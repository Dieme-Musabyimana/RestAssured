package restAssured.exercises.exercise3.task3;

import io.qameta.allure.*;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import restAssured.exercises.utils.GmailTokenManager;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@Epic("Gmail service")
@Feature("Gmail API")
public class GmailTests extends BaseThread {
    GmailTokenManager accessToken = new GmailTokenManager();
    public static String currentToken;
    public static String messageId;

    @Story("Send email")
    @Link("https://example.org")
    @Link(name= "allure", type = "mylink")
    @TmsLink("12345")
    @Description("this is the description")
    @Test(description = "This test must be executed first")
    @Step("Step2: Compose and send email")
    public void sendEmailWithAutoToken() {
        currentToken = accessToken.getFreshAccessToken();
        System.out.println("New Access Token Generated: " + currentToken);

        String emailContent = "To: musabyimanadieme83@gmail.com\r\n" +
                "Subject: Test Purpose\r\n\r\n" +
                "Hello Developer your test was passed!!!!!!!";
        String encodedEmail = Base64.getUrlEncoder().encodeToString(emailContent.getBytes());

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("raw", encodedEmail);

        Response response = given()
                .baseUri("https://gmail.googleapis.com")
                .header("Authorization", "Bearer " + currentToken)
                .header("Content-Type", "application/json")
                .filter(new AllureRestAssured())
                .body(requestBody)
                .when()
                .post("/gmail/v1/users/me/messages/send")
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println("Email Sent Successfully! Response: " + response.asPrettyString());
        messageId = response.jsonPath().getString("id");
        System.out.println("Step 1: Email Sent. Captured ID: " + messageId);
    }

    @Story("List all recent emails")
    @Test(dependsOnMethods = "sendEmailWithAutoToken")
    @Step("Step1: List all recent emails")
    public void listRecentMessage(){
        Response response = given()
                .baseUri("https://gmail.googleapis.com")
                .header("Authorization", "Bearer " + currentToken)
                .queryParam("maxResults", 10)
                .when()
                .get("/gmail/v1/users/me/messages")
                .then()
                .statusCode(200)
                .extract().response();

        java.util.List<String> ids = response.jsonPath().getList("messages.id");

        System.out.println("--- Recent Message IDs---");

        if (ids != null) {
            for (String id : ids) {
                System.out.println(id);
            }
            messageId = ids.get(0);
        }

        System.out.println("----------------------------------------");
        System.out.println("Targeting for next steps: " + messageId);
    }

    @Story("Get message details")
    @Test(dependsOnMethods = "listRecentMessage")
    @Step("Get the message details")
    public void getMessageDetails(){
        System.out.println("Fetching details for ID:" + messageId);
        Response response = given()
                .baseUri("https://gmail.googleapis.com")
                .header("Authorization", "Bearer " + currentToken)
                .pathParam("id", messageId)
                .filter(new AllureRestAssured())
                .when()
                .get("/gmail/v1/users/me/messages/{id}")
                .then()
                .statusCode(200)
                .extract().response();

        String snippet = response.jsonPath().getString("snippet");
        System.out.println("Confirmed Content via Snippet: " + snippet);
    }

    @Story("Modify message details")
    @Test(dependsOnMethods = "getMessageDetails")
    public void modifyMessage(){
        Map<String, Object> modifyBody = new HashMap<>();
        modifyBody.put("addLabelIds", new String[]{"STARRED"});

        given()
                .baseUri("https://gmail.googleapis.com")
                .header("Authorization", "Bearer " + currentToken)
                .header("Content-Type", "application/json")
                .pathParam("id", messageId)
                .body(modifyBody)
                .when()
                .post("/gmail/v1/users/me/messages/{id}/modify")
                .then()
                .statusCode(200);
        System.out.println("Step 4: Message successfully modified");
    }

    @Story("Delete the message")
    @Test(dependsOnMethods = "modifyMessage")
    public void deleteMessage() {
        System.out.println("Deleting message with ID: " + messageId);

        given()
                .baseUri("https://gmail.googleapis.com")
                .header("Authorization", "Bearer " + currentToken)
                .pathParam("id", messageId)
                .filter(new AllureRestAssured())
                .when()
                .delete("/gmail/v1/users/me/messages/{id}")
                .then()
                .statusCode(204);

        System.out.println("Step 5: Message successfully deleted.");
    }
}
