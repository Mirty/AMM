package amm.milestone3.Classi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author mirty
 */
public class UtenteFactory {
    // lo uso per vedere se ci sono istante
    // della classe o se non ne esistono
    private static UtenteFactory singleton;
    ArrayList <Utente> listaUtenti;
    private String connectionString;

    public void setConnectionString(String s){ 
        this.connectionString = s;
    }
    
    public String getConnectionString(){ 
        return this.connectionString;
    }

    public static UtenteFactory getInstance() {
        if (singleton == null) singleton = new UtenteFactory();
        return singleton;
    }
    
    UtenteFactory () {}
    
    public ArrayList <Utente> getListaUtenti () {
        return listaUtenti;
    }
    
    public Utente getUtente (String email, String password) {
    try
        {
            Connection conn = DriverManager.getConnection(connectionString,  "APP", "");

            // controllo che sia un acquirente
            String query = "select * from acquirente join utente on utente.email = acquirente.email where password = ? and acquirente.email = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, password);
            stmt.setString(2, email);
            ResultSet set = stmt.executeQuery();
            // se restituisce 1 riga => è un acquirente
            if(set.next())
            {
                Acquirente acquirente = new Acquirente();
                acquirente.setEmail(set.getString("email"));
                acquirente.setNome(set.getString("nome"));
                acquirente.setCognome(set.getString("cognome"));
                acquirente.setPassword(set.getString("password"));
                acquirente.setConto(set.getInt("conto"));
                
                stmt.close();
                conn.close();
                
                return acquirente;
            }
            
            
            // se arrivo quì significa che non è un acquirente
            // controllo che sia un venditore
            query = "select * from venditore join utente on utente.email = venditore.email  where password = ? and venditore.email = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, password);
            stmt.setString(2, email);
            set = stmt.executeQuery();
            // se restituisce 1 riga => è un venditore
            if(set.next())
            {
                Venditore venditore = new Venditore();
                venditore.setEmail(set.getString("email"));
                venditore.setNome(set.getString("nome"));
                venditore.setCognome(set.getString("cognome"));
                venditore.setPassword(set.getString("password"));
                venditore.setMarca(set.getString("marchio"));
                venditore.setConto(set.getInt("conto"));
                
                stmt.close();
                conn.close();
                
                return venditore;
            }
        }
        catch(SQLException e){
            System.out.println("Errore: "+e.getMessage());
        }
    
        return null;
    }
    
}
