package de.ebc.storch.person;

public class Student {
    private String firstName;
    private String lastName;
    private int student_Id;
    private int student_grade;
    private String subjectValues;

    public Student() {}

    public Student(int id, String firstName, String lastName){
        this.student_Id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Student (String subject, int grade) {
        this.student_grade = grade;
        this.subjectValues = subject;
    }

    public int getStudent_Id() {
        return student_Id;
    }

    public void setStudent_Id(int student_Id) {
        this.student_Id = student_Id;
    }


    public String getFirstName() {
        return firstName;
    }

    public String setFirstName(String firstName) {
        this.firstName = firstName;
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String setLastName(String lastName) {
        this.lastName = lastName;
        return lastName;
    }
    public int getStudent_grade() {
        return student_grade;
    }

    public void setStudent_grade(int student_grade) {
        this.student_grade = student_grade;
    }
}
