/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.services.EventService;
import java.util.Date;


/**
 *
 * @author waelb
 */
public class AddEventForm extends Form{
     public AddEventForm(Form previous) {
        setTitle("Ajouter Event");
        setLayout(BoxLayout.y());
        
        Label titre = new Label("Titre :");
        TextField tfTitre = new TextField("", "titre");
        
        Label lieu = new Label("Lieu :");
        TextField tfLieu = new TextField("", "lieu");
        
        Label date = new Label("Date :");
        Picker pickerDate = new Picker();
        
        Label datefin = new Label("Date fin:");
        Picker pickerDate1 = new Picker();
        
        Label description = new Label("Description :");
        TextField tfdescription = new TextField("", "description");
        
        Button btnValider = new Button("Ajouter Evenement");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfTitre.getText().length()==0)||(tfdescription.getText().length()==0) ||(pickerDate1.getText().length()==0)||(pickerDate.getText().length()==0)||(tfLieu.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    Evenement a = new Evenement();
                    a.setTitre(tfTitre .getText().toString());
                    a.setLieu(tfLieu .getText().toString());
                    a.setDescription(tfdescription .getText().toString());
                    a.setDate(pickerDate.getDate() );
                    a.setDatefin(pickerDate1.getDate());
                     if( EventService.getInstance().addEvent(a))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    
                }
               new ListEventForm(previous).showBack(); 
                
            }
              });
        
        addAll(titre,tfTitre,lieu,tfLieu,date,pickerDate,datefin,pickerDate1,description,tfdescription , btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    
}
}
