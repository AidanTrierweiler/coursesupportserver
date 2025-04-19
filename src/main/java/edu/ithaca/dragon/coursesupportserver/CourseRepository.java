package edu.ithaca.dragon.coursesupportserver;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByCourseId(String courseId);
}
