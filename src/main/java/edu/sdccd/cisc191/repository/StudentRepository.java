package edu.sdccd.cisc191.repository;

import edu.sdccd.cisc191.model.Student;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface StudentRepository {
    void save(Student student);
    @Nullable Student findById(int id);
    List<Student> findAll();
    void updateGpa(int id, double newGpa);
    void deleteById(int id);
}
