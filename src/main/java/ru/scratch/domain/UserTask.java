package ru.scratch.domain;

import lombok.Data;
import ru.scratch.domain.keys.UserTaskKey;

import javax.persistence.*;

@Entity
@Table(name = "user_task")
@Data
public class UserTask {

    @EmbeddedId
    private UserTaskKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("taskId")
    @JoinColumn(name = "task_id")
    private Task task;

    @Column(name = "solved")
    private Boolean solved;

    @Column(name = "solution")
    private String solution;

    @Column(name = "mark")
    private int mark;
}
