package ru.scratch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.scratch.domain.Task;
import ru.scratch.domain.User;
import ru.scratch.domain.UserTask;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserTaskRepository extends JpaRepository<UserTask, Long> {
    List<UserTask> findAllByUser(User user);
    List<UserTask> findAllByUserId(Long id);
    Optional<UserTask> findByUserAndTask(User user, Task task);
    Optional<UserTask> findByUserIdAndTaskId(Long userId, Long taskId);
    Long deleteAllByUser(User user);
    List<UserTask> findAllByUserGroupId(Long id);
}
