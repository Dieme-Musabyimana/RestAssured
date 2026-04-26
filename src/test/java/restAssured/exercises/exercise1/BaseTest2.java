package restAssured.exercises.exercise1;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;
public class BaseTest2 {
    protected RequestSpecification specification;
    @BeforeSuite
    public void specification(){
        specification = new RequestSpecBuilder()
                .setBaseUri("https://dummyjson.com/")
                .addHeader("Content-Type", "application/json")
                .build();

    }
}
