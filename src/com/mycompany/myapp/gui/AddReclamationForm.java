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
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ReclamationService;

/**
 *
 * @author Nihel
 */
public class AddReclamationForm extends Form  {
    public AddReclamationForm(Form previous) {
        setTitle("Ajouter Reclamation");
        setLayout(BoxLayout.y());

        Label intitule = new Label("Intitule :");
        TextField tfIntitule = new TextField("", "Intitule");
        Label contenu = new Label("Contenu :");
        TextField tfContenu = new TextField("", "Contenu");

        Button btnValider = new Button("Ajouter Reclamation");

        Label date = new Label("Date :");
        Picker pickerDate = new Picker();
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfIntitule.getText().length()==0) )
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                   Reclamation r = new Reclamation();
                    r.setIntitule(tfIntitule.getText().toString());
                    r.setContenu(tfContenu.getText().toString());
                    r.setDate(pickerDate.getDate());
                     if( ReclamationService.getInstance().addReclamation(r))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    
                }
               new ListReclamationForm(previous).showBack(); 
                
            }
            

           
        });
        
        addAll(intitule,tfIntitule,contenu, tfContenu ,date ,pickerDate , btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

  
    
    
    
}
