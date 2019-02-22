package com.lambdaschool.javatodos.repositories;

import com.lambdaschool.javatodos.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
   @Query(value = "SELECT users.username, todo.todoid, todo.description, todo.datestarted, todo.completed FROM users" +
           " JOIN todo ON users.userid = todo.userid", nativeQuery = true)
    List<Object[]> userWithTodos();

   List<Todo> getByCompletedEquals(int completed);

   List<Todo> getByUser_Userid(int userid);
}