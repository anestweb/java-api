<%-- 
    Document   : listagem
    Created on : 10/04/2016, 19:32:56
    Author     : Jonathan Souza <jonathan.ralison@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<t:mainlayout>

    <h1 class="pageheading">
        Catálogo de Profissionais

        <div class="right floated">
            <a href="./profissionais/novo" class="primary button"><i class="fa fa-plus fa-fw"></i>Novo Profissional</a>
        </div>
    </h1>
    <table class="selectable">
        <thead>
            <tr>
                <th class="compact right aligned">CRM</th>
                <th>Nome</th>
                <th>E-mail</th>
                <th class="compact">Opções</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${profissionais.isEmpty()}">
                    <tr>
                        <td colspan="4">
                            Não há profissional cadastrado para esta listagem.
                        </td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${profissionais}" var="profissional">
                        <tr>
                            <td class="right aligned">${profissional.getCrm()}</td>
                            <td>${profissional.getNome().toUpperCase()}</td>
                            <td>${profissional.getEmail()}</td>
                            <td class="nowrap">
                                <a href="./profissionais/edicao?id=${profissional.getId()}"
                                   class="compact button"><i class="fa fa-pencil fa-fw"></i> editar</a>
                                <a href="./profissionais/exclusao?id=${profissional.getId()}"
                                   data-confirm="Remover o profissional ${profissional.getNome()}?"
                                   class="compact button"><i class="fa fa-times fa-fw"></i></a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>

</t:mainlayout>
