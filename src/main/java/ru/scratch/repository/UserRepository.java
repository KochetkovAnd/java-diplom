package ru.scratch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.scratch.domain.Group;
import ru.scratch.domain.User;
import ru.scratch.domain.enums.Role;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
    List<User> findAllByGroupId(Long id);
    List<User> findAllByGroupAndRole(Group group, Role role);
    List<User> findAllByRole(Role role);
}
