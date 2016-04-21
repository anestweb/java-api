<%-- 
    Document   : listagem
    Created on : 10/04/2016, 19:32:56
    Author     : Jonathan Souza <jonathan.ralison@gmail.com>
--%>

<%@page import="br.projetointegrador.anestwebm.model.Paciente"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<t:mainlayout>

    <h1 class="pageheading">
        Catálogo de Pacientes

        <div class="right floated">
            <a href="./pacientes/novo" class="primary button"><i class="fa fa-plus fa-fw"></i>Novo Paciente</a>
        </div>
    </h1>
    <table class="selectable">
        <thead>
            <tr>
                <th>Nome</th>
                <th>Data de Nascimento</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${pacientes.isEmpty()}">
                    <tr>
                        <td colspan="2">
                            Não há paciente cadastrado para esta listagem.
                        </td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${pacientes}" var="paciente">
                        <tr>
                            <td>${paciente.getNomeCompleto().toUpperCase()}</td>
                            <td>${paciente.getDataNascimento()} (${paciente.getIdade()} anos)</td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>

</t:mainlayout>
