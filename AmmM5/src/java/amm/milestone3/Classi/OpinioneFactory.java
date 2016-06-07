package amm.milestone3.Classi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author mirty
 */
public class OpinioneFactory {
    private static OpinioneFactory singleton;
    private ArrayList <Opinione> listaOpinioni;
    private String connectionString;

    public void setConnectionString(String s){ 
        this.connectionString = s;
    }
    
    public String getConnectionString(){ 
        return this.connectionString;
    }

    public static OpinioneFactory getInstance() {
        if (singleton == null) singleton = new OpinioneFactory();
        return singleton;
    }

    /**
     * Costruttore
     */
    OpinioneFactory() {/*
        listaOpinioni = new ArrayList <> ();
        try {
            // creazione e apertura della connessione
            // si specifica la url, lo username e la password per il db
            Connection conn = DriverManager.getConnection(connectionString, "admin_65005", "");
            // utilizzo della connessione per inviare una query (select)
            Statement stmt = conn.createStatement();
            String sql = "select * from opinione ";
            ResultSet set = stmt.executeQuery(sql);
            while (set.next()) {
                // int id, String titolo, String descrizione, float valutazione, String scrittaDa
                listaOpinioni.add(new Opinione(set.getInt("id"), set.getString("titolo"), set.getString("descrizione"), set.getFloat("valutazione"), set.getString("lasciataDa")));
            }
            stmt.close();
            // chiusura della connessione
            conn.close();
        } 
        catch (SQLException ex) {
            // nel caso la query fallisca (p.e. errori di sintassi)
            // viene sollevata una SQLException
            //Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errore opinione factory");
        }*/
    }

    /**
     * @return the listaOpinioni
     */
    public ArrayList <Opinione> getListaOpinioni() {
        return listaOpinioni;
    }

    /**
     * @param listaOpinioni the listaOpinioni to set
     */
    public void setListaOpinioni(ArrayList <Opinione> listaOpinioni) {
        this.listaOpinioni = listaOpinioni;
    }
    
    /**
     * getOpinioniRandom -> Restituisce una lista randomica di opinioni
     */
    ArrayList <Opinione> getOpinioniRandom () {
        /**
         * Creo una lista grande max (un numero random)
         * e ci inserisco le opinioni randomicamente
         */
        Random random = new Random();
        // definisco randomicamente la grandezza della lista generata
        // che può andara da 0 a listaOpinioni.size()
        int max=random.nextInt(listaOpinioni.size()+1); 
        // definisco l'arraylist da restituire
        ArrayList <Opinione> res = new ArrayList <> ();
        for (int i=0; i<max; i++)  {
            // aggiunge alla lista da restituire un'opinione preso in modo lineare dalla lista
            // non sono possibili dei doppioni ovviamente
            res.add(listaOpinioni.get(i));
        }
        return res;
    }
    
    
}
