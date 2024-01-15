package com.example.SpringBootProject.service;

import com.example.SpringBootProject.entities.TaskEntity;
import lombok.Getter;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class TaskService {
    private int taskId = 1;
    @Getter
    private ArrayList <TaskEntity> taskList = new ArrayList<>();

    public TaskEntity addTask(String name, String description){

        TaskEntity task = new TaskEntity();
        task.setId(taskId);
        task.setName(name);
        task.setDescription(description);
        //task.setDeadline(new Date(deadline));
        task.setStatus(false);
        //task.setCreatedAt(new Date(createdAt));

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


}
