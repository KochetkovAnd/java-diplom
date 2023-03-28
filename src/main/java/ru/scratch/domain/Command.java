package ru.scratch.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "command")
@Data
public class Command {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "type")
    private String type;

    @Column(name = "text")
    private String text;

    @Column(name = "color")
    private String color;

    @Column(name = "numberOfRepeats")
    private String numberOfRepeats;
}
