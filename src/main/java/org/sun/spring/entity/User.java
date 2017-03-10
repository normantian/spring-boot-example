package org.sun.spring.entity;

import java.io.Serializable;

/**
 * Created by tianfei on 17/3/9.
 */
public class User implements Serializable{
    private static final long serialVersionUID = 1L;
    private Long id;
    private String firstName;
    private String lastName;

    public User(){
        super();
    }

    public User(Long id,String firstName, String lastName) {
        super();
        this.id = id ;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
