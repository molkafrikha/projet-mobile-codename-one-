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
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.List;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.OffreCovoiturage;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.Map;
import com.codename1.l10n.SimpleDateFormat;

/**
 *
 * @author Nadhem
 */
public class OffreCovoiturageService {
    public ArrayList<OffreCovoiturage> offrecovoiturages;
    public static OffreCovoiturageService instance=null;
    private ConnectionRequest req;
    public boolean resultOK;
    public static OffreCovoiturageService getInstance(){
        if(instance==null)
            instance=new OffreCovoiturageService();
        return instance;
    }
    public OffreCovoiturageService(){
        req=new ConnectionRequest();
    }
    public void ajoutOffreCovoiturage(OffreCovoiturage offrecovoiturage) {
    String url = Statics.BASE_URL + "/new?id=" + offrecovoiturage.getId()
                + "&matricule=" + offrecovoiturage.getMatricule()
                + "&marque=" + offrecovoiturage.getMarque()
                + "&dated=" + offrecovoiturage.getDateD()
                + "&lieud=" + offrecovoiturage.getLieuD()
                + "&lieua=" + offrecovoiturage.getLieuA()
                + "&dispo=" + offrecovoiturage.isDispo()
                + "&nbPlace=" + offrecovoiturage.getNbPlace()
                + "&numTel=" + offrecovoiturage.getNumTel()
                + "&distance=" + offrecovoiturage.getDistance();
                 
    req.setUrl(url);
    req.addResponseListener((e) -> {
        String str = new String(req.getResponseData());
        System.out.println("data == " + str);
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    }

   
    
      public boolean  deleteOffreCovoiturage(int id){
       String url = Statics.BASE_URL + "/{id}/delete/" +id;

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
      
    public ArrayList<OffreCovoiturage> parseOffreCovoiturages(String jsonText) throws ParseException {
        try {
             offrecovoiturages = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> offrecovoiturageListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) offrecovoiturageListJson.get("root");
            for (Map<String, Object> obj : list) {
                OffreCovoiturage oc = new OffreCovoiturage();
                float id = Float.parseFloat(obj.get("id").toString());
                float nbnumber = Float.parseFloat(obj.get("nbPlace").toString());
                double distance = Double.parseDouble(obj.get("distance").toString());
                oc.setId((int) id);
                oc.setMatricule((String) obj.get("matricule".toString()));
                oc.setMarque((String) obj.get("marque".toString()));
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date = format.parse(obj.get("dateD").toString());
                oc.setDateD(date);

                oc.setLieuD((String) obj.get("lieud".toString()));
                oc.setLieuA((String) obj.get("lieua".toString()));
                oc.setDispo((String) obj.get("dispo".toString()));
                oc.setNbPlace((int) nbnumber);
                oc.setNumTel((String) obj.get("numero".toString()));
                oc.setDistance((double) distance);
                
               

                offrecovoiturages.add(oc);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return offrecovoiturages;
    }
     
     public ArrayList<OffreCovoiturage> getAllOffreCovoiturages() {
        String url = Statics.BASE_URL + "/offrecovoiturage/offrecovoiturageJSON";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    offrecovoiturages = parseOffreCovoiturages(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return offrecovoiturages;
    }
}

