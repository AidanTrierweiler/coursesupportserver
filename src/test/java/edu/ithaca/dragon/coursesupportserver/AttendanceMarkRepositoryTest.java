package edu.ithaca.dragon.coursesupportserver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
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
        basicTestRepo.save(new AttendanceMark("Jose", "COMP172", 1, "present"));
        basicTestRepo.save(new AttendanceMark("Monica", "COMP172", 1, "present"));
        basicTestRepo.save(new AttendanceMark("Katie", "COMP172", 1, "present"));

        basicTestRepo.save(new AttendanceMark("Katie", "COMP220", 2, "absent"));
        basicTestRepo.save(new AttendanceMark("Belinda", "COMP220", 2, "present"));
        basicTestRepo.save(new AttendanceMark("Jose", "COMP220", 2, "absent"));
        basicTestRepo.save(new AttendanceMark("Aaron", "COMP220", 2, "present"));
        basicTestRepo.save(new AttendanceMark("Kaitlyn", "COMP220", 2, "absent"));
        basicTestRepo.save(new AttendanceMark("Jocelyn", "COMP172", 2, "present"));
        basicTestRepo.save(new AttendanceMark("Jose", "COMP172", 2, "absent"));
        basicTestRepo.save(new AttendanceMark("Monica", "COMP172",2, "present"));
        basicTestRepo.save(new AttendanceMark("Katie", "COMP172", 2, "present"));
    }

    @Test
    public void testSave(){
        assertTrue(basicTestRepo.findAll().isEmpty());
        loadBasicTestRepo();
        List<AttendanceMark> attendanceMarks = basicTestRepo.findAll();
        assertEquals(18, attendanceMarks.size());
    }

    @Test
    public void testFindByCourseId(){
        loadBasicTestRepo();
        assertEquals(10, basicTestRepo.findByCourseId("COMP220").size());
        assertEquals(8, basicTestRepo.findByCourseId("COMP172").size());

        assertEquals(0, basicTestRepo.findByCourseId("nonpresent course id").size());
    }

    @Test
    public void testFindByStudentId(){
        loadBasicTestRepo();
        assertEquals(4, basicTestRepo.findByStudentId("Katie").size());
        assertEquals(4, basicTestRepo.findByStudentId("Jose").size());
        assertEquals(2, basicTestRepo.findByStudentId("Belinda").size());
        assertEquals(2, basicTestRepo.findByStudentId("Monica").size());

        assertEquals(0, basicTestRepo.findByStudentId("nonpresent name").size());
    }
    
    @Test
    public void testFindByDayNumber(){
        loadBasicTestRepo();
        assertEquals(9, basicTestRepo.findByDayNumber(1).size());
        assertEquals(9, basicTestRepo.findByDayNumber(2).size());

        assertEquals(0, basicTestRepo.findByDayNumber(3).size());
    }

}
