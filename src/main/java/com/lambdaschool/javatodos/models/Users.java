package com.lambdaschool.javatodos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data @NoArgsConstructor
@Entity
@Table(name = "users")
public class Users {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userid;
    private String username;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    private Set<Todo> todos;
}
