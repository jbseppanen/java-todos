package com.lambdaschool.javatodos.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

@Data @NoArgsConstructor
@Entity
@Table(name = "todo")
public class Todo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int todoid;

    private String description;

    private String datestarted;

    private int completed;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private Users user;

}
