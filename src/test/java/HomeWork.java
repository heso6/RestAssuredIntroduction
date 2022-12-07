import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class HomeWork extends TestBase {

//    Ćwiczenia dla https://jsonplaceholder.typicode.com/todos
//
//Testy API do napisania:
//
//1. Pobierz wszystkie 'todos'
//
//-sprawdz czy status to 200
//2. Pobierz 'todo' o id 4
//
//-sprawdz czy status to 200
//-sprawdz asercją czy reponsponse zawiera id=1
//3. Pobierz 'todos', które były stworzone przez usera o userId = 1
//
//-sprawdz czy status to 200
//4. Pobierz 'todos', które były stworzone przez usera o userId = 1 oraz mają status completed = 1
//
//-sprawdz czy status to 200
//5. Stwórz 'todo' o danych: userId = 9, title = I should do the homework, completed = true
//
//-sprawdz czy status to 201
//-sprawdz asercją czy reponsponse zawiera odpowiednie: userId, title, completed
//
//Ćwiczenia dla https://jsonplaceholder.typicode.com/users
//
//6. Pobierz 'userów', którzy pracują w w firmie o nazwie Deckow-Crist
//-sprawdz czy status to 201
//-sprawdz asercją czy w tablicy obiektów otrzymanych w response, pierwszy obiekt ma nazwę company równą Deckow-Crist

    private String todos = "/todos";
    private String users = "/users";

    String body = "{\n" +
            "    \"userId\": 9,\n" +
            "    \"title\": \"I should do the homework\",\n" +
            "    \"completed\": \"true\"\n" +
            "}";

    //    Zadanie 1
    @Test
    public void getAllToDos() {
        when()
                .get(base_Url + todos)
                .then()
                .statusCode(200);
    }

    //    Zadanie 2
    @Test
    public void getTODoWithId4() {
        Response response =
                given()
                        .queryParam("id", "4")
                        .when()
                        .get(base_Url + todos)
                        .then()
                        .statusCode(200)
                        .extract().response();

        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("[0].userId").toString(), "1");
    }

    //    Zadanie 3
    @Test
    public void getTODosWithUserId1() {
        given()
                .queryParam("userId", "1")
                .when()
                .get(base_Url + todos)
                .then()
                .statusCode(200);
    }

    //    Zadanie 4
    @Test
    public void getTODosWithUserIdAndStatusCompletedTrue() {
        given()
                .queryParam("userId", "1")
                .queryParam("completed", "true")
                .when()
                .get(base_Url + todos)
                .then()
                .statusCode(200);
    }

    //    Zadanie 5
    @Test
    public void createNewPostAndCheckAssert() {
        Response response =
                RestAssured.given()
                        .body(body)
                        .contentType(ContentType.JSON)
                        .when()
                        .post(base_Url + todos)
                        .then()
                        .statusCode(201)
                        .extract().response();

        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("userId").toString(), "9");
        Assert.assertEquals(jsonPath.get("title").toString(), "I should do the homework");
        Assert.assertEquals(jsonPath.get("completed").toString(), "true");
    }

    //    Zadanie 6
    @Test
    public void getAllEmployeesFromDeckowCrist() {
        Response response =
                given()
                        .queryParam("company.name", "Deckow-Crist")
                        .when()
                        .get(base_Url + users)
                        .then()
//                        tu chyba powinien być oczekiwany status 200?? 201 jest przy tworzeniu elementów.
//                        .statusCode(201)
                        .statusCode(200)
                        .extract().response();

        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("[0].company.name").toString(), "Deckow-Crist");


    }
}

