/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author waelb
 */
public class Evenement {
    
    private int id;
    private String lieu;
    private Date date;
    private Date datefin;
    private String titre;
    private String description;
    private int nbparticipations;

    public Evenement(int id, String lieu, Date date, Date datefin, String titre, String description, int nbparticipations) {
        this.id = id;
        this.lieu = lieu;
        this.date = date;
        this.datefin = datefin;
        this.titre = titre;
        this.description = description;
        this.nbparticipations = nbparticipations;
    }

    public Evenement(String lieu, Date date, Date datefin, String titre, String description) {
        this.lieu = lieu;
        this.date = date;
        this.datefin = datefin;
        this.titre = titre;
        this.description = description;
    }

    public Evenement(String lieu, Date date, Date datefin, String titre, String description, int nbparticipations) {
        this.lieu = lieu;
        this.date = date;
        this.datefin = datefin;
        this.titre = titre;
        this.description = description;
        this.nbparticipations = nbparticipations;
    }
    
    public Evenement(){
        
    }
    
    public int getId() {
        return id;
    }

    public String getLieu() {
        return lieu;
    }

    public Date getDate() {
        return date;
    }

    public Date getDatefin() {
        return datefin;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public int getNbparticipations() {
        return nbparticipations;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNbparticipations(int nbparticipations) {
        this.nbparticipations = nbparticipations;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", lieu=" + lieu + ", date=" + date + ", datefin=" + datefin + ", titre=" + titre + ", description=" + description + ", nbparticipations=" + nbparticipations + '}';
    }
    
    
    
}
