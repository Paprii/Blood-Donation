package com.example.bloodforlife;

public class Profile {
    String name, email, password,bloodGroup, phone;

    public Profile() {
    }

    public Profile(String name, String email, String bloodGroup,String password, String phone) {
        this.name = name;
        this.email = email;
        this.bloodGroup = bloodGroup;
        this.password=password;
        this.phone = phone;
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

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

