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
    
    AcquirenteFactory () {}
    
    public Acquirente getAcquirenteByEmail (String email) {
        Acquirente n = null;
        Conto c = null;
        try {
            // creazione e apertura della connessione
            // si specifica la url, lo username e la password per il db
            Connection conn = DriverManager.getConnection(connectionString, "APP", "");
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
            
            sql = "select * from utente where email= ?";
            stmt = conn.prepareStatement(sql);
             // Si associano valori e posizioni di placeholder
            stmt.setString(1, email);
            set = stmt.executeQuery();
            if (set.next())// String nome, String cognome, String email, String password, int conto
                n = new Acquirente (set.getString("nome"),set.getString("cognome"),set.getString("email"),set.getString("password"),c);
            stmt.close();
            // chiusura della connessione
            conn.close();
        } 
        catch (SQLException ex) {
            // nel caso la query fallisca (p.e. errori di sintassi)
            // viene sollevata una SQLException
            //Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errore getAcquirenteByEmail :"+ex.getMessage());
        }
        return n;
    }
    
    public ArrayList<Acquirente> getListaAcquirenti() {
        return listaAcquirenti;
    }
    
    public void ricarica (String email, float ricarica) {
        // inserisco l'oggetto nella tabella oggetto
        try {
            // creazione e apertura della connessione
            // si specifica la url, lo username e la password per il db
            Connection conn = DriverManager.getConnection(connectionString, "APP", "");
            
            int idConto=0;
            float saldo=0;
            
            // prima di tutto prendo l'id del conto
            Statement stmt = conn.createStatement();
            String sql = "select * from utente join conto on conto.idconto=utente.conto where email = '"+email+"' ";
            ResultSet set = stmt.executeQuery(sql);
            while (set.next()) {
                idConto = set.getInt("conto");
                saldo = set.getFloat("saldo");
            }
            
            // utilizzo della connessione per inviare una query (select)
            // Si mettono dei punti di domanda al posto dei valori
            sql = "update conto set saldo= "+(saldo+ricarica)+" where idconto="+idConto;
            int rows = stmt.executeUpdate(sql);
            if(rows == 1){
                System.out.println("Update ricarica ok!");
             }
            stmt.close();
            // chiusura della connessione
            conn.close();
        } 
        catch (SQLException ex) {
            // nel caso la query fallisca (p.e. errori di sintassi)
            // viene sollevata una SQLException
            //Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errore ricarica : "+ex.getMessage());
        }
    }
}
