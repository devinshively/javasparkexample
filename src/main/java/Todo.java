import com.mongodb.BasicDBObject;
import lombok.Data;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by dshively on 6/11/15.
 */
@Data
public class Todo {
    private String id;
    private String title;
    private boolean done;
    private Date createdOn = new Date();

    public Todo(Document dbObject) {
        this.id = ((ObjectId) dbObject.get("_id")).toString();
        this.title = dbObject.getString("title");
        this.done = dbObject.getBoolean("done");
        this.createdOn = dbObject.getDate("createdOn");
    }
}
