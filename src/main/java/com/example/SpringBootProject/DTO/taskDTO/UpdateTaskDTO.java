package com.example.SpringBootProject.DTO.taskDTO;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskDTO {
    private String name;
    private String description;
    private boolean status;
    private String deadline;

}
