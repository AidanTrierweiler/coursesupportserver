package edu.ithaca.dragon.coursesupportserver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@TestPropertySource(locations="classpath:test.properties")
public class AttendanceMarkRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    AttendanceMarkRepository basicTestRepo;

    public void loadBasicTestRepo(){
        basicTestRepo.save(new AttendanceMark("Katie", "COMP220", 1, "present"));
        basicTestRepo.save(new AttendanceMark("Belinda", "COMP220", 1, "present"));
        basicTestRepo.save(new AttendanceMark("Jose", "COMP220", 1, "present"));
        basicTestRepo.save(new AttendanceMark("Aaron", "COMP220", 1, "present"));
        basicTestRepo.save(new AttendanceMark("Kaitlyn", "COMP220", 1, "present"));
        basicTestRepo.save(new AttendanceMark("Jocelyn", "COMP172", 1, "present"));
        basicTestRepo.save(new AttendanceMark("Jared", "COMP172", 1, "present"));
        basicTestRepo.save(new AttendanceMark("Monica", "COMP172", 1, "present"));
        basicTestRepo.save(new AttendanceMark("David", "COMP172", 1, "present"));
    }

    @Test
    public void testSave(){
        assertTrue(basicTestRepo.findAll().isEmpty());
        // loadBasicTestRepo();
        // List<AttendanceMark> attendanceMarks = basicTestRepo.findAll();
        // assertEquals(9, attendanceMarks.size());
    }

    @Test
    public void testFindByCourseId(){
        // loadBasicTestRepo();
        // List<AttendanceMark> attendanceMarks220 = basicTestRepo.findByCourseId("COMP220");
        // assertEquals(5, attendanceMarks220.size());
        // List<AttendanceMark> attendanceMarks172 = basicTestRepo.findByCourseId("COMP172");
        // assertEquals(4, attendanceMarks172.size());
    }
    

}
