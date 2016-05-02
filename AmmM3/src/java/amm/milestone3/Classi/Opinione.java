/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.milestone3.Classi;

/**
 *
 * @author mirty
 */
public class Opinione {
    private String titolo;
    private String descrizione;
    private float valutazione;
    private String scrittaDa;
    private float piaciutaA;

    public Opinione(String titolo, String descrizione, float valutazione, String scrittaDa, float piaciutaA) {
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.valutazione = valutazione;
        this.scrittaDa = scrittaDa;
        this.piaciutaA = piaciutaA;
    }

    /**
     * @return the titolo
     */
    public String getTitolo() {
        return titolo;
    }

    /**
     * @param titolo the titolo to set
     */
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    /**
     * @return the descrizione
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * @param descrizione the descrizione to set
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * @return the valutazione
     */
    public float getValutazione() {
        return valutazione;
    }

    /**
     * @param valutazione the valutazione to set
     */
    public void setValutazione(float valutazione) {
        this.valutazione = valutazione;
    }

    /**
     * @return the scrittaDa
     */
    public String getScrittaDa() {
        return scrittaDa;
    }

    /**
     * @param scrittaDa the scrittaDa to set
     */
    public void setScrittaDa(String scrittaDa) {
        this.scrittaDa = scrittaDa;
    }

    /**
     * @return the piaciutaA
     */
    public float getPiaciutaA() {
        return piaciutaA;
    }

    /**
     * @param piaciutaA the piaciutaA to set
     */
    public void setPiaciutaA(float piaciutaA) {
        this.piaciutaA = piaciutaA;
    }
}
