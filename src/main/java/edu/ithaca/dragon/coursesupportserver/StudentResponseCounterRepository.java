package edu.ithaca.dragon.coursesupportserver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentResponseCounterRepository extends JpaRepository<StudentResponseCounter, Long> {
    Optional<StudentResponseCounter> findByStudentId(long studentId);

    @Query("SELECT s.netpass, s.preferredName, c.answeredCount, c.passedCount " +
            "FROM Student s " +
            "LEFT JOIN StudentResponseCounter c ON s.id = c.studentId " +
            "JOIN s.courses course " +
            "WHERE course.courseId = :courseId")
    List<Object[]> findResponseCountersByCourseId(@Param("courseId") String courseId);
}