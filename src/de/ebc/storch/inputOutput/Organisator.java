package de.ebc.storch.inputOutput;


import de.ebc.storch.books.ClassBook;
import de.ebc.storch.person.Student;
import de.ebc.storch.person.Teacher;

import java.util.*;

public class Organisator {

    private int selectedStudent;

    private int getSelectedStudent() {
        return selectedStudent;
    }

    private void setSelectedStudent(int selectedStudent) {
        this.selectedStudent = selectedStudent;
    }

    private int selectedSubject;

    private int getSelectedSubject() {
        return selectedSubject;
    }

    private String getSelectedSubjectValue() {
        int subject = Integer.parseInt(String.valueOf(getSelectedSubject()));
        return Integer.toString(subject);
    }

    public void setSelectedSubject(int selectedSubject) {
        this.selectedSubject = selectedSubject;
    }

    /**
     * Legt eine TreeMap an
     */
    public final Map<Integer, Student> studentMap = new TreeMap<>();
    public final Map<Integer, Teacher> subjectList = new TreeMap<>();
    public final Map<Integer, Student> personalGradeList = new TreeMap<>();

    /**
     * Mainmethode - Einstiegspunkt
     */
    public void start() {

        boolean running = true;
        while (running) {
            mainMenuOptions();

            // switch case für die auswahl einer Option im Main menu
            Scanner optionChoice = new Scanner(System.in);
            int choice = optionChoice.nextInt();

            switch (choice) {
                case 1:
                    gradeMenu();
                    break;
                case 2:
                    ClassBook.printValues();
                    breakWhile();
                    break;
                case 3:
                    System.out.println("\n\n| -------------------- |\n" +
                            "|       Goodbye!       |\n" +
                            "| Hope to see you soon!|\n" +
                            "| ____________________ |\n");
                    running = false;
            }
        }
    }

    /**
     * Methode zum auswählen der verschiedenen Notenbuchoptionen
     */
    private void gradeMenu() {
        boolean running = true;
        while (running) {
            gradeMenuOptions();
            Scanner menuScan = new Scanner(System.in);

            switch ((menuScan.nextInt())) {
                case 1:
                    createStudent();
                    break;
                case 2:
                    studentMenu();
                    break;
                case 3:
                    printStudentList();
                    breakWhile();
                    break;
                case 4:
                    start();
                    break;
                default:
                    System.out.println("Invalid selection. Please try again!");
            }
        }
    }

    /**
     * Methode zum auswählen der verschiedenen Notenbuchoptionen
     */
    private void studentMenu() {
        boolean running = true;
        selectStudent();
        while (running) {
            studentMenuOptions();
            Scanner menuScan = new Scanner(System.in);

            switch (menuScan.nextInt()) {
                case 1:
                    editGrades();
                    break;
                case 2:
                    editStudentName();
                    break;
                case 3:
                    deleteStudent();
                    breakWhile();
                    gradeMenu();
                    break;
                case 4:
                    printSubjectsWithGrades();
                    breakWhile();
                    break;
                case 5:
                    gradeMenu();
                    break;
                default:
                    System.out.println("Invalid selection. Please try again!");
            }
        }
    }

    /**
     * Methode zum auswählen eines Schülers
     */
    private void selectStudent() {

        if (studentMap.isEmpty()) {
            System.out.println(
                    "\b\n--------------------------------------------------\n" +
                            "Nothing is saved yet. Please create a new student!\n" +
                            "__________________________________________________\n\n");
            breakWhile();
            gradeMenu();
        } else {
            System.out.println("--------------------------------\n" +
                    "Please select a student by ID: \n" +
                    "________________________________\n");
            printStudentList();
            System.out.println("\n\n");
            Scanner studentSelector = new Scanner(System.in);
            setSelectedStudent(studentSelector.nextInt());

            if (studentMap.containsKey(getSelectedStudent())) {

                System.out.println("--- Selected student --- \n");
                System.out.println("| ID: \t\t" + studentMap.get(getSelectedStudent()).getStudent_Id());
                System.out.println("| NAME: \t" + studentMap.get(getSelectedStudent()).getFirstName().toUpperCase() + " " + studentMap.get(getSelectedStudent()).getLastName().toUpperCase());
            } else {
                System.out.println("Invalid selection. Please try again!");
            }
        }
    }

    /**
     * Methode zum löschen des Studenten
     */
    private void deleteStudent() {

        studentMap.remove(selectedStudent);
        System.out.println("You have successfully removed the student!");

    }

    /**
     * Methode zum editieren des Namens
     */
    private void editStudentName() {

        Student selectedStudent = studentMap.get(getSelectedStudent());

        Scanner newName = new Scanner(System.in);
        System.out.println("Please enter a new first Name for " + selectedStudent.getFirstName());
        selectedStudent.setFirstName(newName.nextLine());
        System.out.println("Please enter a new last name for " + selectedStudent.getLastName());
        selectedStudent.setLastName(newName.nextLine());

        Student newNameStudent = new Student(selectedStudent.getStudent_Id(), selectedStudent.getFirstName(), selectedStudent.getLastName());

        studentMap.replace(selectedStudent.getStudent_Id(), newNameStudent);

        System.out.println("You changed the name from " + selectedStudent.getFirstName() + " " + selectedStudent.getLastName() + "to " + newNameStudent);
    }

