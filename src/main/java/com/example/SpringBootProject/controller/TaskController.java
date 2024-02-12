package com.example.SpringBootProject.controller;

import com.example.SpringBootProject.DTO.errorDTO.ErrorResponseDTO;
import com.example.SpringBootProject.DTO.taskDTO.TaskDTO;
import com.example.SpringBootProject.DTO.taskDTO.UpdateTaskDTO;
import com.example.SpringBootProject.entities.TaskEntity;
import com.example.SpringBootProject.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("")
    public ResponseEntity <List<TaskEntity>> getTasks(){
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
    public ResponseEntity<TaskEntity> addTask(@RequestBody TaskDTO body) throws ParseException {
        var task = taskService.addTask(body.getName(), body.getDescription(), body.getDeadline(), body.getCreatedAt());
        return ResponseEntity.ok(task);
    }

    @PutMapping("{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable Integer id, @RequestBody UpdateTaskDTO body) throws Exception {
        var task = taskService.updateTask(id, body);
        if( task == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> errorHandler(Exception e){
        if(e instanceof ParseException){
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("Invalid Date Format"));
        }
        return ResponseEntity.internalServerError().body(new ErrorResponseDTO("Internal Server Error."));
    }

}
