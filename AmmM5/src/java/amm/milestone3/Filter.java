package amm.milestone3;

import amm.milestone3.Classi.Oggetto;
import amm.milestone3.Classi.OggettoFactory;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mirty
 */
@WebServlet(name = "Filter", urlPatterns = {"/filter.json"})
public class Filter extends HttpServlet {

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
        System.out.println("Sono quì");
        String command = request.getParameter("chiave"); // contine q in js/gestioneRicerca.js
        if (command != null) {
            if (command.equals("q")) {
                ArrayList<Oggetto> listaOggetti;
                
                // Esegue la ricerca basandosi su "valore", ovvero ciò che ho scritto nella barra
                // prende tutti gli oggetti che hanno nome o descrizione simile
                if (!request.getParameter("valore").equals("")) // se la stringa inserita non è vuota...
                    listaOggetti = OggettoFactory.getInstance().getOggettiLike((String)request.getParameter("valore"));
                else // se la stringa inserita è vuota mi faccio restituire tutti gli oggetti
                    listaOggetti = OggettoFactory.getInstance().getListaOggetti();
                
                // Imposto la lista come attributo della request
                request.setAttribute("oggetto", listaOggetti);
                
                // Quando si restituisce del json e' importante segnalarlo ed evitare il caching
                response.setContentType("application/json");
                response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
                response.setHeader("Cache-Control", "no-store, no-cache, "
                        + "must-revalidate");
                
                System.out.println("\n\n"+request.getParameter("valore")+": * dim: "+listaOggetti.size());
                for (Oggetto l : listaOggetti) {
                    System.out.println(l.getNome());
                }
                
                // Genero il json 
                request.getRequestDispatcher("filter.jsp").forward(request, response);
                
                
            }
        }
        
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
