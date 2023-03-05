package me.otmane.feb19th;

import java.util.ArrayList;

public class Manager extends Employee {
    private ArrayList<Employee> employees = new ArrayList<>(10);

    public Manager(String name, Gender gender) {
        this(name, gender, null);
    }

    public Manager(String name, Gender gender, Manager manager) {
        super(name, gender, manager);

        if (manager != null)
            manager.addEmployee(this);
    }

    public void addEmployee(Employee employee) {
        if (employees.size() > 10) {
            System.out.println("Cannot add employee, manager already has 10 employees");
            return;
        }

        int idx = employees.indexOf(employee);
        if (idx == -1) {
            employees.add(employee);
            employee.manager.employees.remove(employee);
            employee.setManager(this);

        } else {
            System.out.println("Cannot add employee, employee already affected to the manager");
        }
    }

    public void showEmployees() {
        System.out.println(employees.size());
        for (Employee e : employees)
            System.out.println(e);
    }

    @Override
    public double getSalary() {
        return super.getSalary() + ((super.getSalary() * .4) * employees.size());
    }
}
