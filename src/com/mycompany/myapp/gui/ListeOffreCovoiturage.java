/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.OffreCovoiturage;
import com.mycompany.myapp.services.OffreCovoiturageService;
import java.util.ArrayList;

/**
 *
 * @author Nadhem
 */
public class ListeOffreCovoiturage extends Form {

    public ListeOffreCovoiturage(Form previous) {
        setTitle("List Covoiturage");
        setLayout(BoxLayout.y());

        Label label = new Label("Liste des offres des covoiturage :");
        add(label);
        ArrayList<OffreCovoiturage> offrecovoiturage = OffreCovoiturageService.getInstance().getAllOffreCovoiturages();

        for (OffreCovoiturage c : offrecovoiturage) {
            addElement(c);
        }

       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    
    }

    public ListeOffreCovoiturage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void addElement(OffreCovoiturage offrecovoiturage) {
        OffreCovoiturageService oc = new OffreCovoiturageService();
    

        Label matricule = new Label("Matricule : " + offrecovoiturage.getMatricule());
        Label marque = new Label("Marque : " + offrecovoiturage.getMarque());
        Label lieuD = new Label("lienD : " + offrecovoiturage.getLieuD());
        Label lieuA = new Label("lieuA : " + offrecovoiturage.getLieuA());
       // Label dispo = new Label("Dispo : " + offrecovoiturage.isDispo());
        Label nbPlace = new Label("NbPlace : " + offrecovoiturage.getNbPlace());
        Label numTel = new Label("numTel : " + offrecovoiturage.getNumTel());
        Label distance = new Label("distance : " + offrecovoiturage.getDistance());
        
        
        Button detail = new Button("Détails");
        detail.addActionListener(e -> {
            Dialog.show("Détails", "matricule :"+offrecovoiturage.getMatricule()+"\nmarque : "+ offrecovoiturage.getMarque()
                    +"\ndated :"+offrecovoiturage.getDateD().toString()
                    +"\nlieud : "+ offrecovoiturage.getLieuD()+"\nlieua : "+ offrecovoiturage.getLieuA()+"\ndispo : "+ offrecovoiturage.isDispo()
                    +"\nnbplace : "+ offrecovoiturage.getNbPlace() + "\nnumtel : "+ offrecovoiturage.getNumTel()
                    + "\ndistance : "+ offrecovoiturage.getDistance()
                    , "OK", null);
        });
        addAll(matricule,marque,lieuD,lieuA,nbPlace,numTel,distance,detail);
        
       

    }
    
    
    
}
