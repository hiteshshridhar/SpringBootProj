package com.example.SpringBootProject.DTO.taskDTO;

import com.example.SpringBootProject.model.NotesModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TaskResponseDTO {
    private String name;
    private String description;
    private boolean status;
    private String deadline;
    private String createdAt;
    private List<NotesModel> notes;
}
