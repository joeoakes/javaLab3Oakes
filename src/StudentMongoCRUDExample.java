import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import org.bson.Document;

public class StudentMongoCRUDExample {

    MongoCollection<Document> collection;
    public StudentMongoCRUDExample() {
        // Create a MongoClient using the factory method
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            // Access the database and collection
            MongoDatabase database = mongoClient.getDatabase("your_database_name");
            collection = database.getCollection("students");
        }
    }

        public void create(Student student) {
            // Example: Insert a document
            Document newStudent = new Document("first_name", "John")
                    .append("last_name", "Doe")
                    .append("age", 20)
                    .append("email", "john@example.com");
            collection.insertOne(newStudent);
        }

        public void read(int id) {
            FindIterable<Document> students = collection.find();
            for (Document student : students) {
                System.out.println(student.toJson());
            }
        }

        public void update(Student student) {
            Document updatedStudent = new Document("$set", new Document("first_name", "Updated First Name"));
            collection.updateOne(new Document("first_name", "John"), updatedStudent);
        }

        public void delete(int id) {
            collection.deleteOne(new Document("first_name", "John"));
        }
    }
}
