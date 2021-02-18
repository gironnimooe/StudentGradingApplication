package de.ebc.storch.inputOutput;

import java.util.ArrayList;
import java.util.List;

public class MenuOptions {

    /** Methoden die die inhalte der Optionen in Listen generieren */
    public static List<String> getGradeBookOptions() {

        final List<String> options = new ArrayList<>();

        options.add(".....[1] Create student");
        options.add(".....[2] Edit student");
        options.add(".....[3] Show student list\n");
        options.add(".....[4] Return to menu\n\n");

        return options;

    }
    public static List<String> getMainMenuOptions() {

        final List<String> options = new ArrayList<>();

        options.add(".....[1] Show gradebook");
        options.add(".....[2] Show classbook");
        options.add(".....[3] Exit the application\n\n");

        return options;
    }
    public static List<String> getStudentOptions() {

        final List<String> options = new ArrayList<>();

        options.add(".....[1] Edit student grades");
        options.add(".....[2] Edit student name");
        options.add(".....[3] Delete student");
        options.add(".....[4] List student grades");
        options.add(".....[5] Return to menu\n\n");

        return options;
    }
    public static List<String> getGradeOptions() {

        final List<String> options = new ArrayList<>();

        options.add(".....[1] Set grade");
        options.add(".....[2] Edit grade");
        options.add(".....[3] Back to student menu");

        return options;

    }
}
