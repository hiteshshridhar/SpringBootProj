package com.example.SpringBootProject.controller;

import com.example.SpringBootProject.DTO.TaskDTO;
import com.example.SpringBootProject.entities.TaskEntity;
import com.example.SpringBootProject.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("")

    public ResponseEntity <List<TaskEntity>> getTask(){
        var task = taskService.getTaskList();
        return ResponseEntity.ok(task);
    }

    @GetMapping("{id}")
    public ResponseEntity<TaskEntity> getTaskById( @PathVariable("id") Integer id){
        var task = taskService.getTaskById(id);
        if(task == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @PostMapping("")
    public ResponseEntity<TaskEntity> addTask(@RequestBody TaskDTO body){
        var task = taskService.addTask(body.getName(), body.getDescription());
        return ResponseEntity.ok(task);
    }
}
