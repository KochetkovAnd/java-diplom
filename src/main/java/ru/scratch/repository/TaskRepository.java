package ru.scratch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.scratch.domain.Task;
import ru.scratch.domain.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByName(String name);
    List<Task> findAllByOwner(User owner);
    List<Task> findAllByOwnerId(Long id);

}
