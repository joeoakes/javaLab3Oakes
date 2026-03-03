public class Main {
    public static void main(String[] args) {
        Student student1 = new Student(1, "John", "Doe", 20, "john@example.com", "9876543211");

        StudentCRUDExample  mysql = new StudentCRUDExample();
        StudentMongoCRUDExample mongo = new StudentMongoCRUDExample();

        mysql.create(student1);
        mongo.create(student1);

        mysql.read(1);
        mongo.read(2);

        student1.setEmail("newjohn@example.com");
        mysql.update(student1);
        mongo.update(student1);

        mysql.delete(3);
        mongo.delete(3);
    }
}