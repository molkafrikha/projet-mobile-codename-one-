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
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ReclamationService;


/**
 *
 * @author Nihel
 */
public class EditReclamationForm  extends Form{
    private Reclamation reclamation; 

    private TextField tfIntitule;
    private TextField tfContenu;
    private Picker datePicker;

    public EditReclamationForm(Form previous, Reclamation reclamation) {
        setTitle("Modifier Reclamation");
        setLayout(BoxLayout.y());

        this.reclamation = reclamation;
        
        tfIntitule = new TextField(reclamation.getIntitule(), "Intitule");
        tfContenu = new TextField(reclamation.getContenu(), "Contenu");
        datePicker = new Picker();
      

        addAll(tfIntitule, tfContenu, datePicker);

        // Ajouter un bouton pour enregistrer les modifications
        Button modifier = new Button("Modifier");
        modifier.addActionListener(e -> {
            // Mettre à jour les données de la rec
            reclamation.setIntitule(tfIntitule.getText());
            reclamation.setContenu(tfContenu.getText());
         

            // Enregistrer les modifications dans la base de données
            ReclamationService.getInstance().updateReclamation(reclamation);

            // Retourner à la liste des promos
            new ListReclamationForm(previous).showBack();
        });
        add(modifier);

        // Ajouter un bouton pour annuler les modifications
        Button cancelButton = new Button("Annuler");
        cancelButton.addActionListener(e -> new ListReclamationForm(previous).showBack());
        add(cancelButton);
    }
    
}
