package ru.scratch.dto;

import lombok.Data;

@Data
public class CommandDTO {

    private long id;
    private String type;
    private String text;
    private String color;
    private String numberOfRepeats;
}
