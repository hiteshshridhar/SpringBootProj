package com.example.SpringBootProject.controller;

import com.example.SpringBootProject.DTO.notesDTO.NotesDTO;
import com.example.SpringBootProject.DTO.notesDTO.NotesResponseDTO;
import com.example.SpringBootProject.DTO.notesDTO.UpdateNotesDTO;
import com.example.SpringBootProject.model.NotesModel;
import com.example.SpringBootProject.service.NotesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tasks/{taskId}/notes")
public class NotesController {

    private final NotesService notesService;
    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @GetMapping("")
    public ResponseEntity<List<NotesModel>>  getAllNotes(@PathVariable("taskId") Integer taskId ){
        var notes = notesService.getNotesForTask(taskId);
        return ResponseEntity.ok(notes);
    }

    @PostMapping("")
    public ResponseEntity<NotesResponseDTO> addNoteForTask(
            @PathVariable("taskId") Integer taskId,
            @RequestBody NotesDTO notesDTO) {
        var note = notesService.addNotesForTask(taskId, notesDTO.getTitle(), notesDTO.getDescription());
        return ResponseEntity.ok(new NotesResponseDTO(taskId,note));
    }


    @PutMapping("/{id}")
    public ResponseEntity<NotesModel> updateNotesForTask(
            @PathVariable("taskId") Integer taskId,
            @PathVariable("id") Integer id,
            @RequestBody UpdateNotesDTO body) throws Exception{
        var note = notesService.updateNotesForTask(taskId, id, body.getTitle(), body.getDescription());
        if(note == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(note);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<NotesModel> deleteNoteForTask(@PathVariable("taskId") Integer taskId,
                                  @PathVariable("id")Integer id) {
        try{
            NotesModel note = notesService.deleteNotes(taskId, id);
            return ResponseEntity.ok(note);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}
