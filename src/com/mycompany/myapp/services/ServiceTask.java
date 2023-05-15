/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.List;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


/**
 *
 * @author Andrew
 */
public class ServiceTask {
    ArrayList<User> tasks ;
    ConnectionRequest req;
    
    public boolean resultOk;
    //2  creer un attribut de type de la classe en question (static)
    public static ServiceTask instance = null;
    
    
    //Singleton => Design Pattern qui permet de creer une seule instance d'un objet 
    
    
    //1 rendre le constructeur private
    private ServiceTask() {
        req = new ConnectionRequest();
    }
    
    
    //3 la methode qu'elle va ramplacer le constructeur 
    public static ServiceTask getinstance(){
        if(instance == null){
            instance = new ServiceTask();    
        }
        return instance;
    }
    
    
    
    
    
    
    
    
    
    
    //methode d'ajout
    public boolean addTask(User t){
        String email=t.getEmail();
        String password = t.getPassword();
        String nom=t.getNom();
        String prenom=t.getPrenom();
        int age=t.getAge();
        
        
        String url = "http://127.0.0.1:8000/signUp/mobile?email="+email+"&password="+password+"&nom="+nom+"&prenom="+prenom+"&age="+age;
        
        
        req.setUrl(url);
        //GET =>
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200; //si le code return 200 
                //
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
        
        
        
    }
     public void signin(TextField email, TextField password) {
    String url = "http://127.0.0.1:8000/login/mobile";
    req = new ConnectionRequest(url, false);
    req.addArgument("email", email.getText());
    req.addArgument("password", password.getText());
    
    req.addResponseListener((e) ->{
        JSONParser j = new JSONParser();
        String json = new String(req.getResponseData()) + "";
        
        try {
            if(json.equals("failed")) {
                Dialog.show("Echec d'authentification", "Username ou mot de passe éronné", "OK", null);
            } else {
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                float id = Float.parseFloat(user.get("id").toString());
                SessionManager.setId((int)id);
                SessionManager.setPassowrd(user.get("password").toString());
                SessionManager.setEmail(user.get("email").toString());
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    });
    
    NetworkManager.getInstance().addToQueueAndWait(req);
}
    

    /*
    public ArrayList<Task> parseTasks(String jsonText) {
        try {
            tasks = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Task t = new Task();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                t.setStatus(((int) Float.parseFloat(obj.get("status").toString())));
                if (obj.get("name") == null) {
                    t.setName("null");
                } else {
                    t.setName(obj.get("name").toString());
                }
                tasks.add(t);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return tasks;
    }
    
    
    
    //methode d'affichage
     public ArrayList<Task> getAllTasks(){
          String url = Statics.BASE_URL+"get/";
          req.setUrl(url);
          req.setPost(false);
          req.addResponseListener(new ActionListener<NetworkEvent>() {
              @Override
              public void actionPerformed(NetworkEvent evt) {
                  tasks = parseTasks(new String(req.getResponseData()));
                  req.removeResponseListener(this);
              }
          });
          NetworkManager.getInstance().addToQueueAndWait(req);
         
         
         return tasks;
     }
    .*/
}
