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
public class Oggetto {
    private int id;
    private int venduto;
    private int inStock;
    private float prezzo;
    private String nome;
    private String marca;
    private String categoria;
    private String descrizione;
    private String urlImg;
    private ArrayList <Opinione> opinione;

    /**
     * Costruttore 
     */
    public Oggetto () {}
    
    /**
     * Costruttore completo
     */
     Oggetto (int id, int venduto, int inStock, float prezzo, String nome, String marca, String categoria, String descrizione, String urlImg, ArrayList <Opinione> opinione) {
        this.id = id;
        this.venduto = venduto;
        this.inStock = inStock;
        this.prezzo = prezzo;
        this.nome = nome;
        this.marca = marca;
        this.categoria = categoria;
        this.descrizione = descrizione;
        this.urlImg = urlImg;
        this.opinione = opinione;
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the venduto
     */
    public int getVenduto() {
        return venduto;
    }

    /**
     * @param venduto the venduto to set
     */
    public void setVenduto(int venduto) {
        this.venduto = venduto;
    }

    /**
     * @return the inStock
     */
    public int getInStock() {
        return inStock;
    }

    /**
     * @param inStock the inStock to set
     */
    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    /**
     * @return the prezzo
     */
    public float getPrezzo() {
        return prezzo;
    }

    /**
     * @param prezzo the prezzo to set
     */
    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
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

    /**
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
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
     * @return the urlImg
     */
    public String getUrlImg() {
        return urlImg;
    }

    /**
     * @param urlImg the urlImg to set
     */
    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    /**
     * @return the opinione
     */
    public ArrayList <Opinione> getOpinione() {
        return opinione;
    }

    /**
     * @param opinione the opinione to set
     */
    public void setOpinione(ArrayList <Opinione> opinione) {
        this.opinione = opinione;
    }
}
