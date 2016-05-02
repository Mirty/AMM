/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.milestone3.Classi;
import java.util.Random;

import java.util.ArrayList;

/**
 *
 * @author mirty
 */
public class ContoFactory {
    // lo uso per vedere se ci sono istante
    // della classe o se non ne esistono
    private static ContoFactory singleton;
    private ArrayList <Conto> listaConti;

    public static ContoFactory getInstance() {
        if (singleton == null) singleton = new ContoFactory();
        return singleton;
    }
    
    /**
     * Costruttore
     */
    ContoFactory () {
        listaConti = new ArrayList <Conto> ();
        // genero 6 conti
        listaConti.add(new Conto((float) 57.4));
        listaConti.add(new Conto((float) 58.3));
        listaConti.add(new Conto((float) 34.01));
        listaConti.add(new Conto((float) 124));
        listaConti.add(new Conto((float) 89.99));
        listaConti.add(new Conto((float) 38.5));
    }

    /**
     * @return the listaConti
     */
    public ArrayList <Conto> getListaConti() {
        return listaConti;
    }

    /**
     * @param listaConti the listaConti to set
     */
    public void setListaConti(ArrayList <Conto> listaConti) {
        this.listaConti = listaConti;
    }
}
