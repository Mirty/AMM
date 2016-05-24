package amm.milestone3.Classi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Random;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mirty
 */
public class OggettoFactory {
    
    private static OggettoFactory singleton;
    private ArrayList<Oggetto> listaOggetti;
    private String connectionString;

    public void setConnectionString(String s) {
        this.connectionString = s;
    }

    public String getConnectionString() {
        return this.connectionString;
    }

    public static OggettoFactory getInstance() {
        if (singleton == null) singleton = new OggettoFactory();
        return singleton;
    }

    // con il costruttore vuoto creo degli oggetti (dati fittizi)
    OggettoFactory() {
        /**
         * inizializzo l'array list che contiene la lista degli oggetti venduti
         * sul sito.
         */
        listaOggetti = new ArrayList<>();/*
        Random random = new Random();
        
        /**
         * inserisco gli oggetti da mettere in vendita che si trovano nel db
         *//*
        try {
            // creazione e apertura della connessione
            // si specifica la url, lo username e la password per il db
            Connection conn = DriverManager.getConnection(connectionString, "admin_65005", "");
            // utilizzo della connessione per inviare una query (select)
            Statement stmt = conn.createStatement();
            // int id, String nome, float prezzo,  String descrizione, float peso, String urlImg, String categoria, String marchio
            String sql = "select * from oggetto";
            ResultSet set = stmt.executeQuery(sql);
            while (set.next()) {
                int id = set.getInt("id");
                System.out.println(id);
                listaOggetti.add(new Oggetto(set.getInt("idOggetto"),set.getString("nome"),set.getFloat("prezzo"), set.getString("descrizione"),set.getFloat("peso"),set.getString(baseUrl+"urlImg"),set.getString("categoria"),set.getString("marchio")));
            }
            stmt.close();
            // chiusura della connessione
            conn.close();
        } 
        catch (SQLException ex) {
            // nel caso la query fallisca (p.e. errori di sintassi)
            // viene sollevata una SQLException
            //Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errore oggetto factory");
        }*/
    }

    /**
     * @return the listaOggetti
     */
    public ArrayList<Oggetto> getListaOggetti() {
        
        String baseUrl = "imgs/obj/";
        /**
         * inserisco gli oggetti da mettere in vendita che si trovano nel db
         */
        try {
            // creazione e apertura della connessione
            // si specifica la url, lo username e la password per il db
            Connection conn = DriverManager.getConnection(connectionString, "APP", "");
            // utilizzo della connessione per inviare una query (select)
            Statement stmt = conn.createStatement();
            String sql = "select * from oggetto join istanza on istanza.idoggetto = oggetto.idOggetto";
            ResultSet set = stmt.executeQuery(sql);
            while (set.next()) {
                                            // int id, String nome, float prezzo,  String descrizione, float peso, String urlImg, String categoria, String marchio
                listaOggetti.add(new Oggetto(set.getInt("idOggetto"),set.getString("nome"),set.getFloat("prezzo"), set.getString("descrizione"),set.getFloat("peso"),set.getString("urlImg"),set.getInt("categoria"),set.getString("marchio"),set.getInt("disponibili")));
                System.out.println("inserisco oggetto");
            }
            System.out.println("fine inserimento");
            stmt.close();
            // chiusura della connessione
            conn.close();
        } 
        catch (SQLException ex) {
            // nel caso la query fallisca (p.e. errori di sintassi)
            // viene sollevata una SQLException
            //Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errore oggetto factory: "+ex.getMessage());
        }
        
        return listaOggetti;
    }

    /**
     * @param listaOggetti the listaOggetti to set
     */
    public void setListaOggetti(ArrayList<Oggetto> listaOggetti) {
        this.listaOggetti = listaOggetti;
    }

    /**
     * getOggettoById -> Restituisce l'oggetto in base all'id
     * @param id
     */
    public Oggetto getOggettoById(int id) {
        Oggetto n = null;
        try {
            // creazione e apertura della connessione
            // si specifica la url, lo username e la password per il db
            Connection conn = DriverManager.getConnection(connectionString, "APP", "");
            // utilizzo della connessione per inviare una query (select)
            // Si mettono dei punti di domanda al posto dei valori
            
            String sql = "select * from oggetto join istanza on istanza.idoggetto = oggetto.idOggetto where oggetto.idOggetto= ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
             // Si associano valori e posizioni di placeholder
            stmt.setInt(1, id);
            ResultSet set = stmt.executeQuery();
            if (set.next()) {
                // marchio
                sql = "select marchio from venditore where email= ?";
                stmt = conn.prepareStatement(sql);
                 // Si associano valori e posizioni di placeholder
                stmt.setString(1, set.getString("marchio"));
                ResultSet set1 = stmt.executeQuery();
                String marchio=" ";
                if (set1.next())
                    marchio = set1.getString("marchio");

                // int id, String nome, float prezzo,  String descrizione, float peso, String urlImg, String categoria, String marchio
                n = new Oggetto (set.getInt("idOggetto"), set.getString("nome"),set.getFloat("prezzo"),set.getString("descrizione"),set.getFloat("peso"),set.getString("urlimg"),set.getInt("categoria"),marchio,set.getInt("disponibili"));
            }
            stmt.close();
            // chiusura della connessione
            conn.close();
        } 
        catch (SQLException ex) {
            // nel caso la query fallisca (p.e. errori di sintassi)
            // viene sollevata una SQLException
            //Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errore oggetto factory -> getOggettoById, "+ex.getMessage());
        }
        return n;
    }

