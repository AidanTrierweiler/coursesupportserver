package edu.ithaca.dragon.coursesupportserver;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {

    List<Group> findByName(String name);
}
