<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <!-- Pagina dedicata al venditore, per l'inserimento di un nuovo oggetto nel database -->
    <head>
            <title>Eataly - Venditore</title>
            <meta charset="utf-8"/>
            <meta name="author" content="Marta Murtas">
            <meta name="description" content="Milestone 2 per il corso di AMM">
            <meta name="keywords" content="ecommerce, AMM">
            <link rel="stylesheet" type="text/css" href="style.css">
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
                        <p>Da questa pagina sarà  possibile aggiungere i prodotti che vuoi rivendere tramite <a href="descrizione.jsp">Eataly.com</a>.</p>
                        <p>La procedura per mettere in vendita un nuovo bene è molto semplice. Segui le indicazioni sottostanti. Dovrai inserire:</p>
                        <ul>
                            <li>il nome del bene che vuoi inserire nel database; </li>
                            <li>un'immagine che rappresenti l'oggetto nella maniera più idonea; </li>
                            <li>una breve descrizione dell'oggetto che sia capace di convincere i clienti ad acquistarlo
                                (deve, possibilmente, anche convincerli a comprare per la prima volta il tuo prodotto, se non lo hanno già  provato);</li>
                            <li>il prezzo del tuo bene;</li>
                            <li>la quantità  del bene che sei disposto a mettere nei nostri magazzini.</li>
                        </ul>
                    </div>
                    <!-- Inizio form -->
                    <form action="Venditore" method="post">
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
                                        <input type="text" name="aggiungi_nome" id="aggiungi_nome" value="${nome}"/> 
                                    </div>
                                </div>
                            </li>    
                            <li>
                                <div class="corpo_c_passo">
                                    <div class="corpo_c_form_etichette">
                                        <label for="aggiungi_url">URL immagine dell'oggetto</label>
                                    </div>    
                                    <div class="corpo_c_form_colonna">
                                        <input type="url" name="url" id="aggiungi_url" value="${url}"/> 
                                    </div>
                                </div>
                            </li>     
                            <li>
                                <div class="corpo_c_passo">
                                    <div class="corpo_c_form_etichette">
                                        <label for="aggiungi_marca">Marca dell'oggetto</label>
                                    </div>    
                                    <div class="corpo_c_form_colonna">
                                        <input type="text" name="marca" id="aggiungi_marca" value="${marca}"/> 
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="corpo_c_passo">    
                                    <div class="corpo_c_form_etichette">
                                        <label for="aggiungi_descrizione">Descrizione dell'oggetto</label>
                                    </div>    
                                    <div class="corpo_c_form_colonna">    
                                        <textarea rows="3" cols="20" name="descrizione" id="aggiungi_descrizione" maxlength="80">${descrizione}</textarea>
                                    </div>    
                                </div>
                            </li> 
                            <li>
                                <div class="corpo_c_passo">  
                                    <div class="corpo_c_form_etichette">
                                        <label for="aggiungi_prezzo">Prezzo dell'oggetto</label>
                                    </div>   
                                    <div class="corpo_c_form_colonna">
                                        <input type= "number" name="prezzo" id="aggiungi_prezzo"  value="${prezzo}"/>
                                    </div>    
                                </div>
                            </li> 
                            <li>
                                <div class="corpo_c_passo">    
                                    <div class="corpo_c_form_etichette">
                                        <label for="aggiungi_quantita">Quantità </label>
                                    </div>    
                                    <div class="corpo_c_form_colonna">
                                        <input type= "range" name="quantita" id="aggiungi_quantita" min="10" max="1000" step="5"  value="${quantita}"/>
                                    </div>    
                                </div>
                            </li> 
                        </ol>   
                        <div class="corpo_c_form_pulsante">
                            <button type="submit" name="convalida"> AGGIUNGI </button>
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