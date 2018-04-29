import org.junit.Test;
import java.util.ArrayList;
import java.util.Collections;
import static io.restassured.RestAssured.*;

public class JSONPlaceholder {

    @Test
    public void step_1() {

        ArrayList<Integer> userIds =
                given().
                when().
                   get("http://jsonplaceholder.typicode.com/posts").
                then().
                    extract().path("userId");

        Integer max_uderId = Collections.max(userIds);
        System.out.print(max_uderId);
    }

    @Test
    public void step_2() {

    }
}