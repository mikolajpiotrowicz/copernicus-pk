package com.project.books.api;

import com.project.books.application.StudentService;
import com.project.books.domain.Student;
import com.project.books.domain.command.LoginCommand;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RestController
@AllArgsConstructor
@RequestMapping("/login")
public class LoginController {
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> login(@RequestBody LoginCommand loginCommand) {
        System.out.println("Login, index " + loginCommand.getIndexNumber() + "  password: " + loginCommand.getPassword());
        return ResponseEntity.ok(studentService.login(loginCommand));
    }
}
