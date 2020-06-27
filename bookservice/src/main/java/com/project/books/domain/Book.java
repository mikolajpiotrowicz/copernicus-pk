package com.project.books.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Builder
@Getter
@Entity
@Table(name = "book")
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {
    private static final long serialVersionUID = -2768594609988313113L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "title")
    private String title;

    @Setter
    @Column(name = "author_name")
    private String authorName;

    @Setter
    @Column(name = "rent")
    private boolean rent;

    @Setter
    @Column(name = "studentIndex", unique = true, nullable = false)
    private String studentIndex;

}
