package me.otmane;

import java.util.ArrayList;
import java.util.Collections;

public class Group {
    private final ArrayList<Student> students = new ArrayList<>();

    public int count() {
        return students.size();
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public Student search(String name) {
        int idx = students.indexOf(new Student(name));
        if (idx == -1) {
            return null;
        }
        return students.get(idx);
    }

    public void showStudents() {
        for (Student s : students)
            System.out.println(s);
    }

    public Student getbestStudent(){
        return Collections.max(students, Student.CompareStudentWithAverage);
    }

    public void sort(){
        students.sort(Student.CompareStudentWithName);
    }

}
