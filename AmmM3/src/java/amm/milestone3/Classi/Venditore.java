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
    Venditore (String nome, String cognome, String email, String password, Conto conto, ArrayList <Oggetto> beni) {
        super(nome, cognome, email, password, conto, beni);
    }

    /**
     * per rendere più chiara la distinzione tra Venditore e Utente rispecifico
     * dei metodi che sfruttano i metodi della superclasse ma che hanno nomi
     * più espliciti al fine di un migliore utilizzo degli stessi -> getBeneInVendita
     * @return the beneInVendita
     */
    public ArrayList <Oggetto> getBeneInVendita() {
        return super.getBeni();
    }

    /**
     * per rendere più chiara la distinzione tra Venditore e Utente rispecifico
     * dei metodi che sfruttano i metodi della superclasse ma che hanno nomi
     * più espliciti al fine di un migliore utilizzo degli stessi -> setBeneInVendita
     * @param beneInVendita
     */
    public void setBeneInVendita(ArrayList <Oggetto> beneInVendita) {
        super.setBeni(beneInVendita);
    }
    
}
