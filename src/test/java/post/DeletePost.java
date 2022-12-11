package post;

import base.TestBase;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeletePost extends TestBase {

    private String posts = "/posts";

    @Test
    public void shouldDeleteFirstPost() {
        given()
                .pathParam("postId", "1")
                .when()
                .delete(base_Url + posts + "/{postId}")
                .then()
                .statusCode(200);
    }
}
