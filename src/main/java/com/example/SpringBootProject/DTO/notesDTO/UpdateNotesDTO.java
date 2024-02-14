package com.example.SpringBootProject.DTO.notesDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateNotesDTO {
    private String title;
    private int id;
    private String description;
}
