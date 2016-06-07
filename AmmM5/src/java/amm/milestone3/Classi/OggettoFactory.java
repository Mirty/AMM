package amm.milestone3.Classi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.sql.*;
import java.util.HashMap;
import javax.servlet.http.HttpSession;

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
        listaOggetti = new ArrayList<>();
    }

    /**
     * @return the listaOggetti
     */
    public ArrayList<Oggetto> getListaOggetti() {
        
        String baseUrl = "imgs/obj/";
        listaOggetti = new ArrayList<>();
        /**
         * inserisco gli oggetti da mettere in vendita che si trovano nel db
         */
        try {
            // creazione e apertura della connessione
            // si specifica la url, lo username e la password per il db
            Connection conn = DriverManager.getConnection(connectionString, "APP", "");
            // utilizzo della connessione per inviare una query (select)
            Statement stmt = conn.createStatement();
            String sql = "select * from oggetto join istanza on istanza.idoggetto = oggetto.idOggetto join venditore on venditore.email = oggetto.vendutoDa ORDER BY NOME";
            ResultSet set = stmt.executeQuery(sql);
            while (set.next()) {
                                           // int id, String nome, float prezzo,  String descrizione, float peso, String urlImg, int categoria, String marchio, String venditore, int inStock, int venduti
                listaOggetti.add(new Oggetto(set.getInt("idOggetto"),set.getString("nome"),set.getFloat("prezzo"), set.getString("descrizione"),set.getFloat("peso"),set.getString("urlImg"),set.getInt("categoria"),set.getString("marchio"),set.getString("vendutoDa"),set.getInt("disponibili"),set.getInt("venduti")));
            }
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
        Oggetto n = new Oggetto();
        try {
            // creazione e apertura della connessione
            // si specifica la url, lo username e la password per il db
            Connection conn = DriverManager.getConnection(connectionString, "APP", "");
            // utilizzo della connessione per inviare una query (select)
            // Si mettono dei punti di domanda al posto dei valori
            
            String sql = "select * from oggetto join istanza on istanza.idoggetto = oggetto.idOggetto join venditore on venditore.email = oggetto.vendutoDa  where oggetto.idOggetto= ?";
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
                if (set1.next()) marchio = set1.getString("marchio");

                n = new Oggetto(set.getInt("idOggetto"),set.getString("nome"),set.getFloat("prezzo"), set.getString("descrizione"),set.getFloat("peso"),set.getString("urlImg"),set.getInt("categoria"),set.getString("marchio"),set.getString("vendutoDa"),set.getInt("disponibili"),set.getInt("venduti"));
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
            String sql = "select * from oggetto join istanza on istanza.idoggetto = oggetto.idOggetto join venditore on venditore.email = oggetto.vendutoDa where oggetto.nome = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
             // Si associano valori e posizioni di placeholder
            stmt.setString(1, nome);
            ResultSet set = stmt.executeQuery();
            // int id, String nome, float prezzo,  String descrizione, float peso, String urlImg, String categoria, String marchio
            n = new Oggetto(set.getInt("idOggetto"),set.getString("nome"),set.getFloat("prezzo"), set.getString("descrizione"),set.getFloat("peso"),set.getString("urlImg"),set.getInt("categoria"),set.getString("marchio"),set.getString("vendutoDa"),set.getInt("disponibili"),set.getInt("venduti"));
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
            String sql = "select * from oggetto join venditore on oggetto.vendutoDa=venditore.email join venditore on venditore.email = oggetto.vendutoDa where istanza.idoggetto = oggetto.idOggetto where oggetto.vendutoDa = ? ORDER BY NOME";
            PreparedStatement stmt = conn.prepareStatement(sql);
             // Si associano valori e posizioni di placeholder
            stmt.setString(1, marca);
            ResultSet set = stmt.executeQuery();
             // ciclo sulle righe restituite
            while (set.next()) {
                // int id, String nome, float prezzo,  String descrizione, float peso, String urlImg, String categoria, String marchio
                n.add(new Oggetto(set.getInt("idOggetto"),set.getString("nome"),set.getFloat("prezzo"), set.getString("descrizione"),set.getFloat("peso"),set.getString("urlImg"),set.getInt("categoria"),set.getString("marchio"),set.getString("vendutoDa"),set.getInt("disponibili"),set.getInt("venduti")));
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
            String sql = "select * from oggetto join categoria on oggetto.categoria=categoria.idCategoria join venditore on venditore.email = oggetto.vendutoDa where istanza.idoggetto = oggetto.idOggetto and  categoria.nome = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
             // Si associano valori e posizioni di placeholder
            stmt.setString(1, categoria);
            ResultSet set = stmt.executeQuery();
             // ciclo sulle righe restituite
            while (set.next()) {
                // int id, String nome, float prezzo,  String descrizione, float peso, String urlImg, String categoria, String marchio
                n.add(new Oggetto(set.getInt("idOggetto"),set.getString("nome"),set.getFloat("prezzo"), set.getString("descrizione"),set.getFloat("peso"),set.getString("urlImg"),set.getInt("categoria"),set.getString("marchio"),set.getString("vendutoDa"),set.getInt("disponibili"),set.getInt("venduti")));
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
    
    
    public HashMap <Integer,String> getCategorie () {
        HashMap <Integer,String> cat = new HashMap <> ();
        
        //scorro tutte le categorie
        try {
            // creazione e apertura della connessione
            // si specifica la url, lo username e la password per il db
            Connection conn = DriverManager.getConnection(connectionString, "APP", "");
            // utilizzo della connessione per inviare una query (select)
            // Si mettono dei punti di domanda al posto dei valori
            Statement stmt = conn.createStatement();
            String sql = "select * from categoria";
            ResultSet set = stmt.executeQuery(sql);
            while (set.next()) {
               cat.put(set.getInt("idcategoria"), set.getString("nome"));
            }
            stmt.close();
            // chiusura della connessione
            conn.close();
        } 
        catch (SQLException ex) {
            // nel caso la query fallisca (p.e. errori di sintassi)
            // viene sollevata una SQLException
            //Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errore oggetto factory -> getCategorie : "+ex.getMessage());
        }
        
        return cat;
    }
    
    
    /* TASK 5 */
    public void creaOggetto (Oggetto oggetto) {
        int lastIdObj=0;
        
        // inserisco l'oggetto nella tabella oggetto
        try {
            // creazione e apertura della connessione
            // si specifica la url, lo username e la password per il db
            Connection conn = DriverManager.getConnection(connectionString, "APP", "");
            // utilizzo della connessione per inviare una query (select)
            // Si mettono dei punti di domanda al posto dei valori
            Statement stmt = conn.createStatement();
            String sql = "insert into oggetto values (default, '"+oggetto.getNome()+"',"+oggetto.getPrezzo()+",'"+oggetto.getDescrizione()+"',"+oggetto.getPeso()+",'"+oggetto.getUrlImg()+"',"+oggetto.getCategoria()+",'"+oggetto.getMarca()+"')";
            System.out.println(sql);
            int rows = stmt.executeUpdate(sql);
            if(rows == 1){
                System.out.println("Insert oggetto ok!");
             }
            stmt.close();
            // chiusura della connessione
            conn.close();
        } 
        catch (SQLException ex) {
            // nel caso la query fallisca (p.e. errori di sintassi)
            // viene sollevata una SQLException
            //Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errore oggetto factory -> creaOggetto -> Oggetto : "+ex.getMessage());
        }
        
        // seleziono la chiave dell'ultimo oggetto inserito
        try {
            // creazione e apertura della connessione
            // si specifica la url, lo username e la password per il db
            Connection conn = DriverManager.getConnection(connectionString, "APP", "");
            // utilizzo della connessione per inviare una query (select)
            // Si mettono dei punti di domanda al posto dei valori
            Statement stmt = conn.createStatement();
            String sql = "SELECT max(idoggetto) as \"max\" FROM oggetto";
            ResultSet set = stmt.executeQuery(sql);
            
            if(set.next()){
                lastIdObj = set.getInt("max");
                System.out.println("lastIdObj = "+lastIdObj);
             }
            stmt.close();
            // chiusura della connessione
            conn.close();
        }  
        catch (SQLException ex) {
            // nel caso la query fallisca (p.e. errori di sintassi)
            // viene sollevata una SQLException
            //Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errore oggetto factory -> creaOggetto -> seleziona chiave : "+ex.getMessage());
        }
        
        
        // inserisco l'oggetto nella tabella istanza
        try {
            // creazione e apertura della connessione
            // si specifica la url, lo username e la password per il db
            Connection conn = DriverManager.getConnection(connectionString, "APP", "");
            // utilizzo della connessione per inviare una query (select)
            // Si mettono dei punti di domanda al posto dei valori
            Statement stmt = conn.createStatement();
            String sql = "insert into istanza values ("+lastIdObj+", "+oggetto.getInStock()+",0)";
            System.out.println(sql);
            int rows = stmt.executeUpdate(sql);
            if(rows == 1){
                System.out.println("Insert istanza ok!");
             }
            stmt.close();
            // chiusura della connessione
            conn.close();
        } 
        catch (SQLException ex) {
            // nel caso la query fallisca (p.e. errori di sintassi)
            // viene sollevata una SQLException
            //Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errore oggetto factory -> creaOggetto ->  Istanza: "+ex.getMessage());
        }
    }
    
    public void modificaOggetto (Oggetto oggetto) {
        try {
            // creazione e apertura della connessione
            // si specifica la url, lo username e la password per il db
            Connection conn = DriverManager.getConnection(connectionString, "APP", "");
            Statement stmt = conn.createStatement();
            String sql = "UPDATE oggetto SET nome='"+oggetto.getNome()+"', prezzo="+oggetto.getPrezzo()+",descrizione='"+oggetto.getDescrizione()+"',peso="+oggetto.getPeso()+",urlImg='"+oggetto.getUrlImg()+"',categoria="+oggetto.getCategoria()+" WHERE idOggetto= "+oggetto.getId();
            System.out.println(sql);
            int rows = stmt.executeUpdate(sql);
            if(rows == 1){
                System.out.println("modifica oggetto ok!");
             }
            sql = "UPDATE istanza SET disponibili="+oggetto.getInStock()+" WHERE idOggetto= "+oggetto.getId();
            System.out.println(sql);
            rows = stmt.executeUpdate(sql);
            if(rows == 1){
                System.out.println("modifica istanza ok!");
             }
            stmt.close();
            // chiusura della connessione
            conn.close();
            
        } 
        catch (SQLException ex) {
            // nel caso la query fallisca (p.e. errori di sintassi)
            // viene sollevata una SQLException
            //Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errore oggetto factory -> modificaOggetto : "+ex.getMessage());
        }
    }
    
    public void eliminaOggetto (int id) {
        
        // elimino dalla tabella istanza
        try {
            Connection conn = DriverManager.getConnection(connectionString, "APP", "");
            Statement stmt = conn.createStatement();
            String sql = "DELETE FROM istanza WHERE idOggetto="+id;
            int rows = stmt.executeUpdate(sql);
            if(rows == 1){
                System.out.println("delete from istanza ok!");
             }
            stmt.close();
            // chiusura della connessione
            conn.close();
        } 
        catch (SQLException ex) {
            System.out.println("errore oggetto factory -> eliminaOggetto -> istanza");
        }
        
        // elimino dalla tabella oggetto
        try {
            Connection conn = DriverManager.getConnection(connectionString, "APP", "");
            Statement stmt = conn.createStatement();
            String sql = "DELETE FROM oggetto WHERE idOggetto="+id;
            int rows = stmt.executeUpdate(sql);
            if(rows == 1){
                System.out.println("delete from oggetto ok!");
             }
            stmt.close();
            // chiusura della connessione
            conn.close();
        } 
        catch (SQLException ex) {
            System.out.println("errore oggetto factory -> eliminaOggetto -> oggetto");
        }
    }
    
    public ArrayList <Oggetto> getOggettiByVenditore (String email) {
        ArrayList <Oggetto> objs = new ArrayList <Oggetto>();
        
        // seleziono gli oggetti inseriti dal venditore email
        try {
            // creazione e apertura della connessione
            // si specifica la url, lo username e la password per il db
            Connection conn = DriverManager.getConnection(connectionString, "APP", "");
            // utilizzo della connessione per inviare una query (select)
            // Si mettono dei punti di domanda al posto dei valori
            Statement stmt = conn.createStatement();
            String sql = "SELECT * \n" +
                "from oggetto\n" +
                "join istanza on istanza.idoggetto = oggetto.idOggetto \n" +
                "join venditore on oggetto.vendutoda = venditore.email \n" +
                "where vendutoDa = '"+email+"' order by nome";
            System.out.println(sql);
            ResultSet set = stmt.executeQuery(sql);
            
            while(set.next()){
                                        //int id, String nome, float prezzo,  String descrizione, float peso, String urlImg, int categoria, String marchio, String venditore, int inStock, int venduti
                objs.add(new Oggetto(set.getInt("idOggetto"),set.getString("nome"),set.getFloat("prezzo"), set.getString("descrizione"),set.getFloat("peso"),set.getString("urlImg"),set.getInt("categoria"),set.getString("marchio"),set.getString("vendutoDa"),set.getInt("disponibili"),set.getInt("venduti")));
            }
            stmt.close();
            // chiusura della connessione
            conn.close();
        }  
        catch (SQLException ex) {
            // nel caso la query fallisca (p.e. errori di sintassi)
            // viene sollevata una SQLException
            //Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("errore oggetto factory -> getOggettiByVenditore : "+ex.getMessage());
        }
        
        return objs;
    }
    
    // TASK 6
    public boolean gestioneCompravendita (Oggetto oggetto, String email) throws SQLException {
        // se si verifica un'eccezione quà viene gestita grazie al throws SQLException
        Connection conn = null;
        String sql=" ";
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection(connectionString, "APP", "");
            int disponibili=0, venduti=0;
            float saldo=0;
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            
            
            // TASK 6.1
            // Rimuovere l’oggetto dalla lista di quelli in vendita per il venditore
            // prima di tutto faccio una query per prendere il numero di istanza disponibili
            // secondo: decremento di un'unità questo valore, reimpostandolo
            // faccio la stessa cosa con venduti
            sql = "SELECT disponibili, venduti FROM istanza WHERE idOggetto="+oggetto.getId();
            ResultSet set = stmt.executeQuery(sql);
            if (set.next()) {
                disponibili = set.getInt("disponibili");
                venduti = set.getInt("venduti");
            }
            sql = "UPDATE istanza SET disponibili="+(disponibili-1)+", venduti="+(venduti+1)+ "where idOggetto="+oggetto.getId();
            stmt.executeUpdate(sql);
            
            
            // TASK 6.2
            // Diminuire il credito del cliente del prezzo dell’oggetto
            // prima di tutto vado a controllare che il cliente abbia il credito necessario
            // per acquistare l'oggetto
            sql = "SELECT saldo from conto join utente on utente.conto =  conto.idConto where utente.email='"+email+"' ";
            set = stmt.executeQuery(sql);
            if (set.next()) {
                saldo = set.getFloat("saldo");
            }
            if (saldo<oggetto.getPrezzo()) {
                throw new SQLException();
            }
            // se il cliente ha il credito necessario, decremento il suo credito
            sql = "UPDATE conto SET saldo="+(saldo-oggetto.getPrezzo())+" where idConto=( select idConto from conto join utente on utente.conto = conto.IDconto where utente.email='"+email+"')";
            stmt.executeUpdate(sql);
            
            
            // TASK 6.3
            // Aggiungere il credito nel saldo del venditore
            // prima di tutto prendo il saldo corrente del venditore
            sql = "SELECT saldo from conto join utente on utente.conto =  conto.idConto where utente.email='"+oggetto.getVenditore()+"' ";
            set = stmt.executeQuery(sql);
            if (set.next()) {
                saldo = set.getFloat("saldo");
            }
            // adesso aggiorno il credito del venditore
            sql = "UPDATE conto SET saldo="+(saldo+oggetto.getPrezzo())+"  where idConto=( select idConto from conto join utente on utente.conto = conto.IDconto where utente.email='"+oggetto.getVenditore()+"')";
            stmt.executeUpdate(sql);
            
            
            conn.commit();
        } 
        catch (SQLException ex) {
            System.out.println("errore gestioneCompravendita : "+ex.getMessage()+"\nnella query: "+sql);
            if (conn != null) {
                try {
                   System.err.print("Transaction is being rolled back");
                   conn.rollback();
                } 
                catch(SQLException exc) {
                    System.out.println("errore gestioneCompravendita -> rollback : "+exc.getMessage());
                } 
            }
            return false;
        } 
        finally {
            if (stmt != null) {
                stmt.close();
            }
            conn.setAutoCommit(true);
            // chiusura della connessione
            conn.close();
        }
        
        return true;
    }
    
    // restituisce gli oggetti che hanno nome o marca simile a text
    public ArrayList <Oggetto> getOggettiLike (String text) {
        ArrayList <Oggetto> n = new ArrayList <> ();
        String query=" ";
        try {
            Connection conn = DriverManager.getConnection(connectionString, "APP", "");
            query = "select * from oggetto "
                    + "join istanza on oggetto.idoggetto = istanza.idOggetto "
                    + "join venditore on venditore.email = oggetto.vendutoDa "
                    + "where LOWER(oggetto.nome) LIKE ? OR LOWER(oggetto.descrizione) LIKE ?";         
            PreparedStatement stmt = conn.prepareStatement(query);
            // Assegna dati e faccio le ricerche rendendo tutti i testi 
            // (sia nel db che nella ricerca) lower case
            text = text.toLowerCase();
            text = "%"+text+"%";
            stmt.setString(1, text);
            stmt.setString(2, text);
            ResultSet set = stmt.executeQuery();
            System.out.println("query="+query);
            while(set.next())
            {
                Oggetto current = new Oggetto(set.getInt("idOggetto"),set.getString("nome"),set.getFloat("prezzo"), set.getString("descrizione"),set.getFloat("peso"),set.getString("urlImg"),set.getInt("categoria"),set.getString("marchio"),set.getString("vendutoDa"),set.getInt("disponibili"),set.getInt("venduti"));
                n.add(current);
            }
            
            stmt.close();
            conn.close();
        }
        catch(SQLException ex) {
            System.out.println("errore getOggettiLike : "+ex.getMessage()+"\nquery="+query);
        }
        
        return n;
    }

}
