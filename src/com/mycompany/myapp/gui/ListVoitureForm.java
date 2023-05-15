
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

import com.mycompany.myapp.entities.Voiture;

import com.mycompany.myapp.services.VoitureService;
import java.util.ArrayList;

/**
 *
 * @author Nihel
 */
public class ListVoitureForm extends Form {

    
    
    public ListVoitureForm(Form previous) {
        setTitle("List voiture");
        setLayout(BoxLayout.y());

        Label label = new Label("Liste des voiture :");
        add(label);
        ArrayList<Voiture> voiture = VoitureService.getInstance().getAllVoiture();

        for (Voiture v : voiture) { 
            addElement(v);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

}

   


    public void addElement(Voiture voiture) {
        VoitureService ps = new VoitureService();

        Label matricule = new Label("matricule : " + voiture.getMatricule());
        Label puissance = new Label("puissance : " + voiture.getPuissance());
        Label kilometrage = new Label("kilometrage : " + voiture.getKilometrage());
        Label nbplaces = new Label("nombre  de place  : " + voiture.getNbplaces());
        Label dateda = new Label("date derniere assurance  : " + voiture.getDateAssurance());
        Label dateDv = new Label("date derniere vidange  : " + voiture.getDateDVidange());
        Label color = new Label("couleur  : " + voiture.getColor());
        Label marque= new Label("marque: " + voiture.getMarque());
        
        Button detail = new Button("Détails");
        detail.addActionListener(e -> {
            Dialog.show("Détails", "Matricule :"+voiture.getMatricule()+"\npuissance : "+ voiture.getPuissance()+"\nKilometrage"+voiture.getKilometrage()+"\nNombre de place"+voiture.getNbplaces()+"\ndate derniere assurance  : " + voiture.getDateAssurance()+"\nmarque: " + voiture.getMarque()
                   
                    , "OK", null);
        });
       
        
        
        Button supprimer =new Button("Supprimer");
            supprimer.addActionListener(e -> {
               Dialog alert = new Dialog("Confirmation");
                SpanLabel message = new SpanLabel("Etes-vous sur de vouloir supprimer cette voiture?");
                alert.add(message);
                Button ok = new Button("Confirmer");
                Button cancel = new Button(new Command("Annuler"));
                //User clicks on ok to delete account
                ok.addActionListener((ActionListener) (ActionEvent evt) -> {
                    ps.deleteVoiture(voiture.getId_voiture());    
                    alert.dispose();
                    refreshTheme();                    
               });
                alert.add(cancel);
                alert.add(ok);
                alert.showDialog();      
             });
            
            
            
         Button modifier = new Button("Modifier");
         //modifier.addActionListener(e-> new EditVoitureForm(this,voiture).show());

     
        addAll(matricule , puissance , kilometrage , nbplaces  ,dateda , dateDv , marque , color,supprimer,detail );
        
       
    }
    


}