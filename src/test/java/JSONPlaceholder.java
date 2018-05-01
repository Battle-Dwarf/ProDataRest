import org.json.simple.JSONObject;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Collections;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class JSONPlaceholder {

    @Test
    public void testScenario() {

        String basePath = "http://jsonplaceholder.typicode.com/posts";

        /* Step I */
        ArrayList<Integer> userIds =
                given().
                when().
                    get(basePath).
                then().
                    extract().path("userId");

        Integer max_userId = Collections.max(userIds);
        System.out.print(max_userId + "\n");

        /* Step II */
        ArrayList<Integer> ids =
                given().
                    pathParam("userId", max_userId).
                when().
                    get(basePath + "?{userId}").
                then().
                    extract().path("id");

        Integer max_Id = Collections.max(ids);
        System.out.print(max_Id + "\n");

        /* Step III */
        JSONObject requestParams = new JSONObject();
        requestParams.put("title", "New post title");
        requestParams.put("body", "New post body");
        requestParams.put("userId", "1000");

        given().
                contentType("application/json; charset=UTF-8").
                body(requestParams.toJSONString()).
                pathParam("postId", max_Id).
        when().
                post(basePath + "/{postId}/comments").
        then().
                statusCode(201).
                body("title", equalTo("New post title")).
                body("body", equalTo("New post body")).
                body("userId", equalTo("1000")).
                body("postId", equalTo("100"))
                .log().body();
    }
}