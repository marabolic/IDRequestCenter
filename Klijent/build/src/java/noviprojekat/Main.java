/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package noviprojekat;

import entiteti.Documentrequest;
import gui.Gui;
import javax.persistence.*; 

/**
 *
 * @author makib
 */
public class Main {

    static Klijent k;
    static EntityManagerFactory emf;
    public static EntityManager em;
    
    
    
    
    public static void main(String[] args) {
       k = new Klijent();
       emf = Persistence.createEntityManagerFactory("PU");
       em = emf.createEntityManager();
       
    }
    
}
