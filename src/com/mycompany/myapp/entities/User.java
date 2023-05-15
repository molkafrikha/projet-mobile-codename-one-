/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;


/**
 *
 * @author Andrew
 */
public class User {
    private int id,age;
    private String nom, prenom,password, email;

    public User(String email,String password,String nom, String prenom,int age ) {
        this.email = email;
        this.password = password;
        this.nom= nom;
        this.prenom=prenom;
        this.age=age;
    }

    public User() {
    }
    
    public User(int id,String email,String password,String nom, String prenom,int age) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nom= nom;
        this.prenom=prenom;
        this.age=age;
    }

    public User(String email,String password) {
         this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Task{" + "id=" + id + ", age=" + age + ", nom=" + nom + ", prenom=" + prenom + ", password=" + password + ", email=" + email + '}';
    }


    
    
    
    
}
