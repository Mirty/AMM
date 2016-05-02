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
public class Conto {
    private float saldo;
        
    /**
     Costruttore
     */
    Conto (float saldo) {
        this.saldo = saldo;
    }
    
    /**
     * @return the saldo
     */
    public float getSaldo() {
        return saldo;
    }

    /**
     * @param saldo the saldo to set
     */
    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}
