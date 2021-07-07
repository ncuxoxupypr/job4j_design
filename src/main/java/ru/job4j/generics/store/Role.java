package ru.job4j.generics.store;

public class Role extends Base {
    private String role;
    private int salary;

    public Role(String id, String role, int salary) {
        super(id);
        this.role = role;
        this.salary = salary;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
