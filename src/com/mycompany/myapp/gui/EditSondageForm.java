/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Sondage;

import com.mycompany.myapp.services.SondageServices;


/**
 *
 * @author Nihel
 */
public class EditSondageForm extends Form{
    private Sondage sondage; 

    private TextField tfSujet;
    private TextField tfCategorie;
 

    public EditSondageForm(Form previous, Sondage sondage) {
        setTitle("Modifier Sondage");
        setLayout(BoxLayout.y());

        this.sondage = sondage;
        
        tfSujet = new TextField(sondage.getSujet(), "sujet");
        tfCategorie = new TextField(sondage.getCategorie(), "categorie");
        
      

        addAll( tfSujet, tfCategorie);

        // Ajouter un bouton pour enregistrer les modifications
        Button modifier = new Button("Modifier");
        modifier.addActionListener(e -> {
            // Mettre à jour les données de la rec
            sondage.setSujet(tfSujet.getText());
            sondage.setCategorie(tfCategorie.getText());
         

            // Enregistrer les modifications dans la base de données
           SondageServices.getInstance().EditSondage(sondage);

            // Retourner à la liste des promos
            new ListSondageForm(previous).showBack();
        });
        add(modifier);

        // Ajouter un bouton pour annuler les modifications
        Button cancelButton = new Button("Annuler");
        cancelButton.addActionListener(e -> new ListSondageForm(previous).showBack());
        add(cancelButton);
    }
    
}
