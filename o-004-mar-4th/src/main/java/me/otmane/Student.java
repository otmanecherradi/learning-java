package me.otmane;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class Student {
    private String name;
    private ArrayList<Double> marks = new ArrayList<>();

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Double> getMarks() {
        return marks;
    }

    public void setMarks(ArrayList<Double> marks) {
        this.marks = marks;
    }

    public Double getAverage() {
        if (marks.size() == 0)
            return null;
        return marks.stream().reduce((double) 0, Double::sum) / marks.size();
    }

    public void addMark(double mark) throws InvalidMarkException {
        if(mark <0 || mark>20)
            throw new InvalidMarkException();
        marks.add(mark);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                "average mark='" + getAverage() + '\'' +
                '}';
    }

    public static Comparator<Student> CompareStudentWithAverage = new Comparator<>() {
        @Override
        public int compare(Student o1, Student o2) {
            return o2.getAverage().compareTo(o1.getAverage());
        }
    };

    public static Comparator<Student> CompareStudentWithName = new Comparator<>() {
        @Override
        public int compare(Student o1, Student o2) {
            return o1.name.compareTo(o2.name);
        }
    };
}
