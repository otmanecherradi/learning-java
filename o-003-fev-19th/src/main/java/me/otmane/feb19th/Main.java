package me.otmane.feb19th;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");


        Manager manager1 = new Manager("Manager 1", Gender.MALE);
        Manager manager2 = new Manager("Manager 2", Gender.FEMALE, manager1);

        Employee employee1 =  new Employee("Employee 1", Gender.FEMALE, manager1);
        Employee employee2 =  new Employee("Employee 2", Gender.FEMALE, manager1);
        manager1.addEmployee(employee1);
        manager1.addEmployee(employee2);


        Employee employee3 =  new Employee("Employee 3", Gender.FEMALE, manager2);
        Employee employee4 =  new Employee("Employee 4", Gender.FEMALE, manager2);
        Employee employee5 =  new Employee("Employee 5", Gender.FEMALE, manager2);
        Employee employee6 =  new Employee("Employee 6", Gender.FEMALE, manager2);
        manager2.addEmployee(employee3);
        manager2.addEmployee(employee4);
        manager2.addEmployee(employee5);
        manager2.addEmployee(employee6);




        System.out.println("=============================");
        System.out.println(manager1);
        System.out.println("with employees");
        manager1.showEmployees();

        System.out.println("=============================");
        System.out.println(manager2);
        System.out.println("with employees");
        manager2.showEmployees();


        manager1.addEmployee(employee4);

        System.out.println("=============================");
        System.out.println(manager1);
        System.out.println("with employees");
        manager1.showEmployees();

        System.out.println("=============================");
        System.out.println(manager2);
        System.out.println("with employees");
        manager2.showEmployees();



    }
}