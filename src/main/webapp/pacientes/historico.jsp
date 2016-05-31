<%-- 
    Document   : historico
    Created on : 20/05/2016, 21:25:57
    Author     : Jonathan Souza <jonathan.ralison@gmail.com>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<t:mainlayout>
    <t:paciente_etiqueta />

    <t:paciente_ficha />

    <div class="divider hidden"></div>

    <div class="noprint">
        <a href="./pacientes/avaliacao/nova?pid=${paciente.getId()}" class="primary button">
            <i class="fa fa-stethoscope fa-fw"></i> Iniciar uma avaliação</a>
        <a href="./pacientes/edicao?id=${paciente.getId()}" class="button">
            <i class="fa fa-pencil fa-fw"></i> Editar paciente</a>
        <a href="./pacientes" class="button right floated">
            <i class="fa fa-arrow-left fa-fw"></i> Retornar à lista de pacientes</a>
        <div class="clearfix"></div>
    </div>

    <div class="divider hidden noprint"></div>

    <table class="selectable">
        <thead>
            <tr>
                <th class="center aligned" colspan="4">
                    AVALIAÇÕES REALIZADAS
                </th>
            </tr>
            <tr>
                <th class="compact center aligned">Data</th>
                <th class="">Cirurgia / Patologia</th>
                <th class="">Procedimento Proposto</th>
                <th class="compact">Opções</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${avaliacoes.size() == 0}">
                <tr>
                    <td colspan="4" class="center aligned secondary">
                        Não há ficha de avaliação pré-anestésica registrada
                        para este paciente.
                    </td>
                </tr>
            </c:if>
            <c:forEach items="${avaliacoes}" var="avaliacao">
                <tr>
                    <td>${avaliacao.getDataAvaliacao()}</td>
                    <td>${avaliacao.getCirurgia()}</td>
                    <td>${avaliacao.getProcedimento()}</td>
                    <td class="nowrap">
                        <a href="./pacientes/avaliacao/detalhes?id=${avaliacao.getId()}"
                            class="compact button">abrir</a>
                         <a href="./pacientes/avaliacao/exclusao?id=${avaliacao.getId()}"
                            data-confirm="Remover a avaliação realizada em ${avaliacao.getDataAvaliacao()}?"
                            class="compact button"><i class="fa fa-times fa-fw"></i></a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</t:mainlayout>
