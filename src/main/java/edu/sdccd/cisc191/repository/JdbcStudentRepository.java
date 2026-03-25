package edu.sdccd.cisc191.repository;

import edu.sdccd.cisc191.model.Student;
import edu.sdccd.cisc191.util.DatabaseConfig;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcStudentRepository implements StudentRepository {

    @Override
    public void save(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("A null Student cannot be saved.");
        }

        try (Connection connection = DatabaseConfig.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO students VALUES (?, ?, ?)"
            );

            ps.setInt(1, student.getId());
            ps.setString(2, student.getName());
            ps.setDouble(3, student.getGpa());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Nullable
    public Student findById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Student IDs must be positive.");
        }

        try (Connection connection = DatabaseConfig.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM students WHERE id = ?"
            );

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.first()) {
                return new Student(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getDouble(3)
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Student> findAll() {
        ArrayList<Student> studentList = new ArrayList<>();

        try (Connection connection = DatabaseConfig.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM students"
            );

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                studentList.add(
                    new Student(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3)
                    )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return studentList;
    }

    @Override
    public void updateGpa(int id, double newGpa) {
        if (id <= 0) {
            throw new IllegalArgumentException("Student IDs must be positive.");
        }

        if (newGpa < 0.0 || newGpa > 4.0) {
            throw new IllegalArgumentException("Student GPAs must be between 0.0-4.0.");
        }

        try (Connection connection = DatabaseConfig.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                "UPDATE students SET gpa = ? WHERE id = ?"
            );

            ps.setDouble(1, newGpa);
            ps.setInt(2, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Student IDs must be positive.");
        }

        try (Connection connection = DatabaseConfig.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                "DELETE FROM students WHERE id = ?"
            );

            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
