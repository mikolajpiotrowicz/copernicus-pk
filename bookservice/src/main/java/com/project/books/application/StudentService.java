package com.project.books.application;

import com.project.books.domain.Student;
import com.project.books.domain.StudentRepository;
import com.project.books.domain.command.CreateStudentCommand;
import com.project.books.domain.command.LoginCommand;
import com.project.books.domain.excpetion.LoginException;
import com.project.books.domain.excpetion.PayloadErrorException;
import com.project.books.domain.excpetion.ResourceNotFoundException;
import com.project.books.helpers.Utils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@AllArgsConstructor
public class StudentService {

    private StudentRepository studentRepository;

    public Page<Student> getAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    public boolean checkStudentExists(CreateStudentCommand command) {
        List<Student> students = studentRepository.findAll();
        System.out.println("Command index: " + command.getIndexNumber());
        System.out.println("Command name: " + command.getStudentName());
        System.out.println("Students list size: " + students.size());
        for (Student student : students) {
            System.out.println("Student iterate index: " + student.getIndexNumber());
            System.out.println("Student iterate name: " + student.getIndexNumber());
            if (student.getIndexNumber().equals(command.getIndexNumber())) {
                throw new PayloadErrorException("Student with this index already exist.");
            }
        }
        return false;
    }

    public Student login(LoginCommand command) {
        List<Student> students = studentRepository.findAll();

        for (Student student : students) {
            if (student.getIndexNumber().equals(command.getIndexNumber()) && student.getPassword().equals(command.getPassword())) {
               return student;
            }
        }

        throw new LoginException("Credentials not valid.");
    }


    public Student createStudent(CreateStudentCommand command) {
        checkStudentExists(command);

        return studentRepository.save(
                Student.builder()
                        .indexNumber(command.getIndexNumber())
                        .studentName(command.getStudentName())
                        .password(Utils.generateNewToken())
                        .build()
        );
    }

    public Student getStudent(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    public void removeStudent(Long id) {
        studentRepository.findById(id).ifPresent(studentRepository::delete);
    }
}
