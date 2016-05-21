<%-- 
    Document   : listagem
    Created on : 10/04/2016, 19:32:56
    Author     : Jonathan Souza <jonathan.ralison@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<t:mainlayout>

    <div class="right floated">
        <a href="./patologias/novo" class="primary button">
            <i class="fa fa-plus fa-fw"></i>Nova Patologia
        </a>
    </div>
    <h1 class="pageheading">Catálogo de Patologias</h1>
    <table class="selectable">
        <thead>
            <tr>
                <th class="compact">Id</th>
                <th>Descrição</th>
                <th class="compact">Opções</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${patologias.isEmpty()}">
                    <tr>
                        <td colspan="3">
                            Não há patologia cadastrada para esta listagem.
                        </td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${patologias}" var="patologia">
                        <tr>
                            <td>${patologia.getId()}</td>
                            <td>${patologia.getDescricao()}</td>
                            <td class="nowrap">
                                <a href="./patologias/edicao?id=${patologia.getId()}"
                                   class="compact button"><i class="fa fa-pencil fa-fw"></i> editar</a>
                                <a href="./patologias/exclusao?id=${patologia.getId()}"
                                   data-confirm="Remover a patologia ${patologia.getId()}?"
                                   class="compact button"><i class="fa fa-times fa-fw"></i></a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>

</t:mainlayout>
