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
        Catálogo de Medicamentos

        <div class="right floated">
            <a href="./medicamentos/novo" class="primary button"><i class="fa fa-plus fa-fw"></i>Novo Medicamento</a>
        </div>
    </h1>
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
                <c:when test="${medicamentos.isEmpty()}">
                    <tr>
                        <td colspan="3">
                            Não há medicamento cadastrado para esta listagem.
                        </td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${medicamentos}" var="medicamento">
                        <tr>
                            <td>${medicamento.getId()}</td>
                            <td>${medicamento.getDescricao()}</td>
                            <td class="nowrap">
                                <a href="./medicamentos/edicao?id=${medicamento.getId()}"
                                   class="compact button"><i class="fa fa-pencil fa-fw"></i> editar</a>
                                <a href="./medicamentos/exclusao?id=${medicamento.getId()}"
                                   data-confirm="Remover o medicamento ${medicamento.getId()}?"
                                   class="compact button"><i class="fa fa-times fa-fw"></i></a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>

</t:mainlayout>
