package ru.scratch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.scratch.domain.Command;

public interface CommandRepository extends JpaRepository<Command, Long> {
}
