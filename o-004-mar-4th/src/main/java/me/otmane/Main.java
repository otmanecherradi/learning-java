package me.otmane;

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("s1");
        Student s2 = new Student("s2");
        Student s3 = new Student("s3");
        Student s4 = new Student("s4");
        Student s5 = new Student("s5");


        Group grp1 = new Group();
        grp1.addStudent(s3);
        grp1.addStudent(s1);
        grp1.addStudent(s2);

        Group grp2 = new Group();
        grp2.addStudent(s5);
        grp2.addStudent(s4);


        System.out.println("group 1 size: " + grp1.count());
        grp1.showStudents();
        System.out.println("group 2 size: " + grp2.count());
        grp2.showStudents();

        try {
            s1.addMark(30);
        } catch (InvalidMarkException e) {
            System.out.println(e.getMessage());
        }

        try {
            s1.addMark(10);
            s1.addMark(12);
            s1.addMark(14);

            s2.addMark(16);
            s2.addMark(12);
            s2.addMark(18);

            s3.addMark(16);
            s3.addMark(12);
            s3.addMark(20);

            s4.addMark(16);
            s4.addMark(11);
            s4.addMark(18);

            s5.addMark(11);
            s5.addMark(11);
            s5.addMark(18);
        } catch (InvalidMarkException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("grp 1 best student: " + grp1.getbestStudent());

        grp2.sort();
        grp2.showStudents();
    }
}