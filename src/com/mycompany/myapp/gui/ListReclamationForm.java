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
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ReclamationService;
import java.util.ArrayList;

/**
 *
 * @author Nihel
 */
public class ListReclamationForm extends Form {

    public ListReclamationForm(Form previous) {
        setTitle("List Reclamation");
        setLayout(BoxLayout.y());

        Label label = new Label("Liste des reclamations :");
        add(label);
        ArrayList<Reclamation> reclamation = ReclamationService.getInstance().getAllReclamations();

        for (Reclamation r : reclamation) {
            addElement(r);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

    public void addElement(Reclamation reclamation) {
        ReclamationService ps = new ReclamationService();
    

        Label intitule = new Label("Intitule : " + reclamation.getIntitule());
        Label contenu = new Label("Contenu : " + reclamation.getContenu());
        
        Button detail = new Button("Détails");
        detail.addActionListener(e -> {
            Dialog.show("Détails", "Intitule :"+reclamation.getIntitule()+"\nContenu : "+ reclamation.getContenu()
                    +"\nDate :"+reclamation.getDate().toString()
                    , "OK", null);
        });
        
        Button supprimer =new Button("Supprimer");
            supprimer.addActionListener(e -> {
               Dialog alert = new Dialog("Confirmation");
                SpanLabel message = new SpanLabel("Etes-vous sur de vouloir supprimer cette réclamation?");
                alert.add(message);
                Button ok = new Button("Confirmer");
                Button cancel = new Button(new Command("Annuler"));
                //User clicks on ok to delete account
                ok.addActionListener((ActionListener) (ActionEvent evt) -> {
                    ps.deleteReclamation(reclamation.getId());
                   
                    alert.dispose();
                    refreshTheme();
                     
               });
                alert.add(cancel);
                alert.add(ok);
                alert.showDialog();
                
                
                
                
               
             });
            
            
         Button modifier = new Button("Modifier");
         modifier.addActionListener(e-> new EditReclamationForm(this,reclamation).show());
        

        
      
        addAll(intitule,contenu,detail,modifier,supprimer);
        
       

    }
    

}
