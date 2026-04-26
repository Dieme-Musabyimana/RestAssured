package restAssured.exercises.exercise3.task1;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
public class buildPayloads {
@Test
    public void buildPayload(){
   Geo geo = new Geo();
   geo.setLat("37373");
   geo.setLng("48.488");

   Address address = new Address();
   address.setStreet("Kigali");
   address.setSuite("Apt.44");
   address.setCity("Kigali");
   address.setZipcode("00000");
   address.setGeo(geo);

   User user = new User();
   user.setName("John");
   user.setUsername("john");
   user.setEmail("doe@gmail.com");
   user.setAddress(address);


    ResponseClass response = given()
           .body(user)
           .baseUri("https://jsonplaceholder.typicode.com")
            .header("Content-Type", "application/json")
            .when()
           .post("/users")
           .then()
            .assertThat()
            .statusCode(201)
            .contentType("application/json").log().all()
           .extract().response().as(ResponseClass.class);
    Assert.assertNotNull(response.getId());

    }


}
