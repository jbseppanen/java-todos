package com.lambdaschool.javatodos;


import com.lambdaschool.javatodos.models.Users;
import com.lambdaschool.javatodos.repositories.TodoRepository;
import com.lambdaschool.javatodos.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = {}, produces = MediaType.APPLICATION_JSON_VALUE)
public class TodoController {

    @Autowired
    UsersRepository usersRepo;

    @Autowired
    TodoRepository todoRepo;

    @GetMapping("/users")
    public List<Users> allUsers() {
        return usersRepo.findAll();
    }

}
