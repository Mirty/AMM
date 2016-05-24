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
    private int idConto;
    
    /**
     * Costruttore
     */ 
    Utente (String nome, String cognome, String email, String password, int conto) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        idConto = conto;
    }
    
    Utente () {}
    
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
    public int getConto() {
        //return idConto;
        return 80;
    }

    /**
     * @param conto the conto to set
     */
    public void setConto(int conto) {
        idConto = conto;
    }
}
