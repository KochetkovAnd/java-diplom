package ru.scratch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.scratch.domain.Group;
import ru.scratch.domain.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findAllByTeacher(User teacher);
    List<Group> findAllByTeacherId(Long id);
    Optional<Group> findByName(String name);
}
