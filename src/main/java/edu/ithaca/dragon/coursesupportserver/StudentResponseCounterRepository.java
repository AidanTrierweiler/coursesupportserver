package edu.ithaca.dragon.coursesupportserver;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentResponseCounterRepository extends JpaRepository<StudentResponseCounter, Long> {
    Optional<StudentResponseCounter> findByStudentId(long studentId);
}