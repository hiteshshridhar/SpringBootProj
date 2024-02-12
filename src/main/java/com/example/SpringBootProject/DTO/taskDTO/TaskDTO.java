package com.example.SpringBootProject.DTO.taskDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class TaskDTO {
    private String name;
    private String description;
    private boolean status;
    private String deadline;
    private String createdAt;
}
