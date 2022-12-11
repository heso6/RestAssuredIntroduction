package user;

import base.TestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateUser extends TestBase {

    String fullBody = "{\n" +
            "    \"name\": \"Jan\",\n" +
            "    \"username\": \"Kowal\",\n" +
            "    \"email\": \"JKowalski@april.biz\",\n" +
            "    \"address\": {\n" +
            "        \"street\": \"Usa\",\n" +
            "        \"suite\": \"Groove Steet\",\n" +
            "        \"city\": \"NY\",\n" +
            "        \"zipcode\": \"45-3874\",\n" +
            "        \"geo\": {\n" +
            "            \"lat\": \"-37.3159\",\n" +
            "            \"lng\": \"81.1496\"\n" +
            "        }\n" +
            "    },\n" +
            "    \"phone\": \"1-770-736-8031 x56442\",\n" +
            "    \"website\": \"hildegard.org\",\n" +
            "    \"company\": {\n" +
            "        \"name\": \"Romaguera-Crona\",\n" +
            "        \"catchPhrase\": \"Multi-layered client-server neural-net\",\n" +
            "        \"bs\": \"harness real-time e-markets\"\n" +
            "    }\n" +
            "}";

    String partOfBody = "{\n" +
            "    \"name\": \"Lorem Ipsum Lorem Ipsum Lorem Ipsum vLorem Ipsum Lorem Ipsum\"\n" +
            "}";

    @Test
    public void shouldUpdateUser() {
        Response response =
                given()
                        .pathParam("id", "1")
                        .body(fullBody)
                        .contentType(ContentType.JSON)
                        .when()
                        .put(base_Url + users + "/{id}")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("id").toString(), "1");
        Assert.assertEquals(jsonPath.get("name"), "Jan");
        Assert.assertEquals(jsonPath.get("username"), "Kowal");

    }


    @Test
    public void shouldUpdateUserWithLimitedBody() {
        Response response =
                given()
                        .pathParam("id", "1")
                        .body(partOfBody)
                        .contentType(ContentType.JSON)
                        .when()
                        .put(base_Url + users + "/{id}")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("id").toString(), "1");
        Assert.assertEquals(jsonPath.get("name"), "Lorem Ipsum Lorem Ipsum Lorem Ipsum vLorem Ipsum Lorem Ipsum");
        Assert.assertNull(jsonPath.get("username"));

    }
}
