package com.lambdaschool.javatodos.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data @NoArgsConstructor
@Entity
public class Todo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int todoid;
    private Date datestarted;

    private int completed;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private Users user;
}
