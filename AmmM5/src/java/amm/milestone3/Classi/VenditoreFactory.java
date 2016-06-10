package amm.milestone3.Classi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author mirty
 */
public class VenditoreFactory {
    
    private static VenditoreFactory singleton;
    ArrayList <Venditore> listaVenditori;
    private String connectionString;

    public void setConnectionString(String s){ 
        this.connectionString = s;
    }
    
    public String getConnectionString(){ 
        return this.connectionString;
    }

    public static VenditoreFactory getInstance() {
        if (singleton == null) singleton = new VenditoreFactory();
        return singleton;
    }
    
    VenditoreFactory () {}
    
    Venditore getVenditoreByEmail (String email) {
        Venditore n = null;
        Conto c = null;
        try {
            // creazione e apertura della connessione
            // si specifica la url, lo username e la password per il db
            Connection conn = DriverManager.getConnection(connectionString, "admin_65005", "");
            // utilizzo della connessione per inviare una query (select)
            // Si mettono dei punti di domanda al posto dei valori
            
            // prendo le info sul conto
            String sql = "select * from conto join utente on utente.conto = conto.idConto where email= ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
             // Si associano valori e posizioni di placeholder
            stmt.setString(1, email);
            ResultSet set = stmt.executeQuery();
            if (set.next())
                c = new Conto (set.getInt("idconto"),set.getFloat("saldo"));
            
            sql = "select * from venditore where email= ?";
            stmt = conn.prepareStatement(sql);
             // Si associano valori e posizioni di placeholder
            stmt.setString(1, email);
            set = stmt.executeQuery();
            // String nome, String cognome, String email, String password, int conto, String marca
            n = new Venditore (set.getString("nome"),set.getString("cognome"),set.getString("email"),set.getString("password"),c,set.getString("marchio"));
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
    
    public ArrayList<Venditore> getListaVenditori() {
        return listaVenditori;
    }
}
