import java.sql.*;

public class StudentManagementSystem {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/student_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "abcd";

    private final Connection conn;

    public StudentManagementSystem() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        System.out.println("Connected to database.");
    }

    public boolean studentExists(int id) {
        String sql = "SELECT id FROM students WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Error checking student existence: " + e.getMessage());
            return false;
        }
    }

    public void addStudent(int id, String name, int age, String course) {

        if (studentExists(id)) {
            System.out.println("Student with ID " + id + " already exists.");
            return;
        }

        String sql = "INSERT INTO students (id, name, age, course) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.setInt(3, age);
            stmt.setString(4, course);
            stmt.executeUpdate();
            System.out.println("Student added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    public void viewStudents() {
        String sql = "SELECT * FROM students";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\nID | Name | Age | Course");
            System.out.println("-------------------------------");
            while (rs.next()) {
                System.out.printf("%d | %s | %d | %s\n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("course"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving students: " + e.getMessage());
        }
    }

    public void updateStudent(int id, String name, int age, String course) {
        String sql = "UPDATE students SET name = ?, age = ?, course = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.setString(3, course);
            stmt.setInt(4, id);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Student updated successfully.");
            } else {
                System.out.println("Student not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating student: " + e.getMessage());
        }
    }

    public void deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Student not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting student: " + e.getMessage());
        }
    }

    public void searchByName(String name) {
        String sql = "SELECT * FROM students WHERE name LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();
            printResultSet(rs);
        } catch (SQLException e) {
            System.out.println("Error searching students: " + e.getMessage());
        }
    }

    public void searchByCourse(String course) {
        String sql = "SELECT * FROM students WHERE course LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + course + "%");
            ResultSet rs = stmt.executeQuery();
            printResultSet(rs);
        } catch (SQLException e) {
            System.out.println("Error searching students: " + e.getMessage());
        }
    }

    private void printResultSet(ResultSet rs) throws SQLException {
        System.out.println("\nID | Name | Age | Course");
        System.out.println("-------------------------------");
        boolean found = false;
        while (rs.next()) {
            found = true;
            System.out.printf("%d | %s | %d | %s\n",
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("course"));
        }
        if (!found) {
            System.out.println("No matching students found.");
        }
    }

    public void closeConnection() {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }
}
