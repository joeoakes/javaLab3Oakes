public class Main {
    public static void main(String[] args) {
        Student student1 = new Student(1, "John", "Doe", 20, "john@example.com", "9876543211");

        StudentCRUDExample mysql = new StudentCRUDExample();
        StudentMongoCRUDExample mongo = new StudentMongoCRUDExample();

        // CREATE
        mysql.create(student1);
        mongo.create(student1);

        // READ (read the same id you created)
        mysql.read(1);
        mongo.read(1);

        // UPDATE
        student1.setEmail("newjohn@example.com");
        student1.setFirstName("Johnny");
        mysql.update(student1);
        mongo.update(student1);

        // DELETE
        mysql.delete(1);
        mongo.delete(1);

        // cleanup
        mysql.close();
        mongo.close();
    }
}