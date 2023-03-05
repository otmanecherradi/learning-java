package me.otmane.feb19th;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class Employee {
    protected String name;
    protected Gender gender;

    protected Manager manager;

    public Employee(String name, Gender gender, Manager manager) {
        this.name = name;
        this.gender = gender;
        this.manager = manager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public double getSalary(){
        return 1430.22;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(this instanceof Manager)
            sb.append(switch (this.gender) {case MALE -> "Mr"; case FEMALE -> "Mme";});
        sb.append(" ").append(name);
        if(manager != null)
            sb.append(", is member of '").append(manager.name).append("' service");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Employee employee) {
            return this.name.equals(employee.name) && this.gender == employee.gender;
        }
        return false;
    }
}
