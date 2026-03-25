package edu.sdccd.cisc191.app;

import edu.sdccd.cisc191.model.Course;
import edu.sdccd.cisc191.model.Student;
import edu.sdccd.cisc191.repository.CourseRepository;
import edu.sdccd.cisc191.repository.JdbcCourseRepository;
import edu.sdccd.cisc191.repository.JdbcStudentRepository;
import edu.sdccd.cisc191.service.StudentService;
import edu.sdccd.cisc191.util.DatabaseInitializer;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DatabaseInitializer.initialize();

        StudentService studentService = new StudentService(new JdbcStudentRepository());
        CourseRepository courseRepository = new JdbcCourseRepository();

        studentService.addStudent(new Student(13, "Hannah", 3.4));
        studentService.addStudent(new Student(46, "Niko", 1.5));
        studentService.addStudent(new Student(32, "Alex", 2.9));

        courseRepository.save(new Course(3, "Math Homework", 13));
        courseRepository.save(new Course(7, "Presentation", 13));
        courseRepository.save(new Course(14, "Chemistry Lab", 32));

        List<Student> studentList = studentService.getAllStudents();
        System.out.println("--- List of All Students: ---");
        studentList.forEach(System.out::println);

        Student studentNiko = studentService.getStudent(46);
        System.out.println("\n--- Student by ID (46) ---");
        System.out.println(studentNiko);

        List<Course> hannahCoursesList = courseRepository.findByStudentId(13);
        System.out.println("\n--- Courses for Student (ID 13) ---");
        hannahCoursesList.forEach(System.out::println);

        Student studentAlexBefore = studentService.getStudent(32);
        studentService.changeGpa(32, 3.75);
        Student studentAlexAfter = studentService.getStudent(32);
        System.out.println("\n--- Updating GPA (ID 32) ---");
        System.out.printf(
            "Before: %s\nAfter: %s\n",
            studentAlexBefore, studentAlexAfter
        );

        studentService.removeStudent(46);
        Student studentNikoAfter = studentService.getStudent(46);
        System.out.println("\n--- Deleting Student (ID 46) ---");
        System.out.printf(
            "Before: %s\nAfter: %s\n",
            studentNiko, studentNikoAfter
        );

        studentList = studentService.getAllStudents();
        List<Course> courseList = courseRepository.findAll();

        System.out.println("\n--- Remaining Students ---");
        studentList.forEach(System.out::println);

        System.out.println("\n--- Remaining Courses ---");
        courseList.forEach(System.out::println);
    }
}
