/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.services;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 *
 * @author waelb
 */
public class EventService {
     public ArrayList<Evenement> events;

    public static EventService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    public EventService(){
        req = new ConnectionRequest();
    }
    public static EventService getInstance()
    {
        if(instance==null)
        {
            instance = new EventService();
        }
        return instance ;
    }
    public ArrayList<Evenement> parseEvents(String jsonText) throws ParseException {
        try {
            events = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> eventsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) eventsListJson.get("root");
            for (Map<String, Object> obj : list) {
                Evenement r = new Evenement();
                float id = Float.parseFloat(obj.get("id").toString());
                r.setId((int) id);
                r.setDescription((String) obj.get("description".toString()));
                r.setLieu((String) obj.get("lieu".toString()));
                r.setTitre((String) obj.get("titre".toString()));
                float nb = Float.parseFloat(obj.get("nbparticipants").toString());
                r.setNbparticipations((int) nb);

                
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = format.parse(obj.get("date").toString());
                Date datefin = format.parse(obj.get("datefin").toString());
                r.setDate(date);
                r.setDatefin(datefin);

                events.add(r);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return events;
    }
    
    public ArrayList<Evenement> getAllEvents() {
        String url = Statics.BASE_URL + "/AllEventsJson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    events = parseEvents(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;
    }
    public boolean addEvent(Evenement e) {

        String lieu = e.getLieu();
        String description = e.getDescription();
        String titre=e.getTitre();
        int id = e.getId();
        int nbparticipants=e.getNbparticipations();
        Date date = e.getDate();
        Date datefin=e.getDatefin();

        String url = Statics.BASE_URL + "/addevenementJSON?lieu=" + lieu + "&description=" + description + "&date=" + date + "&datefin="+datefin+"&titre="+titre ;

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
    public void updateEvenement(Evenement e) {
        String url = Statics.BASE_URL + "/modifierEveJSON/" + e.getId()+"?lieu=" + e.getLieu() + "&description=" + e.getDescription() + "&date=" + e.getDate() + "&datefin="+e.getDatefin()+"&titre="+e.getTitre()+"&nbparticipants="+e.getNbparticipations() ;
        req.setUrl(url);
        req.setPost(false);
       
        req.addArgument("id", String.valueOf(e.getId()));
        req.addArgument("lieu", e.getLieu());
        req.addArgument("description", e.getDescription());
        req.addArgument("date", e.getDate().toString());
        req.addArgument("datefin", e.getDatefin().toString());
        req.addArgument("titre", e.getTitre());
        req.addArgument("nbparticipants", String.valueOf(e.getNbparticipations()));
        

        req.addResponseListener((NetworkEvent evt) -> {
            byte[] data = (byte[]) req.getResponseData();
            String s = new String(data);
            System.out.println("Result: " + s);
        });

        NetworkManager.getInstance().addToQueue(req);
    }
    
    public boolean  deleteEvent(int id){
       String url = Statics.BASE_URL + "/deletereventJSON/" +id;

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
}
