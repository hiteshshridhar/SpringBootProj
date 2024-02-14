package com.example.SpringBootProject.service;


import com.example.SpringBootProject.entities.NotesEntity;
import com.example.SpringBootProject.entities.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class NotesService {
    private final TaskService taskService;
    private final static HashMap<Integer, TaskNotesHolder> taskNotesHolder = new HashMap<>();

    @Autowired
    public NotesService(TaskService taskService) {
        this.taskService = taskService;
    }

    static class TaskNotesHolder {
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

        taskNotesHolder.putIfAbsent(taskId, new TaskNotesHolder());
        TaskNotesHolder holder = taskNotesHolder.get(taskId);

        NotesEntity newNote = new NotesEntity();
        newNote.setId(holder.noteId);
        newNote.setTitle(title);
        newNote.setDescription(description);

        holder.notesList.add(newNote);
        holder.noteId++;
        System.out.println(holder.notesList);
        return newNote;
    }

    //Todo:
    public List<NotesEntity> updateNotesForTask(int taskId, String title, String description) {
        TaskEntity task = taskService.getTaskById(taskId);

        List<NotesEntity> note = getNotesForTask(taskId);

        if(task == null || note == null){
            return  null;
        }

       note.get(0).setTitle("hello hello");

       return note;
    }

    //Todo:
    public void deleteNotes() {
    }


}
