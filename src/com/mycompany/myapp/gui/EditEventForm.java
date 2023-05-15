/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import java.util.Date;

import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.services.EventService;

/**
 *
 * @author waelb
 */

public class EditEventForm extends Form {
    private Evenement evenement;
    private TextField tfLieu;
    private TextField tfDescription;
    private Picker datePicker;
    private Picker datePickerFin;
    private TextField tfTitre;
    private TextField tfNbParticipants;

    public EditEventForm(Form previous, Evenement evenement) {
        setTitle("Modifier Événement");
        setLayout(BoxLayout.y());

        this.evenement = evenement;

        tfLieu = new TextField(evenement.getLieu(), "Lieu de l'événement");
        tfDescription = new TextField(evenement.getDescription(), "Description de l'événement");
        datePicker = new Picker();
        datePicker.setDate(evenement.getDate());
        datePickerFin = new Picker();
        datePickerFin.setDate(evenement.getDatefin());
        tfTitre = new TextField(evenement.getTitre(), "Titre de l'événement");
        tfNbParticipants = new TextField(String.valueOf(evenement.getNbparticipations()), "Nombre de participants");

        addAll(tfLieu, tfDescription, datePicker, datePickerFin, tfTitre, tfNbParticipants);

        // Ajouter un bouton pour enregistrer les modifications
        Button modifier = new Button("Modifier");
        modifier.addActionListener(e -> {
            // Mettre à jour les données de l'événement
            evenement.setLieu(tfLieu.getText());
            evenement.setDescription(tfDescription.getText());
            evenement.setDate(datePicker.getDate());
            evenement.setDatefin(datePickerFin.getDate());
            evenement.setTitre(tfTitre.getText());
            evenement.setNbparticipations(Integer.parseInt(tfNbParticipants.getText()));
            
            // Enregistrer les modifications dans la base de données
            new EventService().updateEvenement(evenement);

            // Retourner à la liste des événements
            new ListEventForm(previous).showBack();
        });
        add(modifier);

        // Ajouter un bouton pour annuler les modifications
        Button retourButton = new Button("retour");
        retourButton.addActionListener(e -> new ListEventForm(previous).showBack());
        add(retourButton);
    }
}



