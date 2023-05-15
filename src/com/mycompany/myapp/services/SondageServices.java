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
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Sondage;

import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;


/**
 *
 * 
 */
public class SondageServices {
     public ArrayList<Sondage> sondages;

    public static SondageServices instance = null;
    public boolean resultOK;
    private ConnectionRequest con;

    public SondageServices() {
        con = new ConnectionRequest();
    }

    public static SondageServices getInstance()
    {
        if(instance==null)
        {
            instance = new SondageServices();
        }
        return instance ;
    }
    
    public boolean addSondage(Sondage r) {

        String sujet = r.getSujet();
        String categorie = r.getCategorie();

        String url = Statics.BASE_URL + "/sondage/newSondagejson?sujet=" + sujet + "&categorie=" + categorie  ;

        con.setUrl(url);
       con.setPost(false);

       con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = con.getResponseCode() == 200; 
                con.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return resultOK;
    }
    
     public void EditSondage(Sondage sondage) {
        String url = Statics.BASE_URL + "/sondage/editjson" + sondage.getSondage_id()+"?sujet=" +sondage.getSujet() +"&categorie=" + sondage.getCategorie();
        con.setUrl(url);
        con.setPost(false);
       
        con.addArgument("sondageId", String.valueOf(sondage.getSondage_id()));
        con.addArgument("sujet", sondage.getSujet());
        con.addArgument("categorie", sondage.getCategorie());
        
      

        con.addResponseListener((NetworkEvent evt) -> {
            byte[] data = (byte[]) con.getResponseData();
            String s = new String(data);
            System.out.println("Result: " + s);
        });

        NetworkManager.getInstance().addToQueue(con);
    }
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     public boolean  deleteSondage(int id){
       String url = Statics.BASE_URL + "/sondage/deleteSondagejson"  + id ;

        con.setUrl(url);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
               con.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        
        return resultOK;
      
      
      }
     
     public ArrayList<Sondage> parseSondages(String jsonText) throws ParseException {
        try {
            sondages = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> reclamationsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) reclamationsListJson.get("root");
            for (Map<String, Object> obj : list) {
                Sondage r = new Sondage();
                float sondage_id = Float.parseFloat(obj.get("sondageId").toString());
                r.setSondage_id((int) sondage_id);
                r.setSujet((String) obj.get("sujet".toString()));
                r.setCategorie((String) obj.get("categorie".toString()));
                

               sondages.add(r);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return sondages;
    }
     
     public ArrayList<Sondage> getAllSondages() {
        String url = Statics.BASE_URL + "/sondage/indexjson";
        con.setUrl(url);
        con.setPost(false);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    sondages = parseSondages(new String(con.getResponseData()));
                } catch (ParseException ex) {
                    
                }
                con.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return sondages;
    }
    

    
}