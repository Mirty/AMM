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
public class OggettoFactory {
    // lo uso per vedere se ci sono istante
    // della classe o se non ne esistono
    private static OggettoFactory singleton;
    private ArrayList <Oggetto> listaOggetti;

    public static OggettoFactory getInstance() {
        if (singleton == null) singleton = new OggettoFactory();
        return singleton;
    }
    
    
    // con il costruttore vuoto creo degli oggetti (dati fittizi)
    OggettoFactory () {
        /**
         * inizializzo l'array list che contiene la lista degli oggetti venduti sul sito.
         */
        listaOggetti = new ArrayList <> ();
        String baseUrl = "imgs/obj/";
        Random random = new Random ();
        /**
         * genero gli oggetti da mettere in vendita
         */
        // int id, int venduto, int inStock, float prezzo, String nome, String marca, String categoria, String descrizione
        listaOggetti.add(new Oggetto(0, 0, random.nextInt(2000), (float)2.5, "Spaghetti integrali", "Barilla", "Pasta integrale", "Con la pasta Barilla Integrale porti in tavola tutto il gusto del benessere.Una porzione di pasta Barilla Integrale (85g) fornisce più di 20% del tuo fabbisogno giornaliero di fibre.", baseUrl+"barillaInSpaghetti.jpg",OpinioneFactory.getInstance().getOpinioniRandom()));
        listaOggetti.add(new Oggetto(1, 0, random.nextInt(2000), (float)2.7, "Pennette rigate integrali", "Barilla", "Pasta integrale", "Con la pasta Barilla Integrale porti in tavola tutto il gusto del benessere.Una porzione di pasta Barilla Integrale (85g) fornisce più di 20% del tuo fabbisogno giornaliero di fibre.", baseUrl+"barillaInPennetteRigate.jpg",OpinioneFactory.getInstance().getOpinioniRandom()));
        listaOggetti.add(new Oggetto(2, 0, random.nextInt(2000), (float)2.3, "Mezze Penne rigate integrali", "Barilla", "Pasta integrale", "Con la pasta Barilla Integrale porti in tavola tutto il gusto del benessere.Una porzione di pasta Barilla Integrale (85g) fornisce più di 20% del tuo fabbisogno giornaliero di fibre.", baseUrl+"barillaInMezzePenneRigate.jpg",OpinioneFactory.getInstance().getOpinioniRandom()));
        listaOggetti.add(new Oggetto(3, 0, random.nextInt(2000), (float)2.2, "Bavette integrali", "Barilla", "Pasta integrale", "Con la pasta Barilla Integrale porti in tavola tutto il gusto del benessere.Una porzione di pasta Barilla Integrale (85g) fornisce più di 20% del tuo fabbisogno giornaliero di fibre.", baseUrl+"barillaInBavette.jpg",OpinioneFactory.getInstance().getOpinioniRandom()));
        listaOggetti.add(new Oggetto(4, 0, random.nextInt(2000), (float)2.5, "Tortiglioni", "Barilla", "Pasta senza glutine", "Una pasta senza glutine davvero buona come la pasta Barilla di sempre. Barilla Senza Glutine è preparata con mais bianco, mais giallo e riso, una ricetta esclusiva dal sapore equilibrato e dalla consistenza sempre al dente.", baseUrl+"barillaGFTortiglioni.png",OpinioneFactory.getInstance().getOpinioniRandom()));
        listaOggetti.add(new Oggetto(5, 0, random.nextInt(2000), (float)2.5, "Fusilli", "Barilla", "Pasta senza glutine", "Una pasta senza glutine davvero buona come la pasta Barilla di sempre. Barilla Senza Glutine è preparata con mais bianco, mais giallo e riso, una ricetta esclusiva dal sapore equilibrato e dalla consistenza sempre al dente.", baseUrl+"barillaGFFusilli.png",OpinioneFactory.getInstance().getOpinioniRandom()));
        listaOggetti.add(new Oggetto(6, 0, random.nextInt(2000), (float)2.5, "Spaghetti", "Barilla", "Pasta senza glutine", "Una pasta senza glutine davvero buona come la pasta Barilla di sempre. Barilla Senza Glutine è preparata con mais bianco, mais giallo e riso, una ricetta esclusiva dal sapore equilibrato e dalla consistenza sempre al dente.", baseUrl+"barillaGFSpaghetti.png",OpinioneFactory.getInstance().getOpinioniRandom()));
        listaOggetti.add(new Oggetto(7, 0, random.nextInt(2000), (float)2.5, "Penne rigate", "Barilla", "Pasta senza glutine", "Una pasta senza glutine davvero buona come la pasta Barilla di sempre. Barilla Senza Glutine è preparata con mais bianco, mais giallo e riso, una ricetta esclusiva dal sapore equilibrato e dalla consistenza sempre al dente.", baseUrl+"barillaGFPenneRigate.png",OpinioneFactory.getInstance().getOpinioniRandom()));
        
    }
    
