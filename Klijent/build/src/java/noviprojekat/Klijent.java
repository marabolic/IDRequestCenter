/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package noviprojekat;

import entiteti.Documentrequest;
import gui.Gui;
import java.io.*;
import java.io.IOException;
import java.net.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.*;
import javax.json.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 *
 * @author makib
 */
public class Klijent {
    Gui gui;
    
   
    
    public Klijent(){
       
        gui = new Gui(this);
       
    }
    
    
    public Documentrequest createRequestObject(String id){
        Documentrequest dr = new Documentrequest(id);
        dr.setJmbg(gui.getJMBG());
        dr.setIme(gui.getIme());
        dr.setPrezime(gui.getPrezime());
        dr.setImeMajke(gui.getImeMajke());
        dr.setImeOca(gui.getImeOca());
        dr.setPrezimeMajke(gui.getPrezimeMajke());
        dr.setPrezimeOca(gui.getPreizmeOca());
        dr.setNacionalnost(gui.getNacionalnost());
        dr.setProfesija(gui.getProfesija());
        dr.setOpstinaPrebivalista(gui.getOpstina());
        dr.setUlicaPrebivalista(gui.getUlica());;
        dr.setBrojPrebivalista(gui.getBroj());
        dr.setPol(gui.getPol());
        dr.setBracnoStanje(gui.getBracnoStanje());
        dr.setDatumRodjenja(gui.getDatum());
        dr.setStatus("kreiran");
       
        return dr;
    }
    
    
    public void createRequest(){
        try {
            String termin = //"http://virtserver.swaggerhub.com/petar.noki0x60/ETFTask/1.0.0/terminCentar/" +
                    "http://collabnet.netset.rs:8081/is/terminCentar/checkTimeslotAvailability" + "?regionalniCentarId=" + "17614" + "&termin=" + "2020-02-24T16:45:00";
            
            //check if available
            
            URL url = new URL(termin);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            
            int respCode = con.getResponseCode();
            if (respCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                JsonReader reader = Json.createReader(in); 
                
                JSONParser par = new JSONParser();
                JSONObject obj = (JSONObject)par.parse(in);
                
                System.out.println("ispis: " + obj.get("dostupnost"));
                if ((boolean)obj.get("dostupnost") != true){
                    System.out.println("unutra");
                    return;
                }
                //System.err.println("ispis: " + arr.get(1));
                //System.err.println("ispis: " + arr.get(2));
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        List<String> maxID = Main.em.createQuery("select max(d.id) from Documentrequest d").getResultList();
        long maxint;
        if (maxID.get(0) != null) {
            maxint = Math.max(Long.parseLong(maxID.get(0)), 171170000050l);
        }
        else maxint = 171170000040l;
        
        
        System.out.println("max ID:" + maxID.get(0));
        
        
        maxint++;
        String id = maxint + "";
        System.out.println("myID: " + id);
        
        Documentrequest dr = createRequestObject(id);
        
        Main.em.getTransaction().begin();
        Main.em.persist(dr);
        Main.em.flush();
        Main.em.getTransaction().commit();
        
        
        dr.setStatus("uProdukciji");
        ObjectMessage msg = Main.context.createObjectMessage(dr);
        Main.producer.send(Main.queue, msg);
        
    }
   
    
    public String statusRequest(String id) throws ProtocolException{
        List<Documentrequest> doc = Main.em.createNamedQuery("Documentrequest.findById", Documentrequest.class).setParameter("id", id).getResultList();
        String ret = "";
       
        if (doc.size() > 0){ //exists in local database
            Documentrequest dr = doc.get(0);
            
            if (dr.getStatus().equals("uProdukciji")){
                try {
                    String termin =  "http://collabnet.netset.rs:8081/is/persoCentar/" + id;
                    

                    URL url = new URL(termin);
                    HttpURLConnection con = (HttpURLConnection)url.openConnection();
                    con.setRequestMethod("GET");
                    
                    int respCode = con.getResponseCode();
                    if (respCode == 200){
                        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        JsonReader reader = Json.createReader(in); 
                
                        JSONParser par = new JSONParser();
                        JSONObject obj = (JSONObject)par.parse(in);

                       
                        if (obj.get("status").equals("proizveden")){
                            Main.em.getTransaction().begin();
                            dr.setStatus("proizveden");
                            Main.em.flush();
                            Main.em.getTransaction().commit();
                            gui.setDeliverEnable(true);
                        }
                        else{
                            gui.setDeliverEnable(false);
                        }
                    }
                    
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Klijent.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Klijent.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(Klijent.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            ret = dr.getStatus();
            if (ret.equals("proizveden")){
                gui.setDeliverEnable(true);
            }
        }
        return ret;
    }
    
    public void deliver(String id){
         List<Documentrequest> doc = Main.em.createNamedQuery("Documentrequest.findById", Documentrequest.class).setParameter("id", id).getResultList();
         if (doc.size() > 0){ 
            Documentrequest dr = doc.get(0);
            Main.em.getTransaction().begin();
            dr.setStatus("urucen");
            Main.em.flush();
            Main.em.getTransaction().commit();
            gui.setDeliverEnable(false);
         }
    }
    
    
    
    
}