    /**
     * getOggettoByNome -> Restituisce l'oggetto che ha come nome la stringa
     * passata come parametro
     */
    public Oggetto getOggettoByNome(String nome) {
        Oggetto n = null;
        try {
            // creazione e apertura della connessione
            // si specifica la url, lo username e la password per il db
            Connection conn = DriverManager.getConnection(connectionString, "APP", "");
            // utilizzo della connessione per inviare una query (select)
            // Si mettono dei punti di domanda al posto dei valori
            String sql = "select * from oggetto join istanza on istanza.idoggetto = oggetto.idOggetto where oggetto.nome = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
             // Si associano valori e posizioni di placeholder
            stmt.setString(1, nome);
            ResultSet set = stmt.executeQuery();
            // int id, String nome, float prezzo,  String descrizione, float peso, String urlImg, String categoria, String marchio
            n = new Oggetto(set.getInt("idOggetto"), set.getString("nome"),set.getFloat("prezzo"),set.getString("descrizione"),set.getFloat("peso"),set.getString("urlImg"),set.getInt("categoria"),set.getString("marchio"),set.getInt("disponibili"));
            stmt.close();
            // chiusura della connessione
            conn.close();
        } 
        catch (SQLException ex) {
            // nel caso la query fallisca (p.e. errori di sintassi)
            // viene sollevata una SQLException
            //Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errore oggetto factory -> getOggettoByNome");
        }
        return n;
    }
    
    /**
     * getOggettiByMarca -> Restituisce la lista di oggetti della marca passata
     * come parametro
     */
    public ArrayList<Oggetto> getOggettiByMarca(String marca) {
        ArrayList<Oggetto> n = new ArrayList<>();
        try {
            // creazione e apertura della connessione
            // si specifica la url, lo username e la password per il db
            Connection conn = DriverManager.getConnection(connectionString, "APP", "");
            // utilizzo della connessione per inviare una query (select)
            // Si mettono dei punti di domanda al posto dei valori
            String sql = "select * from oggetto join venditore on oggetto.marchio=venditore.email and istanza where istanza.idoggetto = oggetto.idOggetto where oggetto.marchio = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
             // Si associano valori e posizioni di placeholder
            stmt.setString(1, marca);
            ResultSet set = stmt.executeQuery();
             // ciclo sulle righe restituite
            while (set.next()) {
                // int id, String nome, float prezzo,  String descrizione, float peso, String urlImg, String categoria, String marchio
                n.add(new Oggetto(set.getInt("idOggetto"), set.getString("nome"),set.getFloat("prezzo"),set.getString("descrizione"),set.getFloat("peso"),set.getString("urlImg"),set.getInt("categoria"),set.getString("marchio"),set.getInt("disponibili")));
            }
            stmt.close();
            // chiusura della connessione
            conn.close();
        } 
        catch (SQLException ex) {
            // nel caso la query fallisca (p.e. errori di sintassi)
            // viene sollevata una SQLException
            //Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errore oggetto factory -> getOggettiByMarca");
        }
        return n;
    }

