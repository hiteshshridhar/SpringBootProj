package com.example.SpringBootProject.controller;

import com.example.SpringBootProject.DTO.errorDTO.ErrorResponseDTO;
import com.example.SpringBootProject.DTO.taskDTO.TaskDTO;
import com.example.SpringBootProject.DTO.taskDTO.TaskResponseDTO;
import com.example.SpringBootProject.DTO.taskDTO.UpdateTaskDTO;
import com.example.SpringBootProject.entities.TaskEntity;
import com.example.SpringBootProject.service.NotesService;
import com.example.SpringBootProject.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final NotesService notesService;
    private final ModelMapper modelMapper = new ModelMapper();



    @Autowired
    public TaskController(TaskService taskService, NotesService notesService) {
        this.taskService = taskService;
        this.notesService = notesService;
    }


    @GetMapping("")
    public ResponseEntity <List<TaskEntity>> getTasks(){
        var task = taskService.getTaskList();
        return ResponseEntity.ok(task);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable("id") Integer id){
        var task = taskService.getTaskById(id);
        var notes = notesService.getNotesForTask(id);
        if(task == null){
            return ResponseEntity.notFound().build();
        }
        var taskResponse = modelMapper.map(task, TaskResponseDTO.class);
        taskResponse.setNotes(notes);
        return ResponseEntity.ok(taskResponse);
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
