package com.example.SpringBootProject.DTO.notesDTO;

import com.example.SpringBootProject.entities.NotesEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotesResponseDTO {
    private Integer taskId;
    private NotesEntity notesEntity;

}
