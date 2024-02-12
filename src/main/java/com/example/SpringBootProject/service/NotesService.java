package com.example.SpringBootProject.service;

import com.example.SpringBootProject.DTO.notesDTO.NotesDTO;
import com.example.SpringBootProject.entities.NotesEntity;
import com.example.SpringBootProject.entities.TaskEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class NotesService {
    private final TaskService taskService;
    private HashMap<Integer, TaskNotesHolder> taskNotesHolder = new HashMap<>();

    public NotesService(TaskService taskService) {
        this.taskService = taskService;
    }

    class TaskNotesHolder {
        protected int noteId = 1;
        protected List<NotesEntity> notesList = new ArrayList<>();
    }

    public List<NotesEntity> getNotesForTask(int taskId) {
        TaskEntity task = taskService.getTaskById(taskId);
        if(task == null){
            return null;
        }

        if(taskNotesHolder.get(taskId)== null){
            taskNotesHolder.put(taskId, new TaskNotesHolder());
        }
        return taskNotesHolder.get(taskId).notesList;
    }

    public NotesEntity addNotesForTask(int taskId, String title, String description) {
        TaskEntity getTask = taskService.getTaskById(taskId);

        if (getTask == null) {
            return null;
        }

        TaskNotesHolder holder = new TaskNotesHolder();

        taskNotesHolder.putIfAbsent(taskId, holder);

        NotesEntity newNote = new NotesEntity();
        newNote.setId(holder.noteId);
        newNote.setTitle(title);
        newNote.setDescription(description);

        holder.notesList.add(newNote);
        holder.noteId++;
        return newNote;
    }

    public NotesEntity updateNotes() {
        return null;
    }

    public void deleteNotes() {
    }


}
