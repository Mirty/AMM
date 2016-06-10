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
public class Venditore extends Utente {

    /**
     * Costruttore con anche il bene in vendita
     */ 
    private String marca;
    
    Venditore (String nome, String cognome, String email, String password, Conto conto, String marca) {
        super(nome, cognome, email, password, conto);
        this.marca = marca;
    }
    
    Venditore () {
        super();
    }

    /**
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }
    
}
