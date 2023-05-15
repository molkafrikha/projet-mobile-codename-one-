/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Nihel
 */
public class HomeForm extends Form{
    public HomeForm(Form previous) {
        
        setTitle("page home");
        setLayout(BoxLayout.y());
        
        Button btnAddReclamation = new Button("Ajouter Reclamation");
        Button btnListReclamation  = new Button("Liste reclamations");
        Button btnAddAvis  = new Button("Ajouter avis");
        Button btnListAvis  = new Button("Liste des avis");
        Button btnAddSondage = new Button("Ajouter  Sondage");
        Button btnListSondage  = new Button("Liste Sondages");
        Button btnListEvents  = new Button("Liste des événements");
        Button btnaddEvent  = new Button("Ajouter événement");
        Button btnlistVoiture  = new Button("Liste voiture");
        Button btnlistOffre  = new Button("Liste offre");
        
        
        btnaddEvent.addActionListener(e-> new AddEventForm(this).show());
        btnListEvents.addActionListener(e-> new ListEventForm(this).show());
        
        btnListSondage.addActionListener(e-> new ListSondageForm(this).show());
        btnAddSondage.addActionListener(e-> new AddSondageForm(this).show());
        
        btnlistOffre.addActionListener(e-> new ListeOffreCovoiturage(this).show());
        btnListReclamation.addActionListener(e-> new ListReclamationForm(this).show());
        btnAddReclamation.addActionListener(e-> new AddReclamationForm(this).show());
       btnlistVoiture.addActionListener(e-> new ListVoitureForm(this).show());
        
        addAll(btnListReclamation,btnAddReclamation,btnListSondage,btnAddSondage,btnListEvents,btnaddEvent,btnlistVoiture,btnlistOffre);
        
       
        
    }
    
    
}
