package user;

import base.TestBase;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteUser extends TestBase {

    @Test
    public void shouldDeleteFirstUser() {
        given()
                .pathParam("userId", "1")
                .when()
                .delete(base_Url + users + "/{userId}")
                .then()
                .statusCode(200);
    }
}
