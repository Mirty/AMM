package amm.milestone3;

import amm.milestone3.Classi.Oggetto;
import amm.milestone3.Classi.OggettoFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mirty
 * Create una servlet Venditore e mappatela sulla URL venditore.html. La servlet si deve comportare nel modo seguente:
 * •Nel caso l’utente non sia autenticato o non sia un venditore, deve mostrare un messaggio di accesso negato
 * •Nel caso l’utente sia un venditore, deve mostrare il form di inserimento dell’oggetto.
 * •Nel caso siano inviati i dati relativi all’inserimento di un oggetto, deve mostrare una conferma dell’avvenuto inserimento ed i dati dell’oggetto inserito.

 */
@WebServlet(name = "Venditore", urlPatterns = {"/Venditore"})
public class Venditore extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        // se la sessione non esiste già, non la creo nuova
        HttpSession session = request.getSession(false);

        // verifico che ad autenticarsi sia stato il venditore e che stia arrivando da login.jsp (dove c'è il button con name accedi)
        if (session.getAttribute("venditoreLoggedIn") != null && request.getParameter("accedi") != null) {
            // setto le categorie
            session.setAttribute("categorie", OggettoFactory.getInstance().getCategorie());
            // setto gli oggetti messi in vendita dal venditore
            session.setAttribute("oggetti", OggettoFactory.getInstance().getOggettiByVenditore((String)session.getAttribute("email")));
            // rimanda alla pagina del venditore autenticato
            request.getRequestDispatcher("venditore.jsp").forward(request, response);
        } // controllo se arrivo dalla pagina per l'acquisto dell'oggetto o dalla pagina della creazione del nuovo oggetto
        // basandomi sul name del pulsante
        // INSERIMENTO NUOVO OGGETTO
        else if (session.getAttribute("venditoreLoggedIn") != null && request.getParameter("convalida") != null) { // arrivo da venditore.jsp
            // creo un nuovo oggetto 
            Oggetto temp = new Oggetto();
            // creo una variabile che varrà !=0 quando dovrò tornare alla pagina venditore.jsp per inserire correttamente tutti i dati
            int goBackVenditore = 0;
            // setto le proprietà dell'oggetto in base agli input presi in venditore.jsp
            
            // nome oggetto
            if (!request.getParameter("aggiungi_nome").equals("")) {
                temp.setNome(request.getParameter("aggiungi_nome"));
                // salvo questo dato nel caso mi serva ricompilare la pagina venditore.jsp
                // nel caso in cui non tutti i dati sono stati inseriti correttamente.
                // in questo modo semplifico e velocizzo l'inserimento dati per il venditore
                request.setAttribute("nome", temp.getNome());
            } else {
                goBackVenditore++;
            }
            
            // categoria oggetto
            if (!request.getParameter("categoria").equals("")) {
                temp.setCategoria(Integer.parseInt(request.getParameter("categoria")));
                request.setAttribute("categoria", temp.getCategoria());
            } else {
                goBackVenditore++;
            }
            
            // url img oggetto
            if (!request.getParameter("url").equals("")) {
                temp.setUrlImg(request.getParameter("url"));
                request.setAttribute("url", temp.getUrlImg());
            } else {
                goBackVenditore++;
            }
            
            // prezzo oggetto
            try {
                if (request.getParameter("prezzo") != null) {
                    temp.setPrezzo(Float.parseFloat(request.getParameter("prezzo")));
                    request.setAttribute("prezzo", temp.getPrezzo());
                }
            } catch (Exception e) {
                goBackVenditore++;
            }
            
            // peso oggetto
            try {
                if (request.getParameter("peso") != null) {
                    temp.setPeso(Float.parseFloat(request.getParameter("peso")));
                    request.setAttribute("peso", temp.getPeso());
                }
            } catch (Exception e) {
                goBackVenditore++;
            }
            
            //descrizione oggetto
            if (!request.getParameter("descrizione").equals("")) {
                temp.setDescrizione(request.getParameter("descrizione"));
                request.setAttribute("descrizione", temp.getDescrizione());
            } else {
                goBackVenditore++;
            }
            
            // se n>=1 campi sono vuoti, torna a venditore.jsp per l'inserimento corretto
            if (goBackVenditore > 0) {
                request.getRequestDispatcher("venditore.jsp").forward(request, response);
            }
            
            // setto l'attributo oggetto passandogli come valore l'oggetto Oggetto temp
            // mi serve per la pagina di riepilogo (visualizza_oggetto.jsp)
            
            temp.setInStock(Integer.parseInt((String)request.getParameter("quantita")));
            temp.setMarca((String)session.getAttribute("email"));
            request.setAttribute("oggetto", temp);
            
            // Inserisco l'oggetto nel db
            OggettoFactory.getInstance().creaOggetto(temp);
            session.removeAttribute("oggetti");
            session.setAttribute("oggetti", OggettoFactory.getInstance().getOggettiByVenditore((String)session.getAttribute("email")));
            request.getRequestDispatcher("visualizza_oggetto.jsp").forward(request, response);
        } 
        // se ho cliccato sulla riga (per visualizzare l'oggetto) dalla pagina sold.jsp
        else if (session.getAttribute("venditoreLoggedIn")!=null && request.getParameter("productId") != null) {
            int idPar = Integer.parseInt(request.getParameter("productId"));
            request.setAttribute("oggetto",OggettoFactory.getInstance().getOggettoById(idPar));
            request.getRequestDispatcher("visualizza_oggetto.jsp").forward(request, response);
        } 
        // se ho cliccato su elimina dalla pagina sold.jsp
        else if (session.getAttribute("venditoreLoggedIn")!=null && request.getParameter("DelProductId") != null) {
            int idPar = Integer.parseInt(request.getParameter("DelProductId"));
            OggettoFactory.getInstance().eliminaOggetto(idPar);
            session.removeAttribute("oggetti");
            session.setAttribute("oggetti", OggettoFactory.getInstance().getOggettiByVenditore((String)session.getAttribute("email")));
            request.getRequestDispatcher("sold.jsp").forward(request, response);
        } 
        // se ho cliccato su modifica dalla pagina sold.jsp
        else if (session.getAttribute("venditoreLoggedIn")!=null && request.getParameter("EditProductId") != null) {
            int idPar = Integer.parseInt(request.getParameter("EditProductId"));
            // seleziono l'oggetto che voglio modificare prendendone i dati dal db basandomi sull'id
            request.setAttribute("oggetto", OggettoFactory.getInstance().getOggettoById(idPar));
            request.getRequestDispatcher("edit.jsp").forward(request, response);
        }
        // MODIFICA OGGETTO
        else if (session.getAttribute("venditoreLoggedIn") != null && request.getParameter("modifica") != null && request.getParameter("idProduct")!= null) { // arrivo da edit.jsp
            // creo un nuovo oggetto 
            Oggetto temp = new Oggetto();
            // creo una variabile che varrà !=0 quando dovrò tornare alla pagina venditore.jsp per inserire correttamente tutti i dati
            int goBackVenditore = 0;
            // setto le proprietà dell'oggetto in base agli input presi in venditore.jsp
            
            // nome oggetto
            if (!request.getParameter("aggiungi_nome").equals("")) {
                temp.setNome(request.getParameter("aggiungi_nome"));
                // salvo questo dato nel caso mi serva ricompilare la pagina venditore.jsp
                // nel caso in cui non tutti i dati sono stati inseriti correttamente.
                // in questo modo semplifico e velocizzo l'inserimento dati per il venditore
                request.setAttribute("nome", temp.getNome());
            } else {
                goBackVenditore++;
            }
            
            // categoria oggetto
            if (!request.getParameter("categoria").equals("")) {
                temp.setCategoria(Integer.parseInt(request.getParameter("categoria")));
                request.setAttribute("categoria", temp.getCategoria());
            } else {
                goBackVenditore++;
            }
            
            // url img oggetto
            if (!request.getParameter("url").equals("")) {
                temp.setUrlImg(request.getParameter("url"));
                request.setAttribute("url", temp.getUrlImg());
            } else {
                goBackVenditore++;
            }
            
            // prezzo oggetto
            try {
                if (request.getParameter("prezzo") != null) {
                    temp.setPrezzo(Float.parseFloat(request.getParameter("prezzo")));
                    request.setAttribute("prezzo", temp.getPrezzo());
                }
            } catch (Exception e) {
                goBackVenditore++;
            }
            
            // peso oggetto
            try {
                if (request.getParameter("peso") != null) {
                    temp.setPeso(Float.parseFloat(request.getParameter("peso")));
                    request.setAttribute("peso", temp.getPeso());
                }
            } catch (Exception e) {
                goBackVenditore++;
            }
            
            //descrizione oggetto
            if (!request.getParameter("descrizione").equals("")) {
                temp.setDescrizione(request.getParameter("descrizione"));
                request.setAttribute("descrizione", temp.getDescrizione());
            } else {
                goBackVenditore++;
            }
            
            // se n>=1 campi sono vuoti, torna a edit.jsp per l'inserimento corretto
            if (goBackVenditore > 0) {
                request.getRequestDispatcher("edit.jsp").forward(request, response);
            }
            
            // setto l'attributo oggetto passandogli come valore l'oggetto Oggetto temp
            // mi serve per la pagina di riepilogo (visualizza_oggetto.jsp)
            request.setAttribute("oggetto", temp);
            temp.setInStock(Integer.parseInt((String)request.getParameter("quantita")));
            temp.setMarca((String)session.getAttribute("email"));
            temp.setId(Integer.parseInt((String)request.getParameter("idProduct")));
            
            // Inserisco l'oggetto nel db
            OggettoFactory.getInstance().modificaOggetto(temp);
            session.removeAttribute("oggetti");
            session.setAttribute("oggetti", OggettoFactory.getInstance().getOggettiByVenditore((String)session.getAttribute("email")));
            request.getRequestDispatcher("sold.jsp").forward(request, response);
        } 
        else if (session.getAttribute("venditoreLoggedIn")!=null) {
            request.getRequestDispatcher("venditore.jsp").forward(request, response);
        }

        // se il venditore è correttamente loggato E se i parametri sono passati correttamente,
        // a questo punto del codice neanche ci sia arriva... se siamo quì significa che c'è 
        // un'anomalia.. quindi lo reindirizzo alla pagina di accesso_negato.jsp per la segnalazione dell'errore
        request.getRequestDispatcher("accesso_negato.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
