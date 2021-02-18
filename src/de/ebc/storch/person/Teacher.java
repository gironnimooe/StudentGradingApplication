package de.ebc.storch.person;

public class Teacher {
    private String teacherName;
    private String teacherSubject;
    private int teacher_Id;

    public Teacher() {}

    public Teacher(String subject, String name){
        this.teacherName = name;
        this.teacherSubject = subject;
    }

    public String getName() {
        return teacherName;
    }

    public String setName(String firstName) {
        this.teacherName = firstName;
        return teacherName;
    }

    public String getSubject() {
        return teacherSubject;
    }

    public void setSubject(String subject) {
        this.teacherSubject = subject;
    }

}

