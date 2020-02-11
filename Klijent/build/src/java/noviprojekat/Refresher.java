/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package noviprojekat;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.*;
import javax.jms.Queue;

/**
 *
 * @author makib
 */
public class Refresher extends Thread{

    @Resource(lookup = "jms/__defaultConnectionFactory")
    public ConnectionFactory conn;
        
    @Resource(lookup = "timerQueue")
    public Queue queue;
    
    public JMSContext context;
    public JMSConsumer consumer;
    
    
    @Override
    public void run() {
        if(context==null){
            context=conn.createContext();
            consumer=context.createConsumer(queue);
        }
        
        while(true){
            Message msg = consumer.receive();
            if (msg instanceof TextMessage){
                try {
                    TextMessage tmsg = (TextMessage) msg;
                    if (!tmsg.getText().equals("")){
                        Main.k.gui.doClickStatus();
                    }
                } catch (JMSException ex) {
                    Logger.getLogger(Refresher.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
    
    
    
}

/*
CREATE TABLE `documentrequest` (
  `JMBG` varchar(13) DEFAULT NULL,
  `id` varchar(12) NOT NULL,
  `bracnoStanje` varchar(45) DEFAULT NULL,
  `brojPrebivalista` varchar(45) DEFAULT NULL,
  `datumRodjenja` varchar(45) DEFAULT NULL,
  `ime` varchar(45) DEFAULT NULL,
  `imeMajke` varchar(45) DEFAULT NULL,
  `imeOca` varchar(45) DEFAULT NULL,
  `nacionalnost` varchar(45) DEFAULT NULL,
  `opstinaPrebivalista` varchar(45) DEFAULT NULL,
  `pol` varchar(45) DEFAULT NULL,
  `prezime` varchar(45) DEFAULT NULL,
  `prezimeMajke` varchar(45) DEFAULT NULL,
  `prezimeOca` varchar(45) DEFAULT NULL,
  `profesija` varchar(45) DEFAULT NULL,
  `ulicaPrebivalista` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
*/