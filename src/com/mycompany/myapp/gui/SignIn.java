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
public class SignIn extends Form{
    
    public SignIn(Form previous){
        
        setTitle("AddTask");
        setLayout(BoxLayout.y());
        TextField tfemail = new TextField("","email");
        TextField tfpassword = new TextField("","password");
   
        
        Button btnadd =new Button("Sign In");
        User user =new User();
        
       btnadd.addActionListener(e-> new HomeForm(this).show());
     
         addAll(tfemail,tfpassword,btnadd);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_LEFT, ev->previous.show());
    }
    
}
