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
public class Utente {
    private String nome;
    private String cognome; 
    private String email; 
    private String password;
    private Conto conto;
    /*
        per il venditore conterrà i beni messi in vendita
        per l'acquirente sarà il carrello
    */
    private ArrayList <Oggetto> beni; 
    
    /**
     * Costruttore
     */ 
    Utente (String nome, String cognome, String email, String password, Conto conto, ArrayList <Oggetto> beni) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.conto = conto;
        this.beni = beni;
    }
    
    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the cognome
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * @param cognome the cognome to set
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the conto
     */
    public Conto getConto() {
        return conto;
    }

    /**
     * @param conto the conto to set
     */
    public void setConto(Conto conto) {
        this.conto = conto;
    }
    
    
    /**
     * @return the beneInVendita
     * rendo il metodo protected in modo che possa essere richiamato
     * solo dalle sue sottoclassi (Venditore e Acquirente)
     * In questo modo sia in Venditore che in Acquirente
     * specifico dei metodi più espliciti che sono rispettivamente
     * get e set BeneInVendita e get e set Carrello
     */
    protected ArrayList <Oggetto> getBeni() {
        return beni;
    }

    /**
     * @param beni
     */
    protected void setBeni(ArrayList <Oggetto> beni) {
        this.beni = beni;
    }
}
