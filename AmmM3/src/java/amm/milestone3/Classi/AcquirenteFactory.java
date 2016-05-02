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
public class AcquirenteFactory {
    
    // lo uso per vedere se ci sono istante
    // della classe o se non ne esistono
    private static AcquirenteFactory singleton;
    ArrayList <Acquirente> listaAcquirenti;

    public static AcquirenteFactory getInstance() {
        if (singleton == null) singleton = new AcquirenteFactory();
        return singleton;
    }
    
    // con il costruttore vuoto creo degli acquirenti (dati fittizi)
    AcquirenteFactory () {
        /**
         * inizializzo l'array list che contiene la lista degli acquirenti iscritti al sito.
         */
        listaAcquirenti = new ArrayList <Acquirente> ();
        /**
         * genero casualmente i 3 clienti, e per fare questo creo un array contenente nomi
         * e un array contenente i cognomi che verranno estratti casualmente per la generazione.
         */
        String [] nomi  = {"Marta", "Francesco", "Alberto", "Beatrice", "Ambrogio"};
        String [] cognomi = {"Murtas", "Angela", "Rossi", "Mascia", "Puddu"};
        Random random = new Random();
        for (int i=0; i<3; i++) {
            /** 
             * Genero le email tramite concatenazione di nome e cognome estratti casualmente.
             */
            String tempNome = nomi[random.nextInt(5)];
            String tempCognome = cognomi[random.nextInt(5)];
            // concateno nome e cognome minuscoli a @gmail.com
            String tempEmail = tempNome.toLowerCase() + tempCognome.toLowerCase() + "@gmail.com";
            // per inizializzare l'utente uso per comodità sempre la stessa password e come conto ne inserisco uno randomicamente
            Acquirente temp = new Acquirente (tempNome, tempCognome, tempEmail, "pass",  ContoFactory.getInstance().getListaConti().get(random.nextInt(6)), OggettoFactory.getInstance().getOggettiRandom());
            listaAcquirenti.add(temp);
        }
        // creo un acquirente di cui conosco i dati in modo da fare le prove sfruttando questo oggetto
        Acquirente temp = new Acquirente ("Acqui", "Rente", "acquirente@gmail.com", "pass",  ContoFactory.getInstance().getListaConti().get(random.nextInt(6)), OggettoFactory.getInstance().getOggettiRandom());
        listaAcquirenti.add(temp);
    }
    
    Acquirente getAcquirenteByEmail (String email) {
        /**
         * Scorro la lista dei clienti che va avanti o fino a quando non trova
         * un utente con la stessa email (che rappresenta l'id o primary key)
         * o fino a quando finisce la lista. In questo caso significa che non esiste
         * nessun utente con la mail passata come paramentro
         */
        int i=0;
        while (listaAcquirenti.iterator().hasNext()) {
            if (listaAcquirenti.get(i).getEmail().equals(email)) 
                return listaAcquirenti.get(i);
            i++;
        }
        return null;
    }
    
    public ArrayList<Acquirente> getListaAcquirenti() {
        return listaAcquirenti;
    }
    
    /**
     * getOggettiRandom -> Restituisce una lista randomica di acquirenti
     */
    ArrayList <Acquirente> getAcquirentiRandom () {
        /**
         * Creo una lista grande max (un numero random)
         * e ci inserisco gli acquirenti randomicamente
         */
        Random random = new Random();
        // definisco randomicamente la grandezza della lista generata
        // che può andara da 0 a listaAcquirenti.size()
        int max=random.nextInt(listaAcquirenti.size()); 
        // definisco l'arraylist da restituire
        ArrayList <Acquirente> res = new ArrayList <> ();
        for (int i=0; i<max; i++)  {
            // aggiunge alla lista da restituire un acquirente preso in modo lineare dalla lista
            // non sono possibili dei doppioni ovviamente
            res.add(listaAcquirenti.get(i));
        }
        return res;
    }
}
