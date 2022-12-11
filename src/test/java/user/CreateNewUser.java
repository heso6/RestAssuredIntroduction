package user;

import base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateNewUser extends TestBase {

    String body = "{\n" +
            "    \"name\": \"Jan Kowalski\",\n" +
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

    @Test
    public void shouldCreateNewUser() {
        Response response =
        RestAssured.given()
                .body(body)
                .contentType(ContentType.JSON)
                .when()
                .post(base_Url + users)
                .then()
                .statusCode(201)
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("username"), "Kowal");
    }
}