    /**
     * @return the listaOggetti
     */
    public ArrayList <Oggetto> getListaOggetti() {
        return listaOggetti;
    }

    /**
     * @param listaOggetti the listaOggetti to set
     */
    public void setListaOggetti(ArrayList <Oggetto> listaOggetti) {
        this.listaOggetti = listaOggetti;
    }
    
    /**
     * getOggettoById -> Restituisce l'oggetto in base all'id
     */
    public Oggetto getOggettoById (int id) {
        /**
         * Scorro la lista degli oggetti che va avanti o fino a quando non trova l'oggetto
         * o fino a quando finisce la lista. In questo caso significa che non esiste
         * nessun oggetto con l'id passato come paramentro
         */
        int i=0;
        while (getListaOggetti().iterator().hasNext()) {
            if (getListaOggetti().get(i).getId() == id) 
                return getListaOggetti().get(i);
            i++;
        }
        return null;
    }
    
    /**
     * getOggettiByMarca -> Restituisce la lista di oggetti della marca passata come parametro
     */
    public ArrayList <Oggetto> getOggettiByMarca (String marca) {
        /**
         * Scorro la lista degli oggetti che va avanti o fino a quando non trova la marca
         * o fino a quando finisce la lista. In questo caso significa che non esiste
         * nessun oggetto con la marca passata come parametro
         */
        int i=0;
        // definisco l'arraylist da restituire
        ArrayList <Oggetto> res = new ArrayList <Oggetto> ();
        while (getListaOggetti().iterator().hasNext()) {
            if (getListaOggetti().get(i).getMarca().equals(marca)) 
                // se trovo l'oggetto della marca passata come parametro
                // lo aggiungo alla lista che restituirò al chiamante
                res.add(getListaOggetti().get(i));
            i++;
        }
        return res;
    }
    
    /**
     * getOggettiInStock -> Restituisce la lista di oggetti disponibili in magazzino
     */
    public ArrayList <Oggetto> getOggettiInStock () {
        /**
         * Scorro la lista degli oggetti che va avanti o fino a quando non trova oggetti in stock
         * o fino a quando finisce la lista. In questo caso significa che non esiste
         * nessun oggetto con la marca passata come parametro
         */
        int i=0;
        // definisco l'arraylist da restituire
        ArrayList <Oggetto> res = new ArrayList <Oggetto> ();
        while (getListaOggetti().iterator().hasNext()) {
            if (getListaOggetti().get(i).getInStock()>0) 
                // se l'oggetto è in stock lo aggiungo alla lista che restituirò al chiamante
                res.add(getListaOggetti().get(i));
            i++;
        }
        return res;
    }
    
    /**
     * getOggettoByNome -> Restituisce l'oggetto che ha come nome la stringa passata come parametro
     */
    public Oggetto getOggettoByNome (String nome) {
        /**
         * Scorro la lista degli oggetti che va avanti o fino a quando non trova l'oggetto
         * o fino a quando finisce la lista. In questo caso significa che non esiste
         * nessun oggetto con l'id passato come paramentro
         */
        int i=0;
        while (getListaOggetti().iterator().hasNext()) {
            if (getListaOggetti().get(i).getNome().equals(nome))
                return getListaOggetti().get(i);
            i++;
        }
        return null;
    }
    
