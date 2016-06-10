<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="header">
    <div id="header_c">
        <div id="header_c_sezione_superiore">
            <div id="header_c_sezione_superiore_logo">
                <a href="descrizione.jsp" title="Vai alla Home"><div id="header_c_sezione_superiore_logo_eataly"></div></a>
                <div id="header_c_sezione_superiore_logo_bandiera">
                    <div id="header_c_sezione_superiore_logo_bandiera_verde"></div>
                    <div id="header_c_sezione_superiore_logo_bandiera_bianco"></div>
                    <div id="header_c_sezione_superiore_logo_bandiera_rosso"></div>
                </div>
            </div>
            <div id="header_c_sezione_superiore_utente">
                <c:choose>
                    <c:when test="${loggedIn!=null}" >
                        <a href="Logout"><div id="header_c_sezione_superiore_utente_accesso" title="Esci">LOGOUT</div></a>
                    </c:when>
                    <c:otherwise>
                        <a href="Login"><div id="header_c_sezione_superiore_utente_accesso" title="Accedi">ACCEDI</div></a>
                    </c:otherwise>
                </c:choose>
                <a href="Login"><div id="header_c_sezione_superiore_utente_carrello" title="Vai al carrello"></div></a>
            </div>
        </div>
        <div id="header_c_sezione_inferiore">
            <div class="cat">PASTA</div>
            <div class="cat">FORMAGGI</div>
            <div class="cat">SALUMI</div>
            <div class="cat">VINI</div>
            <div class="cat">LIQUORI</div>
            <div class="cat">ALTRO</div>
        </div>
    </div>
</div>
