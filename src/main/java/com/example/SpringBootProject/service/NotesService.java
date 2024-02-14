package com.example.SpringBootProject.service;


import com.example.SpringBootProject.model.NotesModel;
import com.example.SpringBootProject.model.TaskEntity;
import lombok.NonNull;
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
        protected List<NotesModel> notesList = new ArrayList<>();
    }

    public List<NotesModel> getNotesForTask(int taskId) {
        TaskEntity task = taskService.getTaskById(taskId);
        if(task == null){
            return null;
        }

        if(taskNotesHolder.get(taskId)== null){
            taskNotesHolder.put(taskId, new TaskNotesHolder());
        }
        return taskNotesHolder.get(taskId).notesList;
    }

    public NotesModel addNotesForTask(int taskId, String title, String description) {
        TaskEntity getTask = taskService.getTaskById(taskId);

        if (getTask == null) {
            return null;
        }

        taskNotesHolder.putIfAbsent(taskId, new TaskNotesHolder());
        TaskNotesHolder holder = taskNotesHolder.get(taskId);

        NotesModel newNote = new NotesModel();
        newNote.setId(holder.noteId);
        newNote.setTitle(title);
        newNote.setDescription(description);

        holder.notesList.add(newNote);
        holder.noteId++;
        System.out.println(holder.notesList);
        return newNote;
    }

    //Todo:
    public NotesModel updateNotesForTask(int taskId, @NonNull Integer nId, String title, String description) {

        List<NotesModel> note = getNotesForTask(taskId);

        if(note == null){
            return  null;
        }

        for(NotesModel notesModel: note){
            if(notesModel.getId() == nId){
                notesModel.setTitle(title);
                notesModel.setDescription(description);
                return notesModel;
            }
        }


        TaskNotesHolder notesHolder = taskNotesHolder.get(taskId);
        NotesModel notesModel = notesHolder.notesList.get(nId);

       return notesModel;
    }

    //Todo:
    public void deleteNotes() {
    }


}
