package com.project.books.domain.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBookCommand {
    private String title;
    private String authorName;
}
