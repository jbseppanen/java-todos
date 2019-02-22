package com.lambdaschool.javatodos;


import com.lambdaschool.javatodos.models.Todo;
import com.lambdaschool.javatodos.models.Users;
import com.lambdaschool.javatodos.repositories.TodoRepository;
import com.lambdaschool.javatodos.repositories.UsersRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Api(value = "Java Orders Application", description = "Java Orders with Swagger")
@ApiResponses(value =
        {
                @ApiResponse(code = 200, message = "Successfully retrieved list"),
                @ApiResponse(code = 401, message = "Not authorized for this resource"),
                @ApiResponse(code = 403, message = "Access to resource forbidden"),
                @ApiResponse(code = 404, message = "Resource not found")
        })
@RestController
@RequestMapping(path = {}, produces = MediaType.APPLICATION_JSON_VALUE)
public class TodoController {

    @Autowired
    UsersRepository usersRepo;

    @Autowired
    TodoRepository todoRepo;

    // ------------Users Mapping------------
    @ApiOperation(value = "Returns a list of all users.", response = List.class)
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

    @PostMapping("/users")
    public Users newCustomer(@RequestBody Users newUser) throws URISyntaxException {
        return usersRepo.save(newUser);
    }

    @PutMapping("/users/userid/{userid}")
    public Users updateCustomer(@RequestBody Users UserToUpdate, @PathVariable int userid) throws URISyntaxException {
        Optional<Users> foundUser = usersRepo.findById(userid);
        if (foundUser.isPresent()) {
            UserToUpdate.setUserid(userid);
            usersRepo.save(UserToUpdate);
            return UserToUpdate;
        } else {
            return null;
        }
    }

    @DeleteMapping("/users/userid/{userid}")
    public Users deleteUserByID(@PathVariable int userid) {
        Optional<Users> foundUser = usersRepo.findById(userid);
        if (foundUser.isPresent()) {
            List<Todo> todosFromUser = todoRepo.getByUser_Userid(userid);
            for (Todo todo : todosFromUser) {
                todoRepo.deleteById(todo.getTodoid());
            }
            usersRepo.deleteById(userid);
            return foundUser.get();
        } else {
            return null;
        }
    }

    // ------------Todos Mapping------------
    @ApiOperation(value = "Returns a list of all todos.", response = List.class)
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
    @ApiOperation(value = "Returns a list of all users (denoted by user name and all the todos associated with each user.", response = List.class)
    @GetMapping("/todos/users")
    public List<Object[]> getTodosByUser() {
        return todoRepo.userWithTodos();
    }
    @ApiOperation(value = "Returns a list of all todos that are not complete.", response = List.class)
    @GetMapping("todos/active")
    public List<Todo> activeTodos() {
        return todoRepo.getByCompletedEquals(0);
    }

    @PostMapping("/todos")
    public Todo newTodo(@RequestBody Todo newTodo) throws URISyntaxException {
        return todoRepo.save(newTodo);
    }

    @ApiOperation(value = "Updates a single supplied todo based on supplied todo id {todoid} in the database " +
            "and returns that todo if it is found.  If not found in database, returns null", response = Users.class)
    @PutMapping("/todos/todoid/{todoid}")
    public Todo updateTodo(@RequestBody Todo todoToUpdate, @PathVariable int todoid) throws URISyntaxException {
        Optional<Todo> foundTodo = todoRepo.findById(todoid);
        if (foundTodo.isPresent()) {
            todoToUpdate.setTodoid(todoid);
            todoRepo.save(todoToUpdate);
            return todoToUpdate;
        } else {
            return null;
        }
    }
    @ApiOperation(value = "Deletes a single todo from database based on supplied todo id {todoid} and returns" +
            " that todo if found.  If not found, returns null.", response = Todo.class)
    @DeleteMapping("/todos/todoid/{todoid}")
    public Todo deleteTodoById(@PathVariable int todoid) {
        Optional<Todo> foundTodo = todoRepo.findById(todoid);
        if (foundTodo.isPresent()) {
            todoRepo.deleteById(todoid);
            return foundTodo.get();
        } else {
            return null;
        }

    }

}
