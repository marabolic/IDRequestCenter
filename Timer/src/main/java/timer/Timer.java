/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timer;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.jms.*;

/**
 *
 * @author makib
 */
@Stateless
public class Timer {
    
    @Resource(lookup = "jms/__defaultConnectionFactory")
    public ConnectionFactory conn;
        
    @Resource(lookup = "timerQueue")
    public Queue queue;
    
    public JMSContext context;
    public JMSProducer producer;
    
    public int vr=0;
    
    @Schedule(second="*", minute = "*",  hour = "*")
    public void test(){
        if(conn!=null && queue!=null){
            if(context==null){
                context=conn.createContext();
                producer=context.createProducer();
                System.out.println("Kreiran producer");
            }
           
            TextMessage txtMsg=context.createTextMessage("Test "+vr);
            producer.send(queue, txtMsg);
            vr++;
            System.out.println("timer");
        }
    }
}

