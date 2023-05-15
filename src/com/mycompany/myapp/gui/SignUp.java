/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceTask;


/**
 *
 * @author Andrew
 */
public class SignUp extends Form{
    
    public SignUp(Form previous){
        
        setTitle("AddTask");
        setLayout(BoxLayout.y());
        TextField tfemail = new TextField("","email");
        TextField tfpassword = new TextField("","password");
        TextField tfnom = new TextField("","nom");
        TextField tfprenom = new TextField("","prenom");
        TextField tfage = new TextField("","age");
        
        Button btnadd =new Button("Sign Up");
        
        
        btnadd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                if(tfemail.getText().length()==0){
                    Dialog.show("Alert","please fill all the fiels","ok",null);
                }
                else{
                    
                    
                   
                   
                    User tas = new User(tfemail.getText(),tfpassword.getText(),tfnom.getText(),tfprenom.getText(),Integer.parseInt(tfage.getText()));
                    if(ServiceTask.getinstance().addTask(tas)){
                         Dialog.show("Alert","added successfuly","ok",null);
                    }else {
                         Dialog.show("Alert","Err while connecting to server ","ok",null);
                    }
                   
                }
            }
        });
        addAll(tfemail,tfpassword,tfnom,tfprenom,tfage,btnadd);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_LEFT, ev->previous.show());
     
        
    }
    
}
