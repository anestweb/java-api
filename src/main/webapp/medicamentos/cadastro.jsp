<%-- 
    Document   : cadastro
    Created on : 10/04/2016, 20:16:38
    Author     : Jonathan Souza <jonathan.ralison@gmail.com>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="br.projetointegrador.anestwebm.model.Medicamento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<%
    if ((boolean) request.getAttribute("isEditing")) {
        Integer id = ((Medicamento) request.getAttribute("medicamento")).getId();
        request.setAttribute("actionUrl", "./medicamentos/edicao?id=" + id);
    } else {
        request.setAttribute("actionUrl", "./medicamentos/novo");
    }
%>

<t:mainlayout>
    <h1 class="pageheading">
        Cadastrar Novo Medicamento
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
                <label for="y1">Descrição do Medicamento</label>
                <input type="text" id="y1" name="descricao"
                       value="${medicamento.getDescricao()}" required>
            </div>
        </div>

        <div class="actions">
            <button type="submit" class="primary button"><i class="fa fa-save fa-fw"></i>Salvar</button>
            <a href="./medicamentos" class="button">Cancelar</a>
        </div>
    </form>
</t:mainlayout>
