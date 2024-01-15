package com.example.SpringBootProject.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TaskDTO {
    private String name;
    private String description;
    private boolean status;

}
