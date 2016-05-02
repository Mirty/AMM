package amm.milestone3;

import amm.milestone3.Classi.OggettoFactory;
import amm.milestone3.Classi.Venditore;
import amm.milestone3.Classi.Utente;
import amm.milestone3.Classi.UtenteFactory;
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
 * 
 * Create una servlet Login e mappatela sulla URL login.html. La servlet si deve comportare ne modo seguente: 
 * •Nel caso l’utente non sia autenticato, deve mostrare il form di login (login.jsp) e verificare username e password nel caso siano inviate tramite il form
 * •Nel caso l’utente sia già stato autenticato (durante la gestione della richiesta corrente o ad una precedente), deve mostrare
 * •La pagina per l’aggiunta di un nuovo oggetto nel caso l’utente sia un venditore (venditore.jsp)
 * •La pagina per l’acquisto di un nuovo oggetto nel caso l’utente sia un cliente (cliente.jsp)
 * •Nel caso l’utente abbia inviato username e password ma l’autenticazione sia fallita, deve mostrare un messaggio di errore e permettere di riprovare.
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        
        // se non esiste già una sessione, la crea
        HttpSession session = request.getSession(true);
        
        // controllo se l'utente ha premuto sul pulsante per il login
        if(request.getParameter("accedi")!=null) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
        
            // Creo la lista degli utenti sfruttando la factory:
            // listaUtenti contiene tutti gli utenti registrati
            // La sto creando per vedere se chi si logga 
            // è contenuto nella lista degli utenti registrati
            ArrayList<Utente> listaUtenti = UtenteFactory.getInstance().getListaUtenti();
            
            // creo la var u che di volta in volta prende un elemento di listaUtenti
            for (Utente u: listaUtenti) {
                if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
                    // setto l'attributo generale di loggedIn
                    session.setAttribute("loggedIn",true);
                    // voglio che l'id dell'utente sia la sua mail
                    // perché mi sembra ridondante creare più chiavi primarie
                    session.setAttribute("email", u.getEmail());
                    // dobbiamo decidere se è venditore o acquirente...
                    if (u instanceof Venditore) {
                        // setto la variabile di sessione in modo da capire chi si è loggato
                        session.setAttribute("venditoreLoggedIn",true);
                        // passo i dati del venditore loggato
                        request.setAttribute("venditore", u);
                        // rimanda alla Servlet Venditore
                        request.getRequestDispatcher("Venditore").forward(request, response);
                    } else {
                        // setto la variabile di sessione in modo da capire chi si è loggato
                        session.setAttribute("acquirenteLoggedIn",true);
                        // serve in cliente.jsp per prendere tutti gli oggetti in vendita da mettere in tabella
                        session.setAttribute("oggetti",OggettoFactory.getInstance().getListaOggetti());
                        // passo i dati dell'acquirente loggato
                        // in questo caso uso la sessione così poter controllare, se necessario
                        // se il cliente ha abbastanza soldi per effettuare l'acquisto
                        session.setAttribute("acquirente", u);
                        // rimanda alla Servlet Acquirente
                        request.getRequestDispatcher("Acquirente").forward(request, response);
                    }
                } 
                else {
                    request.setAttribute("errore", "Dati inseriti non corretti - ");
                }
            }
        } 
        // controllo se la sessione contiene qualcosa.. (se qualcuno si è loggato)
        else if (!session.isNew()){
            if(session.getAttribute("loggedIn")!=null) {
                // se è loggato l'acquirente lo rimando a cliente.jsp
                if (session.getAttribute("acquirenteLoggedIn")!=null) {
                    request.getRequestDispatcher("cliente.jsp").forward(request, response);
                }
                // se è loggato il venditore lo rimando a venditore.jsp
                else {
                    request.getRequestDispatcher("venditore.jsp").forward(request, response);
                }
            }
        }
        // la pagina jsp che effettivamente gira è login.jsp
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
