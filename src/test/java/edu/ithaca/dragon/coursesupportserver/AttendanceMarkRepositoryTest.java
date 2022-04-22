package edu.ithaca.dragon.coursesupportserver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class AttendanceMarkRepositoryTest {

    @Autowired
    AttendanceMarkRepository basicTestRepo;

    @Test
    public void testSave(){
        assertTrue(basicTestRepo.findAll().isEmpty());
        basicTestRepo.saveAll(AttendanceMarkRespositoryExamples.basicTestRepoList());
        List<AttendanceMark> attendanceMarks = basicTestRepo.findAll();
        assertEquals(18, attendanceMarks.size());
    }

    @Test
    public void testFindByCourseId(){
        basicTestRepo.saveAll(AttendanceMarkRespositoryExamples.basicTestRepoList());
        assertEquals(10, basicTestRepo.findByCourseId("COMP220").size());
        assertEquals(8, basicTestRepo.findByCourseId("COMP172").size());

        assertEquals(0, basicTestRepo.findByCourseId("nonpresent course id").size());
    }

    @Test
    public void testFindByStudentId(){
        basicTestRepo.saveAll(AttendanceMarkRespositoryExamples.basicTestRepoList());
        assertEquals(4, basicTestRepo.findByStudentId("Katie").size());
        assertEquals(4, basicTestRepo.findByStudentId("Jose").size());
        assertEquals(2, basicTestRepo.findByStudentId("Belinda").size());
        assertEquals(2, basicTestRepo.findByStudentId("Monica").size());

        assertEquals(0, basicTestRepo.findByStudentId("nonpresent name").size());
    }
    
    @Test
    public void testFindByDayNumber(){
        basicTestRepo.saveAll(AttendanceMarkRespositoryExamples.basicTestRepoList());
        assertEquals(9, basicTestRepo.findByDayNumber(1).size());
        assertEquals(9, basicTestRepo.findByDayNumber(2).size());

        assertEquals(0, basicTestRepo.findByDayNumber(3).size());
    }

}
