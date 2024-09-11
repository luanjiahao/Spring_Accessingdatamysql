package com.example.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.Optional;

@CrossOrigin//解决前后端域名不同问题 localhost:8080 & localhost:3000
@Controller//控制器
@RequestMapping("/Todo")
public class TodoController {
    @Autowired//自动生成
    private TodoRepository todoRepository;

    @PostMapping("add")
    public @ResponseBody String addTodo(@RequestParam String name, @RequestParam boolean isCompleted) {
        Todo newTodo = new Todo();
        newTodo.setName(name);
        newTodo.setCompleted(isCompleted);
        todoRepository.save(newTodo);
        return "saved";
    }

    @PostMapping("delete")
    public @ResponseBody String deleteTodo(@RequestParam Integer id) {

        todoRepository.deleteById(id);
        return "deleted";

    }

    @PostMapping("complete")
    public @ResponseBody String completeTodo(@RequestParam Integer id, @RequestParam boolean complete) {
        Optional<Todo> oldTodo = todoRepository.findById(id);
        if (oldTodo.isPresent()) {
            Todo newTodo = new Todo();
            newTodo.setId(id);
            newTodo.setName(oldTodo.get().getName());
            newTodo.setCompleted(complete);
            todoRepository.save(newTodo);
        } else {
            return "id is not found";
        }
        return "completed";
    }

    @PostMapping("update")
    public @ResponseBody String deleteTodo(@RequestParam Integer id, @RequestParam String name) {
        Optional<Todo> oldTodo = todoRepository.findById(id);
        if (oldTodo.isPresent()) {
            Todo newTodo = new Todo();
            newTodo.setId(id);
            newTodo.setName(name);
            newTodo.setCompleted(oldTodo.get().isCompleted());
            todoRepository.save(newTodo);
        } else {
            return "id is not found";
        }
        return "updated";
    }

    @GetMapping("all")
    public @ResponseBody Iterable<Todo> getAllTodos() {
        return todoRepository.findAll();
    }
}