    /**
     * getOggettiByCategoria -> Restituisce la lista di oggetti che hanno come
     * categoria quella specificata come parametro
     */
    public ArrayList<Oggetto> getOggettiByCategoria(String categoria) {
        ArrayList<Oggetto> n = new ArrayList<>();
        try {
            // creazione e apertura della connessione
            // si specifica la url, lo username e la password per il db
            Connection conn = DriverManager.getConnection(connectionString, "APP", "");
            // utilizzo della connessione per inviare una query (select)
            // Si mettono dei punti di domanda al posto dei valori
            String sql = "select * from oggetto join categoria on oggetto.categoria=categoria.idCategoria and istanza where istanza.idoggetto = oggetto.idOggetto and  categoria.nome = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
             // Si associano valori e posizioni di placeholder
            stmt.setString(1, categoria);
            ResultSet set = stmt.executeQuery();
             // ciclo sulle righe restituite
            while (set.next()) {
                // int id, String nome, float prezzo,  String descrizione, float peso, String urlImg, String categoria, String marchio
                n.add(new Oggetto(set.getInt("idOggetto"), set.getString("nome"),set.getFloat("prezzo"),set.getString("descrizione"),set.getFloat("peso"),set.getString("urlImg"),set.getInt("categoria"),set.getString("marchio"),set.getInt("disponibili")));
            }
            stmt.close();
            // chiusura della connessione
            conn.close();
        } 
        catch (SQLException ex) {
            // nel caso la query fallisca (p.e. errori di sintassi)
            // viene sollevata una SQLException
            //Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errore oggetto factory -> getOggettiByCategoria");
        }
        return n;
    }
    
    
    /* TASK 5 */
    public void creaOggetto (Oggetto oggetto) {
        // inserisco l'oggetto nella tabella oggetto
        try {
            // creazione e apertura della connessione
            // si specifica la url, lo username e la password per il db
            Connection conn = DriverManager.getConnection(connectionString, "APP", "");
            // utilizzo della connessione per inviare una query (select)
            // Si mettono dei punti di domanda al posto dei valori
            String sql = "insert into oggetto values (default, ?,?,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
             // Si associano valori e posizioni di placeholder
            stmt.setString(1, oggetto.getNome());
            stmt.setFloat(2, oggetto.getPrezzo());
            stmt.setString(3, oggetto.getDescrizione());
            stmt.setFloat(4, oggetto.getPeso());
            stmt.setString(5, oggetto.getUrlImg());
            stmt.setInt(6, oggetto.getCategoria());
            stmt.setString(7, oggetto.getMarca());
            ResultSet set = stmt.executeQuery();
            
            if (set.next()){
                System.out.println("oggetto aggiunto");
            }
            stmt.close();
            // chiusura della connessione
            conn.close();
        } 
        catch (SQLException ex) {
            // nel caso la query fallisca (p.e. errori di sintassi)
            // viene sollevata una SQLException
            //Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errore oggetto factory -> creaOggetto");
        }
        /*
        // inserisco l'oggetto nella tabella istanza
        try {
            // creazione e apertura della connessione
            // si specifica la url, lo username e la password per il db
            Connection conn = DriverManager.getConnection(connectionString, "APP", "");
            // utilizzo della connessione per inviare una query (select)
            // Si mettono dei punti di domanda al posto dei valori
            String sql = "insert into instanza values (default, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
             // Si associano valori e posizioni di placeholder
            stmt.setString(1, oggetto.get());
            stmt.setFloat(2, oggetto.getPrezzo());
            stmt.setString(3, oggetto.getDescrizione());
            stmt.setFloat(4, oggetto.getPeso());
            stmt.setString(5, oggetto.getUrlImg());
            stmt.setString(6, oggetto.getCategoria());
            stmt.setString(7, oggetto.getMarca());
            ResultSet set = stmt.executeQuery();
            stmt.close();
            // chiusura della connessione
            conn.close();
            
            // aggiorno anche la lista
            listaOggetti.add(oggetto);
        } 
        catch (SQLException ex) {
            // nel caso la query fallisca (p.e. errori di sintassi)
            // viene sollevata una SQLException
            //Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errore oggetto factory -> creaOggetto");
        }*/
    }
    
    public void modificaOggetto (Oggetto oggetto) {
        try {
            // creazione e apertura della connessione
            // si specifica la url, lo username e la password per il db
            Connection conn = DriverManager.getConnection(connectionString, "APP", "");
            // utilizzo della connessione per inviare una query (select)
            // Si mettono dei punti di domanda al posto dei valori
            String sql = "UPDATE oggetto SET nome=?,prezzo=?,descrizione=?,peso=?,urlimg=?,categoria=? WHERE idOggetto=?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
             // Si associano valori e posizioni di placeholder
            stmt.setString(1, oggetto.getNome());
            stmt.setFloat(2, oggetto.getPrezzo());
            stmt.setString(3, oggetto.getDescrizione());
            stmt.setFloat(4, oggetto.getPeso());
            stmt.setString(5, oggetto.getUrlImg());
            stmt.setInt(6, oggetto.getCategoria());
            stmt.setInt(7, oggetto.getId());
            ResultSet set = stmt.executeQuery();
            stmt.close();
            // chiusura della connessione
            conn.close();
            
            // aggiorno anche la lista
            listaOggetti.add(oggetto);
        } 
        catch (SQLException ex) {
            // nel caso la query fallisca (p.e. errori di sintassi)
            // viene sollevata una SQLException
            //Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errore oggetto factory -> modificaOggetto");
        }
    }
    
    public void eliminaOggetto (Oggetto oggetto) {
        try {
            // creazione e apertura della connessione
            // si specifica la url, lo username e la password per il db
            Connection conn = DriverManager.getConnection(connectionString, "APP", "");
            // utilizzo della connessione per inviare una query (select)
            // Si mettono dei punti di domanda al posto dei valori
            String sql = "DELETE FROM  oggetto WHERE idOggetto=?;";
            PreparedStatement stmt = conn.prepareStatement(sql);
             // Si associano valori e posizioni di placeholder
            stmt.setInt(1, oggetto.getId());
            ResultSet set = stmt.executeQuery();
            stmt.close();
            // chiusura della connessione
            conn.close();
            
            // aggiorno anche la lista
            listaOggetti.add(oggetto);
        } 
        catch (SQLException ex) {
            // nel caso la query fallisca (p.e. errori di sintassi)
            // viene sollevata una SQLException
            //Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errore oggetto factory -> eliminaOggetto");
        }
    }

}
