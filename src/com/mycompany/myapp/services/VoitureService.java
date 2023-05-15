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
import com.mycompany.myapp.entities.Voiture;


import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import java.util.Date;
import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 *
 * @author USER
 */
public class VoitureService {
    
    
  public ArrayList<Voiture> voitures;

    public static  VoitureService instance = null;
    public boolean resultOK;
    private ConnectionRequest voi;

    public VoitureService() {
        voi = new ConnectionRequest();
    }

    public static VoitureService getInstance()
    {
        if(instance==null)
        {
            instance = new VoitureService();
        }
        return instance ;
    }
    
    public boolean addVoiture(Voiture v) {

        String matricule = v.getMatricule();
        String puissance = v.getPuissance();
        String marque = v.getMarque();
        int nbplaces = v.getNbplaces();
        int kilometrage = v.getKilometrage();
        String color = v.getColor();
        Date dateDA = v.getDateAssurance();
        Date dateDV = v.getDateDVidange();
        

        String url = Statics.BASE_URL + "/newjson?matricule=" + matricule + "&puissance=" + puissance + "&kilometrage=" + kilometrage + "&nbplaces=" + nbplaces  +"&nbplaces=" + nbplaces   +"&dateDA=" + dateDA +"&dateDV=" + dateDV  +"&color=" + color + "&marque=" + marque ;

        voi.setUrl(url);
        voi.setPost(false);

        voi.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = voi.getResponseCode() == 200; 
                voi.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(voi);
        return resultOK;
    }
    
    
     
     public boolean  deleteVoiture(int id){
       String url = Statics.BASE_URL + "/voiture/deleteVoiturejson/" +id;

        voi.setUrl(url);

        voi.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                voi.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(voi);
        return resultOK;
      
      
      }
     
     public ArrayList<Voiture> parseVoiture(String jsonText) throws ParseException {
        try {
            voitures = new ArrayList<>();
            JSONParser j = new JSONParser();
           Map<String, Object> voitureListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) voitureListJson.get("root");
            for (Map<String, Object> obj : list) {
                Voiture v = new Voiture();
                float id = Float.parseFloat(obj.get("id").toString());
                //float puissance = Float.parseFloat();
                v.setId_voiture((int) id);
                v.setMatricule((String) obj.get("matricule"));
                v.setPuissance(  obj.get("puissance").toString());
                // v.setKilometrage((int) obj.get("kilometrage"));
                //v.setNbplaces((int) obj.get("nbplaces"));
                v.setColor((String) obj.get("color"));
                v.setMarque((String) obj.get("marque"));
               
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date dateDA = format.parse(obj.get("dateAssurance").toString());
                Date dateDV = format.parse(obj.get("dateDVidange").toString());
                
                v.setDateAssurance(dateDA);
                v.setDateDVidange(dateDV);

                voitures.add(v);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return voitures;
    }

      
     public ArrayList<Voiture> getAllVoiture() {
        String url = Statics.BASE_URL + "/voiture/JSON";
        voi.setUrl(url);
        voi.setPost(false);
        voi.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    voitures = parseVoiture
         (new String(voi.getResponseData()));
                } catch (ParseException ex) {
                    
                }
                voi.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(voi);
        return voitures;
    }
    

    

    


    
}