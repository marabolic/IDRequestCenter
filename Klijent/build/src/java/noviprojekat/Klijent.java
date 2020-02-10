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
    
    @Resource(lookup = "jms/__defaultConnectionFactory")
    static ConnectionFactory connectionFactory;
    @Resource(lookup = "myQueue")
    static Queue queue;
    static JMSContext context;
    static JMSProducer producer;
    
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
        System.out.println("max ID:" + maxID.get(0));
        
        
        String id = maxID.get(0);
        long intid = Long.parseLong(id);
        intid++;
        id = intid + "";
        System.out.println("myID: " + id);
        
        Documentrequest dr = createRequestObject(id);
        
        Main.em.getTransaction().begin();
        Main.em.persist(dr);
        Main.em.flush();
        Main.em.getTransaction().commit();
        
        
        ObjectMessage msg = context.createObjectMessage(dr);
        producer.send(queue, msg);
        
    }
   
    
    void statusRequest(){
        
    }
    
    
    
    
}