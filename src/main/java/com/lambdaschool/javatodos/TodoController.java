package com.lambdaschool.javatodos;


import com.lambdaschool.javatodos.models.Todo;
import com.lambdaschool.javatodos.models.Users;
import com.lambdaschool.javatodos.repositories.TodoRepository;
import com.lambdaschool.javatodos.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = {}, produces = MediaType.APPLICATION_JSON_VALUE)
public class TodoController {

    @Autowired
    UsersRepository usersRepo;

    @Autowired
    TodoRepository todoRepo;

    // ------------Users Mapping------------

    @GetMapping("/users")
    public List<Users> allUsers() {
        return usersRepo.findAll();
    }

    @GetMapping("/users/userid/{userid}")
    public Users getUserByID(@PathVariable int userid) {
        Optional<Users> usersById = usersRepo.findById(userid);
        if (usersById.isPresent()) {
            return usersById.get();
        } else {
            return null;
        }
    }

    @GetMapping("/users/username/{username}")
    public Users getUserByID(@PathVariable String username) {
        return usersRepo.findByUsernameIgnoreCase(username);
    }


    // ------------Todos Mapping------------

    @GetMapping("/todos")
    public List<Todo> allTodos() {
        return todoRepo.findAll();
    }

    @GetMapping("/todos/todoid/{todoid}")
    public Todo getTodoByID(@PathVariable int todoid) {
        Optional<Todo> todoById = todoRepo.findById(todoid);
        if (todoById.isPresent()) {
            return todoById.get();
        } else {
            return null;
        }
    }

    @GetMapping("/todos/users")
    public List<Object[]> getTodosByUser() {
        return todoRepo.userWithTodos();
    }

}
