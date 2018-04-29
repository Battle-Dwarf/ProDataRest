import org.junit.Test;
import java.util.ArrayList;
import java.util.Collections;
import static io.restassured.RestAssured.*;

public class JSONPlaceholder {


    @Test
    public void testScenario() {

        /* Step I */
        ArrayList<Integer> userIds =
                given().
                when().
                    get("http://jsonplaceholder.typicode.com/posts").
                then().
                    extract().path("userId");

        Integer max_userId = Collections.max(userIds);
        System.out.print(max_userId + "\n");

        /* Step II */
        ArrayList<Integer> ids =
                given().
                    pathParam("userId", max_userId).
                when().
                    get("http://jsonplaceholder.typicode.com/posts?{userId}").
                then().
                    extract().path("id");

        Integer max_Id = Collections.max(ids);
        System.out.print(max_Id);

        /* Step III */

    }
}