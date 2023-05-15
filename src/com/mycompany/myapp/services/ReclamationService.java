/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Reclamation;


import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import java.util.Date;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nihel
 */
public class ReclamationService {
     public ArrayList<Reclamation> reclamations;

    public static ReclamationService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ReclamationService() {
        req = new ConnectionRequest();
    }

    public static ReclamationService getInstance()
    {
        if(instance==null)
        {
            instance = new ReclamationService();
        }
        return instance ;
    }
    
    public boolean addReclamation(Reclamation r) {

        String intitule = r.getIntitule();
        String contenu = r.getContenu();
        Date date = r.getDate();

        String url = Statics.BASE_URL + "/addreclamationJSON?intitule=" + intitule + "&contenu=" + contenu + "&date=" + date ;

        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
     public void updateReclamation(Reclamation reclamation) {
        String url = Statics.BASE_URL + "/modifierRecJSON/" + reclamation.getId()+"?intitule=" +reclamation.getIntitule() +"&contenu=" + reclamation.getContenu();
        req.setUrl(url);
        req.setPost(false);
       
        req.addArgument("id", String.valueOf(reclamation.getId()));
        req.addArgument("intitule", reclamation.getIntitule());
        req.addArgument("contenu", String.valueOf(reclamation.getContenu()));
        req.addArgument("date", reclamation.getDate().toString());

        req.addResponseListener((NetworkEvent evt) -> {
            byte[] data = (byte[]) req.getResponseData();
            String s = new String(data);
            System.out.println("Result: " + s);
        });

        NetworkManager.getInstance().addToQueue(req);
    }
     
     public boolean  deleteReclamation(int id){
       String url = Statics.BASE_URL + "/deletereclamation/" +id;

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                req.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
      
      
      }
     
     public ArrayList<Reclamation> parseReclamations(String jsonText) throws ParseException {
        try {
            reclamations = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> reclamationsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) reclamationsListJson.get("root");
            for (Map<String, Object> obj : list) {
                Reclamation r = new Reclamation();
                float id = Float.parseFloat(obj.get("id").toString());
                r.setId((int) id);
                r.setIntitule((String) obj.get("intitule".toString()));
                r.setContenu((String) obj.get("contenu".toString()));
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = format.parse(obj.get("date").toString());
                r.setDate(date);

                reclamations.add(r);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamations;
    }
     
     public ArrayList<Reclamation> getAllReclamations() {
        String url = Statics.BASE_URL + "/reclamationJSON";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    reclamations = parseReclamations(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reclamations;
    }
    

    
}
