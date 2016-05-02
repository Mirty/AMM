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
public class OpinioneFactory {
    // lo uso per vedere se ci sono istante
    // della classe o se non ne esistono
    private static OpinioneFactory singleton;
    private ArrayList <Opinione> listaOpinioni;

    public static OpinioneFactory getInstance() {
        if (singleton == null) singleton = new OpinioneFactory();
        return singleton;
    }

    /**
     * Costruttore
     */
    OpinioneFactory() {
        listaOpinioni = new ArrayList <> ();
        Random random = new Random ();
        
        /*
                AcquirenteFactory.getInstance().getListaAcquirenti().get(random.nextInt(AcquirenteFactory.getInstance().getListaAcquirenti().size())),  // estraggo randomicamente l'acquirente che ha scritto la recensione
                random.nextInt(AcquirenteFactory.getInstance().getListaAcquirenti().size()) // estraggo randomicamente a quanti acquirenti è piaciuta la recensione
        */
        
        // 1 opinione -> ti piace vincere facile
        // String titolo, String descrizione, float valutazione, Acquirente scrittaDa, float piaciutaA
        Opinione temp = new Opinione ("Super consigliato!", 
                "Ne ho comprato una confezione giusto per provare, ma devo dire di essere rimasto sorpreso! Consiglio l'acquisto.", 
                random.nextInt(3)+7,  // valutazione -> deve essere molto positiva visto il titolo
                "anonimo",  // estraggo randomicamente l'acquirente che ha scritto la recensione
                random.nextInt(20) // estraggo randomicamente a quanti acquirenti è piaciuta la recensione
        );
        listaOpinioni.add(temp);
        
        // faccio lo stesso per varie volte
        
        // 2 opinione -> il critico
        // String titolo, String descrizione, float valutazione, Acquirente scrittaDa, float piaciutaA
        temp = new Opinione ("Pensavo meglio", 
                "Ne ho comprato una confezione giusto per provare, ma devo dire di esserne rimasta deluso!", 
                random.nextInt(5)+2,  // valutazione -> deve essere negativa e critica visto il titolo
                "anonimo",  // estraggo randomicamente l'acquirente che ha scritto la recensione
                random.nextInt(20) // estraggo randomicamente a quanti acquirenti è piaciuta la recensione
        );
        listaOpinioni.add(temp);
        
        // 3 opinione -> la mamma
        // String titolo, String descrizione, float valutazione, Acquirente scrittaDa, float piaciutaA
        temp = new Opinione ("È già la terza confezione che compro!", 
                "Non riesco più a fare a meno di questo prodotto. I miei bambini ne vanno matti.", 
                random.nextInt(2)+7,  // valutazione -> deve essere positiva visto il titolo
                "anonimo",  // estraggo randomicamente l'acquirente che ha scritto la recensione
                random.nextInt(20) // estraggo randomicamente a quanti acquirenti è piaciuta la recensione
        );
        listaOpinioni.add(temp);
        
        // 4 opinione -> la cena romantica 
        // String titolo, String descrizione, float valutazione, Acquirente scrittaDa, float piaciutaA
        temp = new Opinione ("È stato un affarone!!", 
                "Ho acquistato questo prodotto per una cena a lume di candela. Il risultato è stato impeccabile! Assolutamente consigliato!!!1", 
                random.nextInt(2)+7,  // valutazione -> deve essere positiva visto il titolo
                "anonimo",  // estraggo randomicamente l'acquirente che ha scritto la recensione
                random.nextInt(20) // estraggo randomicamente a quanti acquirenti è piaciuta la recensione
        );
        listaOpinioni.add(temp);
        
        // 5 opinione -> il turista affezionato
        // String titolo, String descrizione, float valutazione, Acquirente scrittaDa, float piaciutaA
        temp = new Opinione ("BEST PRODUCT EVER!", 
                "I eat this for the first time when I was in Italy, it absolutely blowed my mind, it was so tasty that I could't stop thinking of it. Thanks to Eataly I am now able to buy this amazing product! I'm feeling like Marcel Proust right now! Thank you Eataly!", 
                random.nextInt(2)+7,  // valutazione -> deve essere positiva visto il titolo
                "anonimo",  // estraggo randomicamente l'acquirente che ha scritto la recensione
                random.nextInt(20) // estraggo randomicamente a quanti acquirenti è piaciuta la recensione
        );
        listaOpinioni.add(temp);
    }

    /**
     * @return the listaOpinioni
     */
    public ArrayList <Opinione> getListaOpinioni() {
        return listaOpinioni;
    }

    /**
     * @param listaOpinioni the listaOpinioni to set
     */
    public void setListaOpinioni(ArrayList <Opinione> listaOpinioni) {
        this.listaOpinioni = listaOpinioni;
    }
    
    /**
     * getOpinioniRandom -> Restituisce una lista randomica di opinioni
     */
    ArrayList <Opinione> getOpinioniRandom () {
        /**
         * Creo una lista grande max (un numero random)
         * e ci inserisco le opinioni randomicamente
         */
        Random random = new Random();
        // definisco randomicamente la grandezza della lista generata
        // che può andara da 0 a listaOpinioni.size()
        int max=random.nextInt(listaOpinioni.size()+1); 
        // definisco l'arraylist da restituire
        ArrayList <Opinione> res = new ArrayList <> ();
        for (int i=0; i<max; i++)  {
            // aggiunge alla lista da restituire un'opinione preso in modo lineare dalla lista
            // non sono possibili dei doppioni ovviamente
            res.add(listaOpinioni.get(i));
        }
        return res;
    }
    
    
}
