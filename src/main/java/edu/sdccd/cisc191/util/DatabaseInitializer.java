package edu.sdccd.cisc191.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void initialize() {
        try (Connection connection = DatabaseConfig.getConnection()) {
            Statement statement = connection.createStatement();

            statement.addBatch(
                """
                CREATE TABLE IF NOT EXISTS students (
                    id INT PRIMARY KEY,
                    name VARCHAR(100) NOT NULL,
                    gpa DOUBLE NOT NULL
                )"""
            );

            statement.addBatch(
                """
                CREATE TABLE IF NOT EXISTS courses (
                    id INT PRIMARY KEY,
                    title VARCHAR(100) NOT NULL,
                    student_id INT,
                    FOREIGN KEY (student_id) REFERENCES students(id)
                        ON DELETE CASCADE
                )"""
            );

            statement.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
