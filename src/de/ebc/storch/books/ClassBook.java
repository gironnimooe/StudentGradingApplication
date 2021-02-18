package de.ebc.storch.books;

import de.ebc.storch.inputOutput.Organisator;
import de.ebc.storch.person.Student;
import de.ebc.storch.person.Teacher;

import java.util.Map;

public class ClassBook {

    public static void printValues() {

        Organisator orga = new Organisator();
        Map<Integer, Student> printStudentList = orga.studentMap;
        Map<Integer, Teacher> printSubjectList = orga.subjectList;
        System.out.println("\n\n------------------------------------------------------------------------------");
        System.out.println("This section is still under construction. We are sorry for the inconveniences!");
        System.out.println("\n------------------------------------------------------------------------------");
        //System.out.println(printSubjectList.values().iterator().next() + "\t " + printStudentList.values().iterator().next());
        orga.breakWhile();
        orga.start();

    }
}
