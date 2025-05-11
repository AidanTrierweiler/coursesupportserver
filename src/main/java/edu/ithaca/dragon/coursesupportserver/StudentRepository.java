package edu.ithaca.dragon.coursesupportserver;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByNetpass(String netpass);

    @Query("SELECT s FROM Student s JOIN s.courses c WHERE c.courseId = :courseId")
    List<Student> findByCourseId(@Param("courseId") String courseId);
}
