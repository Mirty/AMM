<%-- 
    Document   : filter
    Created on : 3-giu-2016, 14.58.55
    Author     : mirty
--%>

<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<json:array>
    <c:forEach var="oggetto" items="${oggetto}">
        <json:object>
            <json:property name="nome" value="${oggetto.getNome()}"/>
            <json:property name="marca" value="${oggetto.getMarca()}"/>
            <json:property name="img" value="${oggetto.getUrlImg()}"/>
            <json:property name="inStock" value="${oggetto.getInStock()}"/>
            <json:property name="prezzo" value="${oggetto.getPrezzo()}"/>
            <json:property name="peso" value="${oggetto.getPeso()}"/>
            <json:property name="descrizione" value="${oggetto.getDescrizione()}"/>
            <json:property name="id" value="${oggetto.getId()}"/>
        </json:object>
    </c:forEach>
</json:array>
