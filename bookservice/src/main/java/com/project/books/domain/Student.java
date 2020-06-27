package com.project.books.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Getter
@Entity
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {

    private static final long serialVersionUID = -2768594609931L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "indexNumber", unique = true)
    private String indexNumber;

    @Setter
    @Column(name = "studentName")
    private String studentName;

    @Setter
    @Column(name = "password")
    private String password;

}
