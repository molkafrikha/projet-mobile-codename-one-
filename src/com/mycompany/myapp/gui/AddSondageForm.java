/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Sondage;
import com.mycompany.myapp.services.SondageServices;

/**
 *
 * @author Nihel
 */
public class AddSondageForm  extends Form {
    
    public AddSondageForm(Form previous) {
        setTitle("Ajouter Sondage");
        setLayout(BoxLayout.y());

        Label sujet = new Label("Sujet :");
        TextField tfSujet = new TextField("", "Sujet");
        Label categorie = new Label("categorie :");
        TextField tfcategorie = new TextField("", "categorie");

        Button btnValider = new Button("Ajouter sondage");

        
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfSujet.getText().length()==0) )
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                   Sondage s = new Sondage();
                    s.setSujet(tfSujet.getText().toString());
                    s.setCategorie(tfcategorie.getText().toString());
                     if( SondageServices.getInstance().addSondage(s))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    
                }
               new ListSondageForm(previous).showBack(); 
                
            }
            

           
        });
        
        addAll(sujet,tfSujet,categorie, tfcategorie ,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

  
    
    
    
}
