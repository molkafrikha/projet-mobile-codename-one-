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
import com.mycompany.myapp.entities.Sondage;
import com.mycompany.myapp.services.SondageServices;
import java.util.ArrayList;

/**
 *
 * @author Nihel
 */
public class ListSondageForm extends Form{

   public ListSondageForm(Form previous) {
        setTitle("List Sondage");
        setLayout(BoxLayout.y());

        Label label = new Label("Liste des sondages :");
        add(label);
        ArrayList<Sondage> sondage = SondageServices.getInstance().getAllSondages();

        for (Sondage s : sondage) {
            addElement(s);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

  public void addElement(Sondage sondage) {
        SondageServices ps = new  SondageServices();
    

        Label sujet = new Label("Sujet : " + sondage.getSujet());
        Label categorie = new Label("Categorie : " + sondage.getCategorie());
        
        Button detail = new Button("Détails");
        detail.addActionListener(e -> {
            Dialog.show("Détails", "Sujet :"+sondage.getSujet()+"categorie : "+ sondage.getCategorie()
                   
                    , "OK", null);
        });
        
        Button supprimer =new Button("Supprimer");
            supprimer.addActionListener(e -> {
               Dialog alert = new Dialog("Confirmation");
                SpanLabel message = new SpanLabel("Etes-vous sur de vouloir supprimer ce sondage?");
                alert.add(message);
                Button ok = new Button("Confirmer");
                Button cancel = new Button(new Command("Annuler"));
                //User clicks on ok to delete account
                ok.addActionListener((ActionListener) (ActionEvent evt) -> {
                    ps.deleteSondage(sondage.getSondage_id());
                   
                    alert.dispose();
                    refreshTheme();
                     
               });
                alert.add(cancel);
                alert.add(ok);
                alert.showDialog();
                
                
                
                
               
             });
            
            
         Button modifier = new Button("Modifier");
         modifier.addActionListener(e-> new EditSondageForm(this,sondage).show());
        

        
      
        addAll(sujet,categorie,detail,modifier,supprimer);
        

    }
    
        
       

    }

