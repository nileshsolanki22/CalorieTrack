package com.track.calorie;

public class User {

    private int id;
    private String email;
    private String password;

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String toString(){
        return "com.track.calorie.User{" +
                "id=}"+ id +
                ", email ='"+ email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}