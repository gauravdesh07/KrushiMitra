package com.gaurav.krushimitra;

public class User {
    String name;
    String email;
    String password;
    String mobile;
    String date;

    public User(String name, String email, String password, String mobile,String date) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.date=date;
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
