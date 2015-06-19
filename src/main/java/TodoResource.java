import static spark.Spark.*;

/**
 * Created by dshively on 6/11/15.
 */
public class TodoResource {
    private static final String API_CONTEXT = "/api/v1";
    private final TodoService todoService;

    public TodoResource(TodoService todoService) {
        this.todoService = todoService;
        setupEndpoints();
    }

    private void setupEndpoints() {
        before((request, response) -> response.type("application/json"));

        post(API_CONTEXT + "/todos", "application/json", (request, response) -> {
            todoService.createNewTodo(request.body());
            response.status(201);
            return response;
        });

        get(API_CONTEXT + "/todos/:id", "application/json", (request, response) ->
            todoService.find(request.params(":id")), new JsonTransformer()
        );

        get(API_CONTEXT + "/todos", "application/json", (request, response) ->
                todoService.findAll(), new JsonTransformer()
        );

        put(API_CONTEXT + "/todos/:id", "application/json", (request, response) ->
                todoService.update(request.params(":id"), request.body()), new JsonTransformer()
        );
    }
}
