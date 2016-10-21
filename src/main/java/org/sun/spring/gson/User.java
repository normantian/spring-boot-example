package org.sun.spring.gson;

/**
 * Created by tianfei on 16/10/21.
 */
public class User {
    private String name;
    private int age;
    private String emailAddress;

    public User(String name,int age) {
        this.name = name;
        this.age = age;
    }

    public User(String name, int age, String emailAddress) {
        this.name = name;
        this.age = age;
        this.emailAddress = emailAddress;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
