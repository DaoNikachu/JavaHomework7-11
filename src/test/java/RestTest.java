import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

public class RestTest {
    private static final String BASE_URL = "https://postman-echo.com";
    private static final String RAW_TEXT = "This is expected to be sent back as part of response body.";

    @BeforeClass
    static void setUp(){
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void getRequest() {
        given()
                .basePath("/get")
                .contentType(ContentType.JSON)
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
        .when()
                .get()
        .then()
                .statusCode(200)
                .body("args", equalTo(Map.of("foo1", "bar1", "foo2", "bar2")))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("url", equalTo("https://postman-echo.com/get?foo1=bar1&foo2=bar2"));
    }
    @Test
    public void postRawText() {
        given()
                .contentType("text/plain")
                .body(RAW_TEXT)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("args", equalTo(Map.of()))
                .body("data", equalTo(RAW_TEXT))
                .body("files", equalTo(Map.of()))
                .body("form", equalTo(Map.of()))
                .body("json", nullValue())
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.content-type", equalTo("text/plain; charset=ISO-8859-1"))
                .body("url", equalTo(BASE_URL + "/post"));
    }
    @Test
    public void postFormData() {
        given()
                .multiPart("foo1", "bar1")
                .multiPart("foo2", "bar2")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("args", equalTo(Map.of()))
                .body("data", equalTo(Map.of()))
                .body("files", equalTo(Map.of()))
                .body("form", equalTo(Map.of("foo1", "bar1", "foo2", "bar2")))
                .body("json", nullValue())
                .body("headers.host", equalTo("postman-echo.com"))
                .body("url", equalTo(BASE_URL + "/post"));
    }
    @Test
    void putRequest() {
        given()
                .contentType("text/plain")
                .body(RAW_TEXT)
                .when()
                .put("/put")
                .then()
                .statusCode(200)
                .body("args", equalTo(Map.of()))
                .body("data", equalTo(RAW_TEXT))
                .body("files", equalTo(Map.of()))
                .body("form", equalTo(Map.of()))
                .body("json", nullValue())
                .body("headers.host", equalTo("postman-echo.com"))
                .body("url", equalTo(BASE_URL + "/put"));
    }


    @Test
    void patchRequest() {
        given()
                .contentType("text/plain")
                .body(RAW_TEXT)
                .when()
                .patch("/patch")
                .then()
                .statusCode(200)
                .body("args", equalTo(Map.of()))
                .body("data", equalTo(RAW_TEXT))
                .body("files", equalTo(Map.of()))
                .body("form", equalTo(Map.of()))
                .body("json", nullValue())
                .body("headers.host", equalTo("postman-echo.com"))
                .body("url", equalTo(BASE_URL + "/patch"));
    }


    @Test
    void deleteRequest() {
        given()
                .contentType("text/plain")
                .body(RAW_TEXT)
                .when()
                .delete("/delete")
                .then()
                .statusCode(200)
                .body("args", equalTo(Map.of()))
                .body("data", equalTo(RAW_TEXT))
                .body("files", equalTo(Map.of()))
                .body("form", equalTo(Map.of()))
                .body("json", nullValue())
                .body("headers.host", equalTo("postman-echo.com"))
                .body("url", equalTo(BASE_URL + "/delete"));
    }
}
