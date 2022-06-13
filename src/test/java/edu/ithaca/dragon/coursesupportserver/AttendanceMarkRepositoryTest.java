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
        assertEquals(54, attendanceMarks.size());
    }

    @Test
    public void testFindByCourseId(){
        assertTrue(basicTestRepo.findAll().isEmpty());
        basicTestRepo.saveAll(AttendanceMarkRespositoryExamples.basicTestRepoList());
        assertEquals(30, basicTestRepo.findByCourseId("COMP220").size());
        assertEquals(24, basicTestRepo.findByCourseId("COMP172").size());

        assertEquals(0, basicTestRepo.findByCourseId("nonpresent course id").size());
    }

    @Test
    public void testFindByStudentId(){
        assertTrue(basicTestRepo.findAll().isEmpty());
        basicTestRepo.saveAll(AttendanceMarkRespositoryExamples.basicTestRepoList());
        assertEquals(12, basicTestRepo.findByStudentId("Katie").size());
        assertEquals(12, basicTestRepo.findByStudentId("Jose").size());
        assertEquals(6, basicTestRepo.findByStudentId("Belinda").size());
        assertEquals(6, basicTestRepo.findByStudentId("Monica").size());

        assertEquals(0, basicTestRepo.findByStudentId("nonpresent name").size());
    }
    
    @Test
    public void testFindByDayNumber(){
        assertTrue(basicTestRepo.findAll().isEmpty());
        basicTestRepo.saveAll(AttendanceMarkRespositoryExamples.basicTestRepoList());
        assertEquals(9, basicTestRepo.findByDayNumber(1).size());
        assertEquals(9, basicTestRepo.findByDayNumber(2).size());
        assertEquals(9, basicTestRepo.findByDayNumber(6).size());

        assertEquals(0, basicTestRepo.findByDayNumber(7).size());
    }

}
