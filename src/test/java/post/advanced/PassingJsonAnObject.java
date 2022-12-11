package post.advanced;

import base.TestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.Post;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class PassingJsonAnObject extends TestBase {

    @Test
    public void shouldGetFirstPostAndValidateJson() {
        Post post =
                when()
                        .get(base_Url + posts + "/1")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response()
                        .as(Post.class);
        Assert.assertEquals(post.getTitle(), "sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
        Assert.assertEquals(post.getId(), 1);
    }

    @Test
    public void shouldCreateNewPost() {
        Post post = new Post(1, 4, "some title", "some text");

        given()
                .body(post)
                .contentType(ContentType.JSON)
                .when()
                .post(base_Url + posts)
                .then()
                .statusCode(201);
    }


    @Test
    public void shouldPatchPostWithLimitedBody() {

        Map<String, Object> post = new HashMap<>();

        post.put("title", "this is some new title");
        post.put("body", "Lorem ipsum");

        Response response =
                given()
                        .pathParam("postId", "3")
                        .body(post)
                        .contentType(ContentType.JSON)
                        .when()
                        .patch(base_Url + posts + "/{postId}")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("id").toString(), "3");
        Assert.assertEquals(jsonPath.get("userId").toString(), "1");
        Assert.assertEquals(jsonPath.get("title").toString(), "this is some new title");
        Assert.assertEquals(jsonPath.get("body"), "Lorem ipsum");

    }
}
