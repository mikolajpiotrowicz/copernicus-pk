package com.project.books.domain.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentBookCommand {

    private Long bookId;
    private String studentIndex;

}
