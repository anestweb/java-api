<%-- 
    Document   : cadastro
    Created on : 10/04/2016, 20:16:38
    Author     : Jonathan Souza <jonathan.ralison@gmail.com>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="br.projetointegrador.anestwebm.model.Profissional"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<%
    if ((boolean) request.getAttribute("isEditing")) {
        Integer id = ((Profissional) request.getAttribute("profissional")).getId();
        request.setAttribute("actionUrl", "./profissionais/edicao?id=" + id);
    } else {
        request.setAttribute("actionUrl", "./profissionais/novo");
    }
%>

<t:mainlayout>
    <h1 class="pageheading">
        Cadastrar Novo Profissional
    </h1>

    <c:if test="${requestScope.error != null}">
        <p class="message message-error">${requestScope.error}</p>
    </c:if>

    <t:validationMessages/>

    <form action="${actionUrl}" method="post"
          enctype="application/x-www-form-urlencoded"
          accept-charset="utf-8">
        <div class="form">
            <div class="flex-row">
                <div class="field grid-2">
                    <label for="y2">CRM</label>
                    <input type="text" id="y2" name="crm"
                           value="${profissional.getCrm()}" required
                           data-mask="crm" pattern="^[0-9]{4,6}\/[A-Z]{2}$">
                </div>

                <div class="field grid-14">
                    <label for="y1">Nome do Profissional</label>
                    <input type="text" id="y1" name="nome"
                           value="${profissional.getNome()}" required>
                </div>
            </div>

            <div class="field">
                <label for="y3">E-mail</label>
                <input type="email" id="y3" name="email"
                       value="${profissional.getEmail()}" required>
            </div>

            <div class="flex-row">
                <div class="field grid-6">
                    <label for="y4">Senha</label>
                    <input type="password" id="y4" name="senha" value="${profissional.getSenha()}"
                           pattern=".{4,24}$" maxlength="16" required>
                </div>
<!--
                <div class="field grid-8">
                    <label for="y5">Confirme a Senha</label>
                    <input type="password" id="y5" name="senha_c" value="${profissional.getSenha()}"
                           required>
                </div>
-->
            </div>
        </div>

        <div class="actions">
            <button type="submit" class="primary button"><i class="fa fa-save fa-fw"></i>Salvar</button>
            <a href="./profissionais" class="button">Cancelar</a>
        </div>
    </form>
</t:mainlayout>
