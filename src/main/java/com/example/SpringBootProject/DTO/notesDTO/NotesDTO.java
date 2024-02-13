package com.example.SpringBootProject.DTO.notesDTO;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NotesDTO {
    private String title;
    private int id;
    private String description;

}
