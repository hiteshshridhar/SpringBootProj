package com.example.SpringBootProject.service;

import com.example.SpringBootProject.DTO.taskDTO.UpdateTaskDTO;
import com.example.SpringBootProject.model.TaskModel;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Service
public class TaskService {
    private int taskId = 1;
    @Getter
    private final ArrayList <TaskModel> taskList = new ArrayList<>();
    private final SimpleDateFormat deadlineFormatter = new SimpleDateFormat("yyyy-MM-dd");
    private final SimpleDateFormat createDateFormatter = new SimpleDateFormat("yyyy-MM-dd");


    public TaskModel addTask(@NonNull String name, String description, String createdAt, String deadline) throws ParseException {

        TaskModel task = new TaskModel();
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

    public TaskModel getTaskById(int id){
        for(TaskModel task : taskList){
            if(task.getId() == id){
                return task;
            }
        }
        return null;
    }

    public TaskModel updateTask(int id, UpdateTaskDTO body) throws Exception {
        System.out.println(body);
       TaskModel task = getTaskById(id);
        if(task == null){
            throw new Exception();
        }
        task = toUpdateTask(id, body.getName(), body.getDescription(), body.getDeadline() );
        return task;
    }

    private TaskModel toUpdateTask(@NonNull Integer id, String name, String description, String deadline) throws ParseException {
        TaskModel task = getTaskById(id);


        if(name != null){task.setName(name);}

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
