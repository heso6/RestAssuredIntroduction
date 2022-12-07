import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class CreateNewPost extends TestBase {
    private String posts = "/posts";
    String body = "{\n" +
            "    \"userId\": 1,\n" +
            "    \"title\": \"some title\",\n" +
            "    \"body\": \"Lorem Ipsum Lorem Ipsum Lorem Ipsum vLorem Ipsum Lorem Ipsum\"\n" +
            "}";

    @Test
    public void shouldCreateNewPost() {
        RestAssured.given()
                .body(body)
                .contentType(ContentType.JSON)
                .when()
                .post(base_Url + posts)
                .then()
                .statusCode(201);
    }
}
