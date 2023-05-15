/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;
import java.util.Date;

/**
 *
 * @author Nadhem
 */
public class OffreCovoiturage {





    private int id;
    private String matricule;
    private String marque;
    private Date dateD;
    private String lieuD;
    private String lieuA;
    private String dispo;
    private int nbPlace;
    private String numTel;
    private double distance;
    

    public OffreCovoiturage() {

    }

    public OffreCovoiturage(String matricule, String marque, Date dateD, String lieuD, String lieuA, String dispo, int nbPlace, String numTel, double distance) {
        this.matricule = matricule;
        this.marque = marque;
        this.dateD = dateD;
        this.lieuD = lieuD;
        this.lieuA = lieuA;
        this.dispo = dispo;
        this.nbPlace = nbPlace;
        this.numTel = numTel;
        
    }
    

    public OffreCovoiturage(int id, String matricule, String marque, Date dateD, String lieuD, String lieuA, String dispo, int nbPlace, String numTel, double distance) {
        this.id = id;
        this.matricule = matricule;
        this.marque = marque;
        this.dateD = dateD;
        this.lieuD = lieuD;
        this.lieuA = lieuA;
        this.dispo = dispo;
        this.nbPlace = nbPlace;
        this.numTel = numTel;
        
    }
    public double getDistance() {
    return distance;
    }

    public int getId() {
        return id;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getMarque() {
        return marque;
    }

    public Date getDateD() {
        return dateD;
    }

    public String getLieuD() {
        return lieuD;
    }

    public String getLieuA() {
        return lieuA;
    }

    public String isDispo() {
        return dispo;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public String getNumTel() {
        return numTel;
    }
 
    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setDateD(Date dateD) {
        this.dateD = dateD;
    }

    public void setLieuD(String lieuD) {
        this.lieuD = lieuD;
    }

    public void setLieuA(String lieuA) {
        this.lieuA = lieuA;
    }

    public void setDispo(String dispo) {
        this.dispo = dispo;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    @Override
    public String toString() {
        return "OffreCovoiturage{" + "id=" + id + ", matricule=" + matricule + ", marque=" + marque + ", dateD=" + dateD + ", lieuD=" + lieuD + ", lieuA=" + lieuA + ", dispo=" + dispo + ", nbPlace=" + nbPlace + ", numTel=" + numTel + ", distance=" + distance+ '}';
    }

}
    
