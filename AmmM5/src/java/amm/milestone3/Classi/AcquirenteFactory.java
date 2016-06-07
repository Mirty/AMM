package amm.milestone3.Classi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author mirty
 */
public class AcquirenteFactory {

    private static AcquirenteFactory singleton;
    ArrayList <Acquirente> listaAcquirenti;
    private String connectionString;

    public void setConnectionString(String s){ 
        this.connectionString = s;
    }
    
    public String getConnectionString(){ 
        return this.connectionString;
    }

    public static AcquirenteFactory getInstance() {
        if (singleton == null) singleton = new AcquirenteFactory();
        return singleton;
    }
    
    AcquirenteFactory () {
        /**
         * inizializzo l'array list che contiene la lista degli acquirenti iscritti al sito.
         *//*
        listaAcquirenti = new ArrayList <> ();
        try {
            // creazione e apertura della connessione
            // si specifica la url, lo username e la password per il db
            Connection conn = DriverManager.getConnection(connectionString, "admin_65005", "");
            // utilizzo della connessione per inviare una query (select)
            Statement stmt = conn.createStatement();
            String sql = "select * from utente join acquirente on acquirente.email = utente.email";
            ResultSet set = stmt.executeQuery(sql);
            while (set.next()) {
                // String nome, String cognome, String email, String password, Conto conto
                listaAcquirenti.add(new Acquirente(set.getString("nome"), set.getString("cognome"), set.getString("email"), set.getString("password"), set.getInt("conto")));
            }
            stmt.close();
            // chiusura della connessione
            conn.close();
        } 
        catch (SQLException ex) {
            // nel caso la query fallisca (p.e. errori di sintassi)
            // viene sollevata una SQLException
            //Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errore acquirente factory");
        } */
    }
    
    Acquirente getAcquirenteByEmail (String email) {
        Acquirente n = null;
        try {
            // creazione e apertura della connessione
            // si specifica la url, lo username e la password per il db
            Connection conn = DriverManager.getConnection(connectionString, "admin_65005", "");
            // utilizzo della connessione per inviare una query (select)
            // Si mettono dei punti di domanda al posto dei valori
            String sql = "select * from acquirente where email= ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
             // Si associano valori e posizioni di placeholder
            stmt.setString(1, email);
            ResultSet set = stmt.executeQuery();
            // String nome, String cognome, String email, String password, int conto
            n = new Acquirente (set.getString("nome"),set.getString("cognome"),set.getString("email"),set.getString("password"),set.getInt("conto"));
            stmt.close();
            // chiusura della connessione
            conn.close();
        } 
        catch (SQLException ex) {
            // nel caso la query fallisca (p.e. errori di sintassi)
            // viene sollevata una SQLException
            //Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errore acquirente factory");
        }
        return n;
    }
    
    public ArrayList<Acquirente> getListaAcquirenti() {
        return listaAcquirenti;
    }
}
