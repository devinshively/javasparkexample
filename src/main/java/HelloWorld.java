import static spark.Spark.*;
/**
 * Created by dshively on 6/9/15.
 */
public class HelloWorld {
    public static void main(String[] args) {
        get("/", (req, res) -> "Hello World!");
    }
}