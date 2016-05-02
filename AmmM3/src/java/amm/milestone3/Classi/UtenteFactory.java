/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.milestone3.Classi;

import java.util.ArrayList;

/**
 *
 * @author mirty
 */
public class UtenteFactory {
    // lo uso per vedere se ci sono istante
    // della classe o se non ne esistono
    private static UtenteFactory singleton;
    ArrayList <Utente> listaUtenti;

    public static UtenteFactory getInstance() {
        if (singleton == null) singleton = new UtenteFactory();
        return singleton;
    }
    
    // con il costruttore vuoto creo degli acquirenti (dati fittizi)
    UtenteFactory () {
        /**
         * inizializzo l'array list che contiene la lista degli utenti iscritti al sito.
         */
        listaUtenti = new ArrayList <Utente> ();
        
        // creo una lista contenente i venditori e una contenente gli acquirenti
        VenditoreFactory listaVenditori = new VenditoreFactory ();
        AcquirenteFactory listaAcquirenti = new AcquirenteFactory();
        
        
        // inserisco nella lista degli utenti, gli acquirenti e i venditori
        // contenuti nelle rispettive liste
        for (int i=0; i<4; i++) {
            listaUtenti.add(listaVenditori.getListaVenditori().get(i));
            listaUtenti.add(listaAcquirenti.getListaAcquirenti().get(i));
        }
    }
    
    public ArrayList <Utente> getListaUtenti () {
        return listaUtenti;
    }
    
}
