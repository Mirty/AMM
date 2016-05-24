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
public class Oggetto{
    private int id;
    private float prezzo;
    private String nome;
    private String marca;
    private int categoria;
    private String descrizione;
    private String urlImg;
    private float peso;
    private int inStock;

    /**
     * Costruttore 
     */
    public Oggetto () {}
    
    /**
     * Costruttore completo
     */
     public Oggetto (int id, String nome, float prezzo,  String descrizione, float peso, String urlImg, int categoria, String marchio, int inStock) {
        this.id = id;
        this.prezzo = prezzo;
        this.nome = nome;
        this.marca = marchio;
        this.categoria = categoria;
        this.descrizione = descrizione;
        this.urlImg = urlImg;
        this.peso = peso;
        this.inStock = inStock;
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
    public int getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(int categoria) {
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
     * @return the peso
     */
    public float getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(float peso) {
        this.peso = peso;
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
}
