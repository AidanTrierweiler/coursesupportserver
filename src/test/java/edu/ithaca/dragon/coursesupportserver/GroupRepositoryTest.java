package edu.ithaca.dragon.coursesupportserver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test") // Activate the "test" profile for H2
public class GroupRepositoryTest {

    @Autowired
    GroupRepository groupRepository;

    @BeforeEach
    public void clearDatabase() {
        groupRepository.deleteAll(); // Clear the database before each test
    }

    @Test
    public void testSave() {
        assertTrue(groupRepository.findAll().isEmpty());

        // Save a group with subgroups as plain text and courseId
        String subgroups = "[[ally, kate, brendan]; [sasha, connie, eren]; [rick, omar, kevin]]";
        String courseId = "COMP220";
        groupRepository.save(new Group("2023-10-01", subgroups, courseId));

        // Verify the group was saved
        List<Group> groups = groupRepository.findAll();
        assertEquals(1, groups.size());
        assertEquals("2023-10-01", groups.get(0).getName());
        assertEquals(subgroups, groups.get(0).getSubgroups());
        assertEquals(courseId, groups.get(0).getCourseId());
    }

    @Test
    public void testFindByName() {
        // Save groups with courseId
        String subgroups1 = "[[ally, kate, brendan]; [sasha, connie, eren]]";
        String subgroups2 = "[[john, jane]; [alex, emily]]";
        String courseId1 = "COMP220";
        String courseId2 = "COMP310";
        groupRepository.save(new Group("2023-10-01", subgroups1, courseId1));
        groupRepository.save(new Group("2023-10-02", subgroups2, courseId2));

        // Find by name
        Group group = groupRepository.findByName("2023-10-01").orElseThrow();
        assertEquals("2023-10-01", group.getName());
        assertEquals(subgroups1, group.getSubgroups());
        assertEquals(courseId1, group.getCourseId());
    }

    @Test
    public void testFindByCourseId() {
        String subgroups1 = "[[ally, kate, brendan]; [sasha, connie, eren]]";
        String subgroups2 = "[[john, jane]; [alex, emily]]";
        String courseId1 = "COMP220";
        String courseId2 = "COMP310";
        groupRepository.save(new Group("2023-10-01", subgroups1, courseId1));
        groupRepository.save(new Group("2023-10-02", subgroups2, courseId2));

        List<Group> comp220Groups = groupRepository.findByCourseId(courseId1);
        assertEquals(1, comp220Groups.size());
        assertEquals("2023-10-01", comp220Groups.get(0).getName());
        assertEquals(courseId1, comp220Groups.get(0).getCourseId());
    }
}