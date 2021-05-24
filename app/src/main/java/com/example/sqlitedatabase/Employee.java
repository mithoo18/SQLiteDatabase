package com.example.sqlitedatabase;

public class Employee {
    int id;
    String name, dept, joiningDate;
    double salary;

    public Employee(int anInt, String string, String string1, String string2, int anInt1) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.joiningDate = joiningDate;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDept() {
        return dept;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public double getSalary() {
        return salary;
    }
}