    /**
     * Methode zum setzen einer neuen Note und zusammenbau der neuen Liste
     */
    private void setGrade() {

        printTeachersList();
        Scanner choice = new Scanner(System.in);
        System.out.println("Please choose a subject from the list to set a grade for: \n");
        int chosenSubject = choice.nextInt();
        setSelectedSubject(chosenSubject);
        String isSelectedName = studentMap.get(getSelectedStudent()).getFirstName() + " " + studentMap.get(getSelectedStudent()).getLastName();
        setSelectedSubject(chosenSubject);

        if (subjectList.containsKey(chosenSubject)) {

            String subject = subjectList.get(chosenSubject).getSubject();
            System.out.println("Please enter a grade for " + subject.trim() + " and selected student " + isSelectedName + ": ");
            int newGrade = choice.nextInt();
            Student putValue = new Student(subject, newGrade);
            personalGradeList.put(personalGradeList.size() + 1, putValue);
            System.out.println("You set the grade " + newGrade + " for the subject " + subject.trim() + " from the student " + isSelectedName.toUpperCase() + " successfully.");
        } else {
            System.out.println("Not a valid subject. please try again.\n");
        }
    }

    /**
     * Methode zum bearbeiten der Noten
     */
    private void editGrades() {

        if (personalGradeList.isEmpty()) {
            System.out.println("| There are no grades given for the student. Please set a grade first. |");
            breakWhile();
            setGrade();
        } else {
            Scanner chooseGradeToEdit = new Scanner(System.in);
            System.out.println("Please choose the subject you want to change the grade for.");
            printSubjectsWithGrades();
            int chosen = chooseGradeToEdit.nextInt();

            if (personalGradeList.containsKey(chosen)) {
                Scanner setNewGrade = new Scanner(System.in);
                System.out.println("Please set a new grade for " + subjectList.get(chosen).getSubject().trim() + ". Current grade: " + personalGradeList.get(chosen).getStudent_grade());
                int newGrade = setNewGrade.nextInt();
                Student putNewGrade = new Student(subjectList.get(chosen).getSubject(), newGrade);
                personalGradeList.replace(chosen, putNewGrade);
            } else {
                System.out.println("\nInvalid input please select a subject you want to edit.\n");
            }
        }
    }

    /**
     * Methode zum darstellen des Main menus
     */
    private void mainMenuOptions() {
        System.out.println("\n\n----- Main menu -----\n\n");
        printOptions(MenuOptions.getMainMenuOptions());
    }

    /**
     * Methode zum darstellen des Noten menus
     */
    private void gradeMenuOptions() {
        System.out.println("\n\n----- Grade menu -----\n\n");
        printOptions(MenuOptions.getGradeBookOptions());
    }

    /**
     * Methode zum darstellen des Studenten menus
     */
    private void studentMenuOptions() {
        System.out.println("\n----- Student menu -----\n\n");
        printOptions(MenuOptions.getStudentOptions());
    }

    /**
     * Methode zum darstellen der einzelnen Optionen in den Menus
     */
    private void printOptions(List<String> values) {
        values.forEach(System.out::println);
    }

    /**
     * Methode um einen Studenten anzulegen und in die Map zu speichern
     */
    public void createStudent() {

        System.out.println("---------- Create Student ----------\n");

        Scanner makeStudent = new Scanner(System.in);
        Student newS = new Student();
        System.out.println("Please type in the first name of your student: \n\n");
        String value = makeStudent.nextLine();
        newS.setFirstName(validateNameValue(value));

        System.out.println("Please type the last name of your student: \n\n");
        value = makeStudent.nextLine();
        newS.setLastName(validateNameValue(value));

        studentMap.put(studentMap.size() + 1, newS);
        newS.setStudent_Id(studentMap.size());

        System.out.println("\n\nThe student: " + newS.getFirstName() + " " + newS.getLastName() + " with ID: " + newS.getStudent_Id() + " has been created successfully. |");
        System.out.println("--------------------------------------------------------------------");
    }

    /**
     * Methode zum validieren des eingegebenen Namens
     */
    private String validateNameValue(String nameValue) {


        if (nameValue == null || nameValue.isEmpty()) {
            System.out.println("The textfield has no content. Please fill out the field by using letters without special characters only\n\n");
            createStudent();
        } else if (nameValue.length() <= 3) {
            System.out.println("Please enter a valid name with a minimum of 3 letters.\n\n");
            createStudent();
        } else if (nameValue.contains("0-9")) {
            System.out.println("A name can not contain any Numbers. Please use letters without special characters only.\n\n");
            createStudent();
        }
        return nameValue;
    }

