package com.example.SpringBootProject.model;

import lombok.Data;

import java.util.Date;


@Data
public class TaskModel {
    private int id;
    private String name;
    private String description;
    private Date createdAt;
    private Date deadline;
    private boolean status;
}
