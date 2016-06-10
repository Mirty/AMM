<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <!-- Pagina dedicata al venditore, per l'inserimento di un nuovo oggetto nel database -->
    <head>
            <title>Eataly - Modifica</title>
            <meta charset="utf-8"/>
            <meta name="author" content="Marta Murtas">
            <meta name="description" content="Milestone 2 per il corso di AMM">
            <meta name="keywords" content="ecommerce, AMM">
            <link rel="stylesheet" type="text/css" href="style.css">
            <script src="js/miglioramentoUI.js"></script>
    </head>

    <body>
        <!-- importo l'header -->
        <jsp:include page="header.jsp" />


        <div id="wrapper">

            <!-- importo la sidebar -->
            <jsp:include page="sidebar.jsp" />


            <!-- Inizio corpo -->
            <div id="corpo">
                <div id="corpo_c">
                    <div class="corpo_c_vantaggi">
                        <p>Da questa pagina sarà  possibile aggiungere modificare il prodotto selezionato.</p>
                        <p><a href="sold.jsp">Visualizza gli oggetti messi in vendita</a></p>
                    </div>
                    <!-- Inizio form -->
                    <form action="Venditore?idProduct=${oggetto.getId()}" method="post">
                        <!-- 
                            Voglio che il venditore inserisca correttamente tutti i dati.
                            Se così non fosse, la servlet Visualizza prende i dati che sono stati
                            inseriti, e li rimanda a questa pagina, in modo che, tramite
                            il valore value impostato per prendere il valore spedito in precedenza,
                            l'utente ritrovi il form ricompilato esattamente come quando ha cliccato 
                            sul tasto aggiungi. In questo modo, in caso di errore, non dovrà
                            reinserire ogni campo ogni volta, ma solo i campi che gli sono 
                            rimasti da compilare. 
                        -->
                        <ol>
                            <li>
                                <div class="corpo_c_passo">
                                    <div class="corpo_c_form_etichette">
                                        <label for="aggiungi_nome">Nome dell'oggetto</label>
                                    </div>
                                    <div class="corpo_c_form_colonna">
                                        <input type="text" name="aggiungi_nome" id="aggiungi_nome" value="${oggetto.getNome()}"/> 
                                    </div>
                                </div>
                            </li>     
                            <li>
                                <div class="corpo_c_passo">
                                    <div class="corpo_c_form_etichette">
                                        <label for="aggiungi_cat">Categoria dell'oggetto</label>
                                    </div>    
                                    <div class="corpo_c_form_colonna">
                                        <select name="categoria">
                                            <c:forEach var="cat" items="${categorie}">
                                                <c:if test="${cat.key==oggetto.getCategoria()}">
                                                    <option value="${cat.key}" selected>${cat.value}</option>
                                                </c:if>
                                                <c:if test="${cat.key!=oggetto.getCategoria()}">
                                                    <option value="${cat.key}">${cat.value}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </li> 
                            <li>
                                <div class="corpo_c_passo">
                                    <div class="corpo_c_form_etichette">
                                        <label for="aggiungi_url">Nome immagine dell'oggetto</label>
                                    </div>    
                                    <div class="corpo_c_form_colonna">
                                        <input type="text" name="url" id="aggiungi_url" value="${oggetto.getUrlImg()}"/> 
                                    </div>
                                </div>
                            </li>    
                            <li>
                                <div class="corpo_c_passo">    
                                    <div class="corpo_c_form_etichette">
                                        <label for="aggiungi_descrizione">Descrizione dell'oggetto</label>
                                    </div>    
                                    <div class="corpo_c_form_colonna">    
                                        <textarea rows="3" cols="20" name="descrizione" id="aggiungi_descrizione" maxlength="80">${oggetto.getDescrizione()}</textarea>
                                    </div>    
                                </div>
                            </li> 
                            <li>
                                <div class="corpo_c_passo">  
                                    <div class="corpo_c_form_etichette">
                                        <label for="aggiungi_prezzo">Prezzo dell'oggetto</label>
                                    </div>   
                                    <div class="corpo_c_form_colonna">
                                        <input type= "number" name="prezzo" id="aggiungi_prezzo"  value="${oggetto.getPrezzo()}" min="0" step="0.01"/>
                                    </div>    
                                </div>
                            </li> 
                            <li>
                                <div class="corpo_c_passo">  
                                    <div class="corpo_c_form_etichette">
                                        <label for="aggiungi_peso">Peso dell'oggetto</label>
                                    </div>   
                                    <div class="corpo_c_form_colonna">
                                        <input type= "number" name="peso" id="aggiungi_peso"  value="${oggetto.getPeso()}" min="0" step="0.01"/>
                                    </div>    
                                </div>
                            </li> 
                            <li>
                                <div class="corpo_c_passo">    
                                    <div class="corpo_c_form_etichette">
                                        <label for="aggiungi_quantita">Quantità </label>
                                    </div>    
                                    <div class="corpo_c_form_colonna">
                                        <input type= "range" name="quantita" id="aggiungi_quantita" min="10" max="1000" step="5"  value="${oggetto.getInStock()}" oninput="ShowRangeValue(this.value)"/>
                                        <div id="range_value"></div>
                                    </div>    
                                </div>
                            </li> 
                            
                        </ol>   
                        <div class="corpo_c_form_pulsante">
                            <button type="submit" name="modifica"> MODIFICA </button>
                        </div>
                    </form>
                    <!-- Fine form -->
                </div>
            </div>
            <!-- Fine corpo -->

            <!-- importo il footer -->
            <jsp:include page="footer.jsp" />
        </div>
    </body>
</html>