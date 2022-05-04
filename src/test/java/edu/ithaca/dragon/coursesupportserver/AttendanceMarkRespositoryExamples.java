package edu.ithaca.dragon.coursesupportserver;

import java.util.ArrayList;
import java.util.List;

public class AttendanceMarkRespositoryExamples {

    public static List<AttendanceMark> basicTestRepoList(){
        List<AttendanceMark> newList = new ArrayList<>();
        newList.add(new AttendanceMark("Katie", "COMP220", 1, "present"));
        newList.add(new AttendanceMark("Belinda", "COMP220", 1, "present"));
        newList.add(new AttendanceMark("Jose", "COMP220", 1, "present"));
        newList.add(new AttendanceMark("Aaron", "COMP220", 1, "present"));
        newList.add(new AttendanceMark("Kaitlyn", "COMP220", 1, "present"));
        newList.add(new AttendanceMark("Jocelyn", "COMP172", 1, "present"));
        newList.add(new AttendanceMark("Jose", "COMP172", 1, "present"));
        newList.add(new AttendanceMark("Monica", "COMP172", 1, "present"));
        newList.add(new AttendanceMark("Katie", "COMP172", 1, "present"));

        newList.add(new AttendanceMark("Katie", "COMP220", 2, "absent"));
        newList.add(new AttendanceMark("Belinda", "COMP220", 2, "present"));
        newList.add(new AttendanceMark("Jose", "COMP220", 2, "absent"));
        newList.add(new AttendanceMark("Aaron", "COMP220", 2, "present"));
        newList.add(new AttendanceMark("Kaitlyn", "COMP220", 2, "absent"));
        newList.add(new AttendanceMark("Jocelyn", "COMP172", 2, "present"));
        newList.add(new AttendanceMark("Jose", "COMP172", 2, "absent"));
        newList.add(new AttendanceMark("Monica", "COMP172",2, "present"));
        newList.add(new AttendanceMark("Katie", "COMP172", 2, "present"));

        newList.add(new AttendanceMark("Katie", "COMP220", 3, "absent"));
        newList.add(new AttendanceMark("Belinda", "COMP220", 3, "present"));
        newList.add(new AttendanceMark("Jose", "COMP220", 3, "absent"));
        newList.add(new AttendanceMark("Aaron", "COMP220", 3, "present"));
        newList.add(new AttendanceMark("Kaitlyn", "COMP220", 3, "absent"));
        newList.add(new AttendanceMark("Jocelyn", "COMP172", 3, "present"));
        newList.add(new AttendanceMark("Jose", "COMP172", 3, "absent"));
        newList.add(new AttendanceMark("Monica", "COMP172",3, "present"));
        newList.add(new AttendanceMark("Katie", "COMP172", 3, "present"));

        newList.add(new AttendanceMark("Katie", "COMP220", 4, "present"));
        newList.add(new AttendanceMark("Belinda", "COMP220", 4, "absent"));
        newList.add(new AttendanceMark("Jose", "COMP220", 4, "present"));
        newList.add(new AttendanceMark("Aaron", "COMP220", 4, "absent"));
        newList.add(new AttendanceMark("Kaitlyn", "COMP220", 4, "present"));
        newList.add(new AttendanceMark("Jocelyn", "COMP172", 4, "absent"));
        newList.add(new AttendanceMark("Jose", "COMP172", 4, "present"));
        newList.add(new AttendanceMark("Monica", "COMP172",4, "absent"));
        newList.add(new AttendanceMark("Katie", "COMP172", 4, "absent"));

        newList.add(new AttendanceMark("Katie", "COMP220", 5, "present"));
        newList.add(new AttendanceMark("Belinda", "COMP220", 5, "present"));
        newList.add(new AttendanceMark("Jose", "COMP220", 5, "absent"));
        newList.add(new AttendanceMark("Aaron", "COMP220", 5, "absent"));
        newList.add(new AttendanceMark("Kaitlyn", "COMP220", 5, "present"));
        newList.add(new AttendanceMark("Jocelyn", "COMP172", 5, "present"));
        newList.add(new AttendanceMark("Jose", "COMP172", 5, "absent"));
        newList.add(new AttendanceMark("Monica", "COMP172",5, "absent"));
        newList.add(new AttendanceMark("Katie", "COMP172", 5, "present"));

        newList.add(new AttendanceMark("Katie", "COMP220", 6, "absent"));
        newList.add(new AttendanceMark("Belinda", "COMP220", 6, "absent"));
        newList.add(new AttendanceMark("Jose", "COMP220", 6, "absent"));
        newList.add(new AttendanceMark("Aaron", "COMP220", 6, "absent"));
        newList.add(new AttendanceMark("Kaitlyn", "COMP220", 6, "absent"));
        newList.add(new AttendanceMark("Jocelyn", "COMP172", 6, "absent"));
        newList.add(new AttendanceMark("Jose", "COMP172", 6, "absent"));
        newList.add(new AttendanceMark("Monica", "COMP172",6, "absent"));
        newList.add(new AttendanceMark("Katie", "COMP172", 6, "absent"));
        return newList;
    }
}
