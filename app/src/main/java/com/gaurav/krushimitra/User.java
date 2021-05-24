package com.gaurav.krushimitra;

public class User {
    String name;
    String email;
    String password;
    String mobile;
    String date;
    double amount_spent;
    double amount_gained;

    public User(String name, String email, String password, String mobile,String date,double amount_spent,double amount_gained) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.date=date;
        this.amount_gained=amount_gained;
        this.amount_spent=amount_spent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
