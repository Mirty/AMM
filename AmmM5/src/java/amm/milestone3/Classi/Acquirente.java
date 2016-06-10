package amm.milestone3.Classi;

/**
 *
 * @author mirty
 */
public class Acquirente extends Utente {
    
    /**
     * Costruttore con anche il carrello
     */ 
    public Acquirente (String nome, String cognome, String email, String password, Conto conto) {
        super(nome, cognome, email, password, conto);
    }
    
    public Acquirente () {
        super();
    }
    
    
}
