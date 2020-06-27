package com.project.books.domain.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditBookCommand {

    private Long id;
    private String title;
    private String authorName;

}
