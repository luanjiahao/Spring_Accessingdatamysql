package com.example.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;

@CrossOrigin
@Controller
@RequestMapping("/Todo")
public class TodoController {
    @Autowired//自动生成
    private TodoRepository todoRepository;

    @PostMapping("add")
    public @ResponseBody String addTodo(@RequestParam  String name, @RequestParam boolean isCompleted){
        Todo newTodo=new Todo();
        newTodo.setName(name);
        newTodo.setCompleted(isCompleted);
        todoRepository.save(newTodo);
        return "saved";
    }

    @GetMapping("all")
    public @ResponseBody Iterable<Todo> getAllTodos(){
        return todoRepository.findAll();
    }
}
