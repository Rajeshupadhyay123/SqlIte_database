package com.example.exeprovider;

public class Employee {
    private String emp_email;
    private String emp_profile;
    private String emp_name;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Employee(String emp_email,String emp_name,String emp_profile) {
        this.emp_email=emp_email;
        this.emp_name =emp_name;
        this.emp_profile=emp_profile;
    }

    public String getEmp_email() {
        return emp_email;
    }

    public String getEmp_profile() {
        return emp_profile;
    }

    public String getEmp_name() {
        return emp_name;
    }


}
