package amm.milestone3;

import amm.milestone3.Classi.Oggetto;
import amm.milestone3.Classi.OggettoFactory;
import amm.milestone3.Classi.Utente;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mirty
 * Create una servlet Cliente e mappatela sulla URL cliente.html. La servlet si deve comporare nel modo seguente:
 * •Nel caso l’utente non sia autenticato o non sia un cliente, deve mostrare un messaggio di accesso negato 
 * •Nel caso l’utente sia un cliente, deve mostrare la lista degli oggetti.
 * •Nel caso l’utente selezioni il link per comprare un oggetto, deve mostrare solo un riepilogo dei dati dell’oggetto ed un pulsante per la conferma di acquisto
 * •In caso di conferma dell’acquisto, deve verificare che l’utente abbia abbastanza soldi. In caso positivo deve mostrare un messaggio di avvenuto acquisto, altrimenti un messaggio di errore.
 */
@WebServlet(name = "Acquirente", urlPatterns = {"/Acquirente"})
public class Acquirente extends HttpServlet {

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

        // controllo se la sessione esiste, e quindi se è stato effettuato il login
        // verifico che ad autenticarsi sia stato il cliente e che sto arrivando da login.jsp (ha il button con name accedi)
        if (session.getAttribute("acquirenteLoggedIn")!=null && request.getParameter("accedi") != null) {
            // rimanda alla pagina dell'acquirente autenticato
            request.getRequestDispatcher("cliente.jsp").forward(request, response);
        } // verfico che a essere loggato sia l'acquirente e che arrivi dalla pagina cliente.jsp (productId)
        else if (session.getAttribute("acquirenteLoggedIn")!=null && request.getParameter("productId") != null) {
            // verifico che si stia cercando di visualizzare un oggetto esistente.
            // quindi vedo se il productId appartiene a un oggetto esistente
            // ricavo l'id dell'oggetto passato come parametro
            int idPar = Integer.parseInt(request.getParameter("productId"));
            // verifico l'esistenza di un oggetto con id idPar.. se non esiste lancia un'eccezione (nessuna)
            try {
                Oggetto temp = OggettoFactory.getInstance().getOggettoById(idPar);
                if (temp != null) {
                    // creo l'oggetto selezionato in base all'id
                    // prendo dalla pagina precedente l'oggetto selezionato (dall'immagine del carrello) passato nella url
                    request.setAttribute("oggetto", temp);
                    
                    // poiché voglio che nella pagina di riepilogo solo l'acquirente possa visualizzare il carrello,
                    // poiché nel nome ci potrebbero essere spazi, ma nelle url non si passano gli spazi ma i %20
                    // mi creo una stringa per sostituire gli spazi e metterci %20
                    String nome=temp.getNome();
                    nome=nome.replaceAll(" ", "%20");
                    // imposto una variabile carrello che contiene i dati per la visualizzazione
                    request.setAttribute("carrello", "<div id=\"corpo_c_visualizza_info_generiche_carrello\">\n" +
"                                        <a href=\"Acquirente?nomeOggetto="+nome+"&prezzoOggetto="+temp.getPrezzo()+"&marcaOggetto="+temp.getMarca()+"\"> <!-- Rimanda alla pagina per completare l'acquisto -->\n" +
"                                            <img src=\"imgs/header/carrello.png\" alt=\"Aggiungi al carrello\" height=\"40\" title=\"Acquista "+temp.getNome()+" " +temp.getMarca()+" \">\n" +
"                                        </a>\n" +
"                                    </div>");
                    request.getRequestDispatcher("visualizza_oggetto.jsp").forward(request, response);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } // verfico che a essere loggato sia l'acquirente e vedo se sono stati passati nome, prezzo e marca dell'oggetto che vuol'essere acquistato
        else if (session.getAttribute("acquirenteLoggedIn")!=null && request.getParameter("nomeOggetto") != null
                && request.getParameter("prezzoOggetto") != null && request.getParameter("marcaOggetto") != null) {
            // ora vado a controllare se l'acquirente ha abbastanza soldi nel conto per procedere con l'acquisto
            // rigenero l'utente loggato e ne creo il suo conto
            Utente temp = (Utente) session.getAttribute("acquirente");
            float conto = temp.getConto();
            float prezzo_obj = Float.parseFloat(request.getParameter("prezzoOggetto"));
            if (conto >= prezzo_obj) {
                float residuo = conto - prezzo_obj;
                // setto come messaggio da scrivere...
                request.setAttribute("messaggio", "Hai appena acquistato " + request.getParameter("nomeOggetto") + " " + request.getParameter("marcaOggetto") + ". Nel tuo conto rimangono " + residuo + " €.");
            } else { // l'acquirente non ha abbastanza soldi in conto
                // setto come messaggio da scrivere...
                request.setAttribute("messaggio", "Non hai abbastanza credito per acquistare " + request.getParameter("nomeOggetto") + " " + request.getParameter("marcaOggetto"));
            }
            request.getRequestDispatcher("acquisto.jsp").forward(request, response);
        } else if (session.getAttribute("acquirenteLoggedIn")!=null) {
            request.getRequestDispatcher("cliente.jsp").forward(request, response);
        }

        // se l'acquirente è correttamente loggato E se i parametri sono passati correttamente,
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
