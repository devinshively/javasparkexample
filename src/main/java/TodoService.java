import com.google.gson.Gson;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dshively on 6/11/15.
 */
public class TodoService {

    private final MongoDatabase db;
    private final MongoCollection<Document> collection;

    public TodoService(MongoDatabase db) {
        this.db = db;
        this.collection = db.getCollection("todos");
    }

    public List<Todo> findAll() {
        List<Todo> todoList = new ArrayList<>();
        for (Document todo : collection.find()) {
            todoList.add(new Todo(todo));
        }
        return todoList;
    }

    public void createNewTodo(String body) {
        Todo todo = new Gson().fromJson(body, Todo.class);
        collection.insertOne(new Document("title", todo.getTitle()).append("done", todo.isDone()).append("createdOn", new Date()));
    }

    public Todo find(String id) {
        return new Todo(collection.find(new Document("_id", new ObjectId(id))).first());
    }

    public Todo update(String todoId, String body) {
        Todo todo = new Gson().fromJson(body, Todo.class);
        collection.updateOne(new Document("_id", new ObjectId(todoId)), new Document("$set", new Document("done", todo.isDone())));
        return this.find(todoId);
    }
}
