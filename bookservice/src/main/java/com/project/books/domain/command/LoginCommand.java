package com.project.books.domain.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginCommand {
    private String indexNumber;
    private String password;
}
