package com.project.books.api;

import com.project.books.application.StudentService;
import com.project.books.domain.Student;
import com.project.books.domain.command.CreateStudentCommand;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RestController
@AllArgsConstructor
@RequestMapping("/students")
public class StudentController {
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<Page<Student>> getAllStudents(Pageable pageable) {
        return ResponseEntity.ok(studentService.getAllStudents(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudent(id));
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody CreateStudentCommand createStudentCommand) {
        return ResponseEntity.ok(studentService.createStudent(createStudentCommand));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> removeStudent(@PathVariable Long id) {
        studentService.removeStudent(id);
        return ResponseEntity.noContent().build();
    }

}
