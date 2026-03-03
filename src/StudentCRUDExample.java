import java.sql.*;

public class StudentCRUDExample {

    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/school";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "IST888IST888";

    private Connection connection;

    public StudentCRUDExample() {
        try {
            this.connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to MySQL", e);
        }
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // CREATE
    public void create(Student student) {
        String sql = "INSERT INTO students (id, firstName, lastName, age, email, phone) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, student.getId());
            ps.setString(2, student.getFirstName());
            ps.setString(3, student.getLastName());
            ps.setInt(4, student.getAge());
            ps.setString(5, student.getEmail());
            ps.setString(6, student.getPhone());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ (by id)
    public void read(int id) {
        String sql = "SELECT id, firstName, lastName, age, email, phone FROM students WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Student s = new Student(
                            rs.getInt("id"),
                            rs.getString("firstName"),
                            rs.getString("lastName"),
                            rs.getInt("age"),
                            rs.getString("email"),
                            rs.getString("phone")
                    );
                    System.out.println(s);
                } else {
                    System.out.println("MySQL: No student found with id=" + id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UPDATE (by id)
    public void update(Student student) {
        String sql = "UPDATE students SET firstName = ?, lastName = ?, age = ?, email = ?, phone = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setInt(3, student.getAge());
            ps.setString(4, student.getEmail());
            ps.setString(5, student.getPhone());
            ps.setInt(6, student.getId());
            int rows = ps.executeUpdate();
            if (rows == 0) {
                System.out.println("MySQL: Update did nothing (no matching id=" + student.getId() + ")");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE (by id)
    public void delete(int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows == 0) {
                System.out.println("MySQL: Delete did nothing (no matching id=" + id + ")");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}