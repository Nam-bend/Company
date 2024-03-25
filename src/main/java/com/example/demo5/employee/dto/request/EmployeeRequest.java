package com.example.demo5.employee.dto.request;

public class EmployeeRequest {
    private String name;
    private int age;

    private int numberOfEmployees;
    private int numberOfWorkingDays;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public int getNumberOfWorkingDays() {
        return numberOfWorkingDays;
    }

    public void setNumberOfWorkingDays(int numberOfWorkingDays) {
        this.numberOfWorkingDays = numberOfWorkingDays;
    }

    @Override
    public String toString() {
        return "EmployeeRequest{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", numberOfEmployees=" + numberOfEmployees +
                ", numberOfWorkingDays=" + numberOfWorkingDays +
                '}';
    }
//    @Override
//    public String toString() {
//        return "EmployeeResponse{" +
//                "id='" + id + '\'' +
//                ", name='" + getName() + '\'' +
//                ", age=" + getAge() +
//                ", numberOfEmployees=" + getNumberOfEmployees() +
//                ", numberOfWorkingDays=" + getNumberOfWorkingDays() +
//                '}';
//    }
}
