package edu.sdccd.cisc191.model;

public class Student {
    private final int id;
    private String name;
    private double gpa;

    public Student(int id, String name, double gpa) {
        if (id <= 0) {
            throw new IllegalArgumentException("Student IDs must be positive.");
        }
        this.id = id;

        // Methods are already defined below.
        setName(name);
        setGpa(gpa);
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public double getGpa() {
        return this.gpa;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student Names must not be null or blank.");
        } else if (name.length() > 100) {
            // student.name is VARCHAR(100)
            throw new IllegalArgumentException("Student Names cannot exceed 100 characters.");
        }

        this.name = name;
    }

    public void setGpa(double gpa) {
        if (gpa < 0.0 || gpa > 4.0) {
            throw new IllegalArgumentException("Student GPAs must be between 0.0-4.0.");
        }

        this.gpa = gpa;
    }

    @Override
    public String toString() {
        // Ex: Student{id=23, name='John', gpa=3.44}
        return String.format(
            "%s{id=%d, name='%s', gpa=%.2f}",
            this.getClass().getSimpleName(), this.id, this.name, this.gpa
        );
    }
}
