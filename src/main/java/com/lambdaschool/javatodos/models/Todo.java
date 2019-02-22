package com.lambdaschool.javatodos.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data @NoArgsConstructor
@Entity
@Table(name = "todo")
public class Todo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int todoid;

    private String description;

    private Date datestarted;

    private int completed;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private Users user;
}
