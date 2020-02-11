/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package noviprojekat;

import entiteti.Documentrequest;
import gui.Gui;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.persistence.*; 

/**
 *
 * @author makib
 */
public class Main {

    static Klijent k;
    static EntityManagerFactory emf;
    public static EntityManager em;
    
     @Resource(lookup = "jms/__defaultConnectionFactory")
    static ConnectionFactory connectionFactory;
    
    @Resource(lookup = "myQueue")
    static Queue queue;
    static JMSContext context;
    static JMSProducer producer;
    
    public static void main(String[] args) {
       context = connectionFactory.createContext();
       producer = context.createProducer();
       k = new Klijent();
       emf = Persistence.createEntityManagerFactory("PU");
       em = emf.createEntityManager();
       
    }
    
}
