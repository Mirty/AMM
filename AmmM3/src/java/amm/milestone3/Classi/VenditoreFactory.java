/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.milestone3.Classi;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author mirty
 */
public class VenditoreFactory {
    // lo uso per vedere se ci sono istante
    // della classe o se non ne esistono
    private static VenditoreFactory singleton;
    ArrayList <Venditore> listaVenditori;

    public static VenditoreFactory getInstance() {
        if (singleton == null) singleton = new VenditoreFactory();
        return singleton;
    }
    
    // con il costruttore vuoto creo dei venditori (dati fittizi)
    VenditoreFactory () {
        /*
         * inizializzo l'array list che contiene la lista dei venditori iscritti al sito.
         */
        listaVenditori = new ArrayList <> ();
        Random random = new Random();
        /*
         * genero casualmente i 4 venditori, e per fare questo creo un array contenente nomi
         * e un array contenente i cognomi che verranno estratti casualmente per la generazione.
         */ 
        String [] nomi  = {"Alessandro", "Carmelo", "Albina", "Giovanna", "Giovanni"};
        String [] cognomi = {"Murtas", "Locci", "Verdi", "Sollai", "Mascia"};
         
        for (int i=0; i<3; i++) {
            //Genero le email tramite concatenazione di nome e cognome estratti casualmente.
             
            String tempNome = nomi[random.nextInt(5)];
            String tempCognome = cognomi[random.nextInt(5)];
            // concateno nome e cognome minuscoli a @gmail.com
            String tempEmail = tempNome.toLowerCase() + tempCognome.toLowerCase() + "@gmail.com";
            // per inizializzare l'utente uso per comodità sempre la stessa password e come conto ne inserisco uno randomicamente
            Venditore temp = new Venditore (tempNome, tempCognome, tempEmail, "pass",  ContoFactory.getInstance().getListaConti().get(random.nextInt(6)), OggettoFactory.getInstance().getOggettiRandom());
            listaVenditori.add(temp);
        }  
        // creo un venditore di cui conosco i dati in modo da fare le prove sfruttando questo oggetto
        Venditore temp = new Venditore ("Vendi", "Tore", "venditore@gmail.com", "pass",  ContoFactory.getInstance().getListaConti().get(random.nextInt(6)), OggettoFactory.getInstance().getOggettiRandom());
        listaVenditori.add(temp);
    }
    
    Venditore getVenditoreByEmail (String email) {
        /**
         * Scorro la lista dei venditori che va avanti o fino a quando non trova
         * un utente con la stessa email (che rappresenta l'id o primary key)
         * o fino a quando finisce la lista. In questo caso significa che non esiste
         * nessun utente con la mail passata come paramentro e quindi restituisco null
         */
        int i=0;
        while (listaVenditori.iterator().hasNext()) {
            if (listaVenditori.get(i).getEmail().equals(email)) 
                return listaVenditori.get(i);
            i++;
        }
        return null;
    }
    
    Venditore getVenditoreByOggetto (Oggetto obj) {
        /**
         * Scorro la lista dei venditori che va avanti o fino a quando non trova
         * un venditore che vende lo stesso oggetto 
         * o fino a quando finisce la lista. In questo caso significa che non esiste
         * nessun utente con la mail passata come paramentro e quindi restituisco null
         */
        int i=0, j;
        while (listaVenditori.iterator().hasNext()) {
            /**
             * Per ogni venditore scorro la lista dei prodotti da lui venduti in modo
             * da ricercare l'oggetto passato come parametro al suo interno
             * Con i scorro i venditori, con j scorro gli oggetti
             */
            j=0;
            while (listaVenditori.get(i).getBeneInVendita().iterator().hasNext()) {
                if (listaVenditori.get(i).getBeneInVendita().get(j) == obj) 
                    return listaVenditori.get(i);
                j++;
            }
            i++;
        }
        return null;
    }
    
    public ArrayList<Venditore> getListaVenditori() {
        return listaVenditori;
    }
}
