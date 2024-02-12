package com.example.SpringBootProject.controller;

import com.example.SpringBootProject.DTO.notesDTO.NotesDTO;
import com.example.SpringBootProject.DTO.notesDTO.NotesResponseDTO;
import com.example.SpringBootProject.entities.NotesEntity;
import com.example.SpringBootProject.service.NotesService;
import com.example.SpringBootProject.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/tasks/{taskId}/notes")
public class NotesController {

    private final NotesService notesService;
    private TaskService taskService;
    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @GetMapping("")
    public ResponseEntity<List<NotesEntity>>  getAllNotes(@PathVariable("taskId") Integer taskId ){
        var notes = notesService.getNotesForTask(taskId);
        return ResponseEntity.ok(notes);
    }

    @PostMapping("")
    public ResponseEntity<NotesResponseDTO> addNoteForTask(
            @PathVariable("taskId") Integer taskId,
            @RequestBody NotesDTO notesDTO) throws ParseException {
        var note = notesService.addNotesForTask(taskId, notesDTO.getTitle(), notesDTO.getDescription());
        return ResponseEntity.ok(new NotesResponseDTO(taskId,note));
    }
}
