package com.example.SpringBootProject.entities;

import lombok.Builder;
import lombok.Data;

import java.util.Date;


@Data
public class TaskEntity {
    private int id;
    private String name;
    private String description;
    private Date createdAt;
    private Date deadline;
    private boolean status;
}
