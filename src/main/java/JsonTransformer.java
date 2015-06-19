import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * Created by dshively on 6/11/15.
 */
public class JsonTransformer implements ResponseTransformer {
    private Gson gson = new Gson();

    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }
}
