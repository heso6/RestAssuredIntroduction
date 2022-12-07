import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    public static final String base_Url = "https://jsonplaceholder.typicode.com";

    @BeforeMethod
    public void setup() {
        //Dzieki temu nie musimy uzywać .log() i .all()
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }
}
