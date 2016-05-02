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
public class Acquirente extends Utente {
    
    /**
     * Costruttore con anche il carrello
     */ 
    Acquirente (String nome, String cognome, String email, String password, Conto conto, ArrayList <Oggetto> carrello) {
        super(nome, cognome, email, password, conto, carrello);
    }

    /**
     * per rendere più chiara la distinzione tra Venditore e Utente rispecifico
     * dei metodi che sfruttano i metodi della superclasse ma che hanno nomi
     * più espliciti al fine di un migliore utilizzo degli stessi -> getCarrello
     * @return the carrello
     */
    public ArrayList <Oggetto> getCarrello() {
        return super.getBeni();
    }

    /**
     * per rendere più chiara la distinzione tra Venditore e Utente rispecifico
     * dei metodi che sfruttano i metodi della superclasse ma che hanno nomi
     * più espliciti al fine di un migliore utilizzo degli stessi -> setCarrello
     * @param carrello the carrello to set
     */
    public void setCarrello(ArrayList <Oggetto> carrello) {
        super.setBeni(carrello);
    }
    
    
}
