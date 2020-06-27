package com.project.books.domain.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStudentCommand {
    private String indexNumber;
    private String studentName;
}
