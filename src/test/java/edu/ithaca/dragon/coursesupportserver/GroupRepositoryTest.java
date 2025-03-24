package edu.ithaca.dragon.coursesupportserver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class GroupRepositoryTest {

    @Autowired
    GroupRepository groupRepository;

    @Test
    public void testSave() {
        assertTrue(groupRepository.findAll().isEmpty());
        groupRepository.save(new Group("Group1", Arrays.asList("student1", "student2")));
        List<Group> groups = groupRepository.findAll();
        assertEquals(1, groups.size());
    }

    @Test
    public void testFindByName() {
        groupRepository.save(new Group("Group1", Arrays.asList("student1", "student2")));
        groupRepository.save(new Group("Group2", Arrays.asList("student3", "student4")));
        assertEquals(1, groupRepository.findAll().stream().filter(group -> group.getName().equals("Group1")).count());
        assertEquals(1, groupRepository.findAll().stream().filter(group -> group.getName().equals("Group2")).count());
    }
}