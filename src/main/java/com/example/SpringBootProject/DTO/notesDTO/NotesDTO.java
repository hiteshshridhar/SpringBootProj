package com.example.SpringBootProject.DTO.notesDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotesDTO {
    private String title;
    private int id;
    private String description;

}
