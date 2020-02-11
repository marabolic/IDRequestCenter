/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import entiteti.Documentrequest;
import java.io.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.json.simple.JSONObject;

/**
 *
 * @author makib
 */
public class Main {

     static EntityManagerFactory emf;
    public static EntityManager em;
    
    @Resource(lookup = "jms/__defaultConnectionFactory")
    static ConnectionFactory connectionFactory;
    
    @Resource(lookup = "myQueue")
    static Queue queue;
    
    static JMSContext context;
    static JMSProducer producer;
    static JMSConsumer consumer;
    
    
    public static void main(String[] args) {
     
        context = connectionFactory.createContext();
        
        consumer = context.createConsumer(queue);
        producer = context.createProducer();
        
        emf = Persistence.createEntityManagerFactory("PU");
        em = emf.createEntityManager();
        
        
        while (true){
            Message msg = consumer.receive();
            if (msg instanceof ObjectMessage){
                
                try {
                    ObjectMessage omsg = (ObjectMessage)msg;
                    System.out.println("Izvukao: " + omsg.getObject().toString());
                    
                    Documentrequest dr = (Documentrequest) omsg.getObject();
                    
                    JSONObject obj = new JSONObject();
                    obj.put("id",dr.getId());
                    obj.put("JMBG",dr.getJmbg());
                    obj.put("ime",dr.getIme());
                    obj.put("prezime", dr.getPrezime());
                    obj.put("imeMajke",dr.getImeMajke());
                    obj.put("imeOca",dr.getImeOca());
                    obj.put("prezimeMajke", dr.getPrezimeMajke());
                    obj.put("prezimeOca", dr.getPrezimeOca());
                    obj.put("pol",dr.getPol());
                    obj.put("datumRodjenja",dr.getDatumRodjenja());
                    obj.put("nacionalnost",dr.getNacionalnost());
                    obj.put("profesija",dr.getProfesija());
                    obj.put("bracnoStanje",dr.getBracnoStanje());
                    obj.put("opstinaPrebivalista",dr.getOpstinaPrebivalista());
                    obj.put("ulicaPrebivalista",dr.getUlicaPrebivalista());
                    obj.put("brojPrebivalista",dr.getBrojPrebivalista());
                    
                    
                    
                    String termin = "http://collabnet.netset.rs:8081/is/persoCentar/submit";

            
                    URL url = new URL(termin);
                    
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");
                    
                    con.setDoOutput(true);
                    con.setRequestProperty("Content-Type", "application/json");
                    
                    OutputStream os = con.getOutputStream();
                    os.write(obj.toString().getBytes());
                    os.flush();
                    os.close();
                    
                    
                    
		int respCode = con.getResponseCode();
		

		if (respCode == 200) { 
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
                        }
			in.close();

                        List<Documentrequest> doc = em.createNamedQuery("Documentrequest.findById", Documentrequest.class).setParameter("id", dr.getId()).getResultList();
                        
                        em.getTransaction().begin();
                        doc.get(0).setStatus("uProdukciji");
                        em.flush();
                        em.getTransaction().commit();
                        
			System.out.println(response.toString());
		}
                else{
                    ObjectMessage om = context.createObjectMessage(dr);
                    producer.send(queue, om);
                }
                    
                    
                    
                    
                } catch (JMSException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
    }
}
