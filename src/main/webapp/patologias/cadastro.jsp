<%-- 
    Document   : cadastro
    Created on : 10/04/2016, 20:16:38
    Author     : Jonathan Souza <jonathan.ralison@gmail.com>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="br.projetointegrador.anestwebm.model.Patologia"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<%
    if ((boolean) request.getAttribute("isEditing")) {
        Integer id = ((Patologia) request.getAttribute("patologia")).getId();
        request.setAttribute("actionUrl", "./patologias/edicao?id=" + id);
    } else {
        request.setAttribute("actionUrl", "./patologias/novo");
    }
%>

<t:mainlayout>
    <h1 class="pageheading">
        Cadastrar Nova Patologia
    </h1>

    <c:if test="${requestScope.error != null}">
        <p class="message message-error">${requestScope.error}</p>
    </c:if>

    <t:validationMessages/>

    <form action="${actionUrl}" method="post"
          enctype="application/x-www-form-urlencoded"
          accept-charset="utf-8">
        <div class="form">
            <div class="field">
                <label for="y1">Descrição da Patologia</label>
                <input type="text" id="y1" name="descricao"
                       value="${patologia.getDescricao()}" required>
            </div>
        </div>

        <div class="actions">
            <button type="submit" class="primary button"><i class="fa fa-save fa-fw"></i>Salvar</button>
            <a href="./patologias" class="button">Cancelar</a>
        </div>
    </form>
</t:mainlayout>
