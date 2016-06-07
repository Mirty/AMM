package amm.milestone3.Classi;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import java.util.ArrayList;

/**
 *
 * @author mirty
 */
public class ContoFactory {
    
    private static ContoFactory singleton;
    private ArrayList <Conto> listaConti;
    private String connectionString;

    public void setConnectionString(String s){ 
        this.connectionString = s;
    }
    
    public String getConnectionString(){ 
        return this.connectionString;
    }

    public static ContoFactory getInstance() {
        if (singleton == null) singleton = new ContoFactory();
        return singleton;
    }
    
    /**
     * Costruttore
     */
    ContoFactory () {/*
        listaConti = new ArrayList <Conto> ();
        try {
            // creazione e apertura della connessione
            // si specifica la url, lo username e la password per il db
            Connection conn = DriverManager.getConnection(connectionString, "admin_65005", "");
            // utilizzo della connessione per inviare una query (select)
            Statement stmt = conn.createStatement();
            String sql = "select * from conto ";
            ResultSet set = stmt.executeQuery(sql);
            while (set.next()) {
                // int id, float saldo
                listaConti.add(new Conto(set.getInt("id"), set.getFloat("saldo")));
            }
            stmt.close();
            // chiusura della connessione
            conn.close();
        } 
        catch (SQLException ex) {
            // nel caso la query fallisca (p.e. errori di sintassi)
            // viene sollevata una SQLException
            //Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errore conto factory");
        }*/
    }

    /**
     * @return the listaConti
     */
    public ArrayList <Conto> getListaConti() {
        return listaConti;
    }

    /**
     * @param listaConti the listaConti to set
     */
    public void setListaConti(ArrayList <Conto> listaConti) {
        this.listaConti = listaConti;
    }
    
    public Conto getContoById (int id) {
        Conto n = null;
        try {
            // creazione e apertura della connessione
            // si specifica la url, lo username e la password per il db
            Connection conn = DriverManager.getConnection(connectionString, "admin_65005", "");
            // utilizzo della connessione per inviare una query (select)
            // Si mettono dei punti di domanda al posto dei valori
            String sql = "select * from conto where idConto = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
             // Si associano valori e posizioni di placeholder
            stmt.setInt(1, id);
            ResultSet set = stmt.executeQuery();
            // int id, float saldo
            n = new Conto (set.getInt("idOggetto"), set.getFloat("saldo"));
            stmt.close();
            // chiusura della connessione
            conn.close();
        } 
        catch (SQLException ex) {
            // nel caso la query fallisca (p.e. errori di sintassi)
            // viene sollevata una SQLException
            //Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errore conto factory -> getContoById");
        }
        return n;
    }
}
