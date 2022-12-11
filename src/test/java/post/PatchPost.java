package post;

import base.TestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PatchPost extends TestBase {

    private String posts = "/posts";

    String fullBody = "{\n" +
            "    \"userId\": 123123123,\n" +
            "    \"title\": \"some new title\",\n" +
            "    \"body\": \"Lorem Ipsum Lorem Ipsum Lorem Ipsum vLorem Ipsum Lorem Ipsum\"\n" +
            "}";

    String partOfBody = "{\n" +
            "    \"body\": \"Lorem Ipsum Lorem Ipsum Lorem Ipsum vLorem Ipsum Lorem Ipsum\"\n" +
            "}";

    @Test
    public void shouldPatchPost() {
        Response response =
                given()
                        .pathParam("postId", "3")
                        .body(fullBody)
                        .contentType(ContentType.JSON)
                        .when()
                        .patch(base_Url + posts + "/{postId}")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("id").toString(), "3");
        Assert.assertEquals(jsonPath.get("userId").toString(), "123123123");
        Assert.assertEquals(jsonPath.get("title"), "some new title");
        Assert.assertEquals(jsonPath.get("body"), "Lorem Ipsum Lorem Ipsum Lorem Ipsum vLorem Ipsum Lorem Ipsum");

    }


    @Test
    public void shouldPatchPostWithLimitedBody() {
        Response response =
                given()
                        .pathParam("postId", "3")
                        .body(partOfBody)
                        .contentType(ContentType.JSON)
                        .when()
                        .patch(base_Url + posts + "/{postId}")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("id").toString(), "3");
        Assert.assertEquals(jsonPath.get("title").toString(), "ea molestias quasi exercitationem repellat qui ipsa sit aut");
        Assert.assertEquals(jsonPath.get("body"), "Lorem Ipsum Lorem Ipsum Lorem Ipsum vLorem Ipsum Lorem Ipsum");

    }
}
