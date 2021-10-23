package com.example.toastapp.classes;

public class PostModel {

    String title,name,email;


    public PostModel(String title, String name, String email) {
        this.title = title;

        this.name = name;
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}
