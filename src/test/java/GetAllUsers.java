import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class GetAllUsers extends TestBase {
    private String users = "/users";

    @Test
    public void shouldGetAllUsers() {
        when()
                .get(base_Url + users)
                .then()
                .statusCode(200);

    }

    @Test
    public void shouldGetFirstUser() {
        when()
                .get(base_Url + users + "/1")
                .then()
                .statusCode(200)
                .log()
                .all();
    }

    @Test
    public void shouldGetFirstUserAndValidateJson() {
        Response response =
                given()
//                        log().
//                        all().
                        .when()
                        .get(base_Url + users + "/1")
                        .then()
                        .statusCode(200)
//                        .log()
//                        .all()
                        .extract().response();

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(jsonPath.get("address.geo.lat"), "-37.3159");
    }

    @Test
    public void shouldGetFirstUserPostWithPathVariable() {
        given()
                .pathParam("userId", "1")
                .when()
                .get(base_Url + users + "/{userId}")
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldGetUserWithId3() {
        Response response =
                given()
                        .queryParam("username", "Samantha")
                        .when()
                        .get(base_Url + users)
                        .then()
                        .statusCode(200)
                        .extract().response();


        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("[0].id").toString(), "3");
    }

}