    /**
     * getOggettiByCategoria -> Restituisce la lista di oggetti che hanno come categoria
     * quella specificata come parametro
     */
    public ArrayList <Oggetto> getOggettiByCategoria (String categoria) {
        /**
         * Scorro la lista degli oggetti che va avanti o fino a quando non trova oggetti della categoria
         * o fino a quando finisce la lista. In questo caso significa che non esiste
         * nessun oggetto della categoria passata come parametro
         */
        int i=0;
        // definisco l'arraylist da restituire
        ArrayList <Oggetto> res = new ArrayList <Oggetto> ();
        while (getListaOggetti().iterator().hasNext()) {
            if (getListaOggetti().get(i).getCategoria().equals(categoria)) 
                // se l'oggetto è della categoria specificata, lo aggiungo alla lista che restituirò al chiamante
                res.add(getListaOggetti().get(i));
            i++;
        }
        return res;
    }
    
    /**
     * getOggettiByMaxPrice -> Restituisce la lista di oggetti che hanno come prezzo massimo
     * quello specificato come parametro
     */
    public ArrayList <Oggetto> getOggettiByMaxPrice (float maxPrezzo) {
        /**
         * Scorro la lista degli oggetti che va avanti o fino a quando non trova oggetti 
         * o fino a quando finisce la lista. In questo caso significa che non esiste
         * nessun oggetto della categoria passata come parametro
         */
        int i=0;
        // definisco l'arraylist da restituire
        ArrayList <Oggetto> res = new ArrayList <Oggetto> ();
        while (getListaOggetti().iterator().hasNext()) {
            if (getListaOggetti().get(i).getPrezzo()<=maxPrezzo) 
                // se l'oggetto è ha un costo <= rispetto a quello passato come parametro
                // lo aggiungo alla lista degli oggetti da restituire al chiamante
                res.add(getListaOggetti().get(i));
            i++;
        }
        return res;
    }
    
    /**
     * getOggettiByMinPrice -> Restituisce la lista di oggetti che hanno come prezzo minimo
     * quello specificato come parametro
     */
    public ArrayList <Oggetto> getOggettiByMinPrice (float minPrezzo) {
        /**
         * Scorro la lista degli oggetti che va avanti o fino a quando non trova oggetti 
         * o fino a quando finisce la lista. In questo caso significa che non esiste
         * nessun oggetto della categoria passata come parametro
         */
        int i=0;
        // definisco l'arraylist da restituire
        ArrayList <Oggetto> res = new ArrayList <Oggetto> ();
        while (getListaOggetti().iterator().hasNext()) {
            if (getListaOggetti().get(i).getPrezzo()>=minPrezzo) 
                // se l'oggetto è ha un costo >= rispetto a quello passato come parametro
                // lo aggiungo alla lista degli oggetti da restituire al chiamante
                res.add(getListaOggetti().get(i));
            i++;
        }
        return res;
    }
    
    /**
     * getOggettiRandom -> Restituisce una lista randomica di oggetti 
     */
    public ArrayList <Oggetto> getOggettiRandom () {
        /**
         * Creo una lista grande max (un numero random)
         * e ci inserisco gli oggetti randomicamente
         */
        Random random = new Random();
        // definisco randomicamente la grandezza della lista generata
        // che può andara da 0 a listaOggetti.size()
        int max=random.nextInt(listaOggetti.size()); 
        // definisco l'arraylist da restituire
        ArrayList <Oggetto> res = new ArrayList <> ();
        for (int i=0; i<max; i++)  {
            // aggiunge alla lista da restituire un oggetto pescato a random dalla lista
            // (sono possibili dei doppioni)
            res.add(listaOggetti.get(random.nextInt(listaOggetti.size())));
        }
        return res;
    }

    
    
}
