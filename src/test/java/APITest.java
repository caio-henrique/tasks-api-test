import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;

public class APITest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080/tasks-backend";
    }

    @Test
    public void deveRetornarTerefas() {

        RestAssured
                .given()
                .when()
                    .get("/todo")
                .then()
                    .statusCode(200);
    }

    @Test
    public void deveAdicionarTarefaComSucesso() {

        RestAssured
                .given()
                    .body("{ \"task\": \"Test via API\", \"dueDate\": \"2021-12-30\" }")
                    .contentType(ContentType.JSON)
                .when()
                    .post("/todo")
                .then()
                    .statusCode(201);
    }

    @Test
    public void naoDeveAdicionarTarefaInvalida() {

        RestAssured
                .given()
                    .body("{ \"task\": \"Test via API\", \"dueDate\": \"2020-12-30\" }")
                    .contentType(ContentType.JSON)
                .when()
                    .post("/todo")
                .then()
                    .statusCode(400)
                    .body("message", CoreMatchers.is("Due date must not be in past"));
    }
}


