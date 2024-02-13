package com.example.SpringBootProject.service;

import com.example.SpringBootProject.DTO.taskDTO.UpdateTaskDTO;
import com.example.SpringBootProject.entities.TaskEntity;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Service
public class TaskService {
    private int taskId = 1;
    @Getter
    private final ArrayList <TaskEntity> taskList = new ArrayList<>();
    private final SimpleDateFormat deadlineFormatter = new SimpleDateFormat("yyyy-MM-dd");
    private final SimpleDateFormat createDateFormatter = new SimpleDateFormat("yyyy-MM-dd");


    public TaskEntity addTask(String name, String description, String createdAt, String deadline) throws ParseException {

        TaskEntity task = new TaskEntity();
        task.setId(taskId);
        task.setName(name);
        task.setDescription(description);
        task.setDeadline(deadlineFormatter.parse(deadline));
        task.setStatus(false);
        task.setCreatedAt(createDateFormatter.parse(createdAt));

        taskList.add(task);
        taskId++;
        return  task;
    }

    public TaskEntity getTaskById(int id){
        for(TaskEntity task : taskList){
            if(task.getId() == id){
                return task;
            }
        }
        return null;
    }

    public TaskEntity updateTask(int id, UpdateTaskDTO body) throws Exception {
        System.out.println(body);
       TaskEntity task = getTaskById(id);
        if(task == null){
            throw new Exception();
        } else {
           task = toUpdateTask(id, body.getName(), body.getDescription(), body.getDeadline() );
        }
        return task;
    }

    private TaskEntity toUpdateTask(int id, String name, String description, String deadline) throws ParseException {
        TaskEntity task = getTaskById(id);

        if(name!=null){
            task.setName(name);
        }

        if(description != null){
            task.setDescription(description);
        }

        if(deadline != null){
            task.setDeadline(deadlineFormatter.parse(deadline));
        }

        task.setStatus(true);

        return task;
    }



}
