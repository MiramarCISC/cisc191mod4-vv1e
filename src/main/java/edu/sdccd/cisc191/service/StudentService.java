package edu.sdccd.cisc191.service;

import edu.sdccd.cisc191.model.Student;
import edu.sdccd.cisc191.repository.StudentRepository;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Cannot save a null Student");
        }

        repository.save(student);
    }

    @Nullable
    public Student getStudent(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Student IDs cannot be negative.");
        }

        return repository.findById(id);
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public void changeGpa(int id, double newGpa) {
        if (id <= 0) {
            throw new IllegalArgumentException("Student IDs cannot be negative.");
        }

        if (newGpa < 0.0 || newGpa > 4.0) {
            throw new IllegalArgumentException("Student GPAs must be between 0.0-4.0.");
        }

        repository.updateGpa(id, newGpa);
    }

    public void removeStudent(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Student IDs cannot be negative.");
        }

        repository.deleteById(id);
    }
}