    /**
     * Methode zum validieren der eingegebenen Id
     */
    private int validateIdValue(int idValue) {
        Set<Integer> keys = subjectList.keySet();

        if (idValue == 0) {
            System.out.println("Invalid Input. Please select a valid Id by typing a number without using special characters.\n");
        } else if (idValue == Integer.parseInt("A-Z")) {
            System.out.println("Invalid input. Id's cannot contain any letters or special characters.\n");
        }
        return idValue;
    }

    /**
     * Methode um die Liste der schüler auszugeben
     */
    public void printStudentList() {

        studentMap.forEach((key, value) -> System.out.println("| ID: " + key + "\t | Student: " + value.getFirstName().toUpperCase() + " " + value.getLastName().toUpperCase() + "\t\t\t "));
    }

    /**
     * Methode zum unterbrechen der Whileschleife
     */
    public void breakWhile() {

        System.out.println("\n\nPress any key to continue:\n");
        Scanner justBreak = new Scanner(System.in);
        justBreak.nextLine();
    }

    /**
     * Methode zum füllen der Lehrer Map
     */
    public void setValueForTeacherList() {

        Teacher teacher1 = new Teacher("FE\t\t\t", "Frau Lünser\t\t");
        subjectList.put(subjectList.size() + 1, teacher1);
        Teacher teacher2 = new Teacher("SP\t\t\t", "Herr Zangrando\t");
        subjectList.put(subjectList.size() + 1, teacher2);
        Teacher teacher3 = new Teacher("BW/5\t\t", "Herr Lohrke\t\t");
        subjectList.put(subjectList.size() + 1, teacher3);
        Teacher teacher4 = new Teacher("GK\t\t\t", "Frau Wittmann\t\t");
        subjectList.put(subjectList.size() + 1, teacher4);
        Teacher teacher5 = new Teacher("DE\t\t\t", "Frau Kretzschmar\t");
        subjectList.put(subjectList.size() + 1, teacher5);
        Teacher teacher6 = new Teacher("EA/7\t\t", "Herr Baumann\t\t");
        subjectList.put(subjectList.size() + 1, teacher6);
        Teacher teacher7 = new Teacher("BW/6\t\t", "Frau Häußer\t\t");
        subjectList.put(subjectList.size() + 1, teacher7);
        Teacher teacher8 = new Teacher("IT/9\t\t", "Herr Sebastian\t");
        subjectList.put(subjectList.size() + 1, teacher8);
        Teacher teacher9 = new Teacher("BW/4\t\t", "Frau Eckey\t\t");
        subjectList.put(subjectList.size() + 1, teacher9);
        Teacher teacher10 = new Teacher("IT/6\t\t", "Herr Jungnickel\t");
        subjectList.put(subjectList.size() + 1, teacher10);
        Teacher teacher11 = new Teacher("IT/6\t\t", "Herr Gutschke\t\t");
        subjectList.put(subjectList.size() + 1, teacher11);
        Teacher teacher12 = new Teacher("IT/7\t\t", "Herr Könnecke\t\t");
        subjectList.put(subjectList.size() + 1, teacher12);
        Teacher teacher13 = new Teacher("EA/4\t\t", "Herr Schneemann\t");
        subjectList.put(subjectList.size() + 1, teacher13);
        Teacher teacher14 = new Teacher("EA/1\t\t", "Frau Neustadt\t\t");
        subjectList.put(subjectList.size() + 1, teacher14);
        Teacher teacher15 = new Teacher("EA/6 G1\t", "Herr Schneemann\t");
        subjectList.put(subjectList.size() + 1, teacher15);
        Teacher teacher16 = new Teacher("EA/6 G2\t", "Dr. Bischof\t\t");
        subjectList.put(subjectList.size() + 1, teacher16);
        Teacher teacher17 = new Teacher("EA/5 G1\t", "Herr Schneemann\t");
        subjectList.put(subjectList.size() + 1, teacher17);
        Teacher teacher18 = new Teacher("EA/6 G2\t", "Herr Schmidtke\t");
        subjectList.put(subjectList.size() + 1, teacher18);
    }

    /**
     * Methode zum ausgeben der Schulfächer mit Lehrern
     */
    public void printTeachersList() {
        setValueForTeacherList();
        subjectList.forEach((key, value) -> System.out.println("| ID: " + key + "\t Subject: " + value.getSubject() + "\t| Teacher:  " + value.getName()));
    }

    /**
     * Methode zum ausgeben der Schulfächer mit Noten für den gewählten Student
     */
    public void printSubjectsWithGrades() {

        if (personalGradeList.isEmpty()) {
            System.out.println("No Data. Please add a student and grades.");
        }

        else {
            System.out.println("----- " + studentMap.get(getSelectedStudent()).getLastName() + " " + studentMap.get(getSelectedStudent()).getFirstName() + " -----\n");
            personalGradeList.forEach((key, value) -> System.out.println("| ID: " + key + " Subject: " + subject() + "\t | Note: " + value.getStudent_grade()));
        }
    }

    public String subject() {
        return subjectList.get(selectedSubject).getSubject();
    }
}


