package com.login.persistance.repositories;

//import jakarta.persistance for sql database ease
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;

//@Entity
//@Table(name="users") // for future sql database 
public class User {
    //@Id // specifies the primary key
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //@Column(unique = true, nullable = false)
    private String username;

    //@Column(nullable = false)
    private String password;

    public String getPassword() {return password;}
    /* TODO: Getters and setters, hashcode, equals and to string
        Hash passwords!!!
    */


}
