/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author Nihel
 */
public class Reclamation {
    private int id;
    private String intitule;
    private String contenu;
    private int idUser;
    private String image;
    private Date date;

    public Reclamation() {
    }
    
    

    public Reclamation(int id, String intitule, String contenu, int idUser, String image, Date date) {
        this.id = id;
        this.intitule = intitule;
        this.contenu = contenu;
        this.idUser = idUser;
        this.image = image;
        this.date = date;
    }
    
    

    public int getId() {
        return id;
    }

    public String getIntitule() {
        return intitule;
    }

    public String getContenu() {
        return contenu;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getImage() {
        return image;
    }

    public Date getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", intitule=" + intitule + ", contenu=" + contenu + ", idUser=" + idUser + ", image=" + image + ", date=" + date + '}';
    }
    
    
    
}
