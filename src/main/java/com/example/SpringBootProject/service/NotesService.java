package com.example.SpringBootProject.service;


import com.example.SpringBootProject.model.NotesModel;
import com.example.SpringBootProject.model.TaskModel;
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
        TaskModel task = taskService.getTaskById(taskId);
        if(task == null){
            return null;
        }

        if(taskNotesHolder.get(taskId)== null){
            taskNotesHolder.put(taskId, new TaskNotesHolder());
        }
        return taskNotesHolder.get(taskId).notesList;
    }

    public NotesModel addNotesForTask(int taskId, String title, String description) {
        TaskModel getTask = taskService.getTaskById(taskId);

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
        //System.out.println(holder.notesList);
        return newNote;
    }

    //Todo:
    public NotesModel updateNotesForTask(int taskId, @NonNull Integer nId, String title, String description) throws Exception {

        List<NotesModel> note = getNotesForTask(taskId);

        for (NotesModel notesModel : note) {
            if (notesModel == null) {
                return null;
            }

            try {
                    if (notesModel.getId() == nId) {
                        notesModel.setTitle(title);
                        notesModel.setDescription(description);
                        return notesModel;
                    }
                } catch (ArrayIndexOutOfBoundsException exception) {
                    throw new Exception(exception);
                }

            }
        return null;

    }

    public NotesModel deleteNotes(Integer taskId, Integer delNoteId) {
        List<NotesModel> notes = getNotesForTask(taskId);
        for (NotesModel notesModel : new ArrayList<>(notes) ){
                try{
                    if(notesModel.getId() == delNoteId){

                         notes.remove(notesModel.getId()-1);
                        return notesModel;
                    }
                } catch (Exception e){
                    return notesModel;
                }
            }

        return null;
    }


}
