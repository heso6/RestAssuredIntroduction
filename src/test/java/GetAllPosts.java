import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class GetAllPosts extends TestBase {
    private String posts = "/posts";

    @Test
    public void shouldGetAllPosts() {
        when()
                .get(base_Url + posts)
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldGetFirstPost() {
        when()
                .get(base_Url + posts + "/1")
                .then()
                .statusCode(200);
//                .log()
//                .all();
    }

    @Test
    public void shouldGetFirstPostAndValidateJson() {
        Response response =
//                given()
//                        log().
//                        all().
                when()
                        .get(base_Url + posts + "/1")
                        .then()
                        .statusCode(200)
//                        .log()
//                        .all()
                        .extract().response();

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(jsonPath.get("title"), "sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
    }

    @Test
    public void shouldGetFirstPostPostWithPathVariable() {
        given()
                .pathParam("postId", "1")
                .when()
                .get(base_Url + posts + "/{postId}")
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldGetPostForUserWithId3() {
        Response response =
                given()
                .queryParam("userId", "3")
                .queryParam("title", "asperiores ea ipsam voluptatibus modi minima quia sint")
                .when()
                .get(base_Url + posts)
                .then()
                .statusCode(200)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("[0].id").toString(), "21");

    }
}
