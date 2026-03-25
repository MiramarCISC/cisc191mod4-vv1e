package edu.sdccd.cisc191.repository;

import edu.sdccd.cisc191.model.Course;
import edu.sdccd.cisc191.util.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcCourseRepository implements CourseRepository {

    @Override
    public void save(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("A null Course cannot be saved.");
        }

        try (Connection connection = DatabaseConfig.getConnection())  {
            PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO courses VALUES (?, ?, ?)"
            );

            ps.setInt(1, course.getId());
            ps.setString(2, course.getTitle());
            ps.setInt(3, course.getStudentId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Course> findByStudentId(int studentId) {
        if (studentId <= 0) {
            throw new IllegalArgumentException("Student IDs must be positive");
        }

        ArrayList<Course> coursesList = new ArrayList<>();

        try (Connection connection = DatabaseConfig.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                "SELECT * from courses WHERE student_id = ?"
            );

            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                coursesList.add(
                    new Course(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)
                    )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return coursesList;
    }

    @Override
    public List<Course> findAll() {
        ArrayList<Course> coursesList = new ArrayList<>();

        try (Connection connection = DatabaseConfig.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(
                "SELECT * from courses"
            );

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                coursesList.add(
                    new Course(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)
                    )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return coursesList;
    }
}
