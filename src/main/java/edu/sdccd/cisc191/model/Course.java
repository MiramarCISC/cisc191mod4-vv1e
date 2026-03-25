package edu.sdccd.cisc191.model;

public class Course {
    private final int id;
    private final String title;
    private final int studentId;

    public Course(int id, String title, int studentId) {
        if (id <= 0) {
            throw new IllegalArgumentException("Course IDs must be positive.");
        }

        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Course Titles must not be null or empty.");
        } else if (title.length() > 100) {
            // course.title is VARCHAR(100)
            throw new IllegalArgumentException("Course Titles cannot exceed 100 characters.");
        }

        if (studentId <= 0) {
            throw new IllegalArgumentException("Courses must be supplied with positive Student IDs.");
        }

        this.id = id;
        this.title = title;
        this.studentId = studentId;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public int getStudentId() {
        return this.studentId;
    }

    @Override
    public String toString() {
        // Ex: Course{id=41, title='Intro', studentId=23}
        return String.format(
            "%s{id=%d, title='%s', studentId=%d}",
            this.getClass().getSimpleName(), this.id, this.title, this.studentId
        );
    }
}
