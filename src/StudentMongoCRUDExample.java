import com.mongodb.client.*;
import org.bson.Document;

public class StudentMongoCRUDExample {

    private final MongoClient mongoClient;
    private final MongoCollection<Document> collection;

    public StudentMongoCRUDExample() {
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("school");
        collection = database.getCollection("students");
    }

    public void close() {
        mongoClient.close();
    }

    // CREATE
    public void create(Student student) {
        Document doc = new Document("id", student.getId())
                .append("first_name", student.getFirstName())
                .append("last_name", student.getLastName())
                .append("age", student.getAge())
                .append("email", student.getEmail())
                .append("phone", student.getPhone());

        collection.insertOne(doc);
    }

    // READ (by id)
    public void read(int id) {
        Document doc = collection.find(new Document("id", id)).first();
        if (doc == null) {
            System.out.println("Mongo: No student found with id=" + id);
        } else {
            System.out.println(doc.toJson());
        }
    }

    // UPDATE (by id)
    public void update(Student student) {
        Document filter = new Document("id", student.getId());
        Document set = new Document("first_name", student.getFirstName())
                .append("last_name", student.getLastName())
                .append("age", student.getAge())
                .append("email", student.getEmail())
                .append("phone", student.getPhone());

        collection.updateOne(filter, new Document("$set", set));
    }

    // DELETE (by id)
    public void delete(int id) {
        collection.deleteOne(new Document("id", id));
    }
}