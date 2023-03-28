package ru.scratch.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "task")
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @Column(name = "n")
    private int n;

    @Column(name = "m")
    private int m;

    @Column(name = "grid")
    private String grid;

    @Column(name = "x")
    private int x;

    @Column(name = "y")
    private int y;

    @Column(name = "angle")
    private int angle;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "task_command",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "command_id")
    )
    List<Command> commands;
}
