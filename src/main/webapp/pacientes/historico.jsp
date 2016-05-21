<%-- 
    Document   : historico
    Created on : 20/05/2016, 21:25:57
    Author     : Jonathan Souza <jonathan.ralison@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.projetointegrador.anestwebm.model.Paciente"%>
<%@page import="java.util.HashMap"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<%
    Paciente paciente = (Paciente) request.getAttribute("paciente");

    String display_nome = paciente.getNomeCompleto().toUpperCase();
    if (display_nome.length() > 37) {
        display_nome = display_nome.substring(0, 37) + "&hellip;";
    }
    request.setAttribute("display_nome", display_nome);

    boolean crianca = (paciente.getIdade() < 12);

    String icone = "user";
    if (crianca) {
        icone = "child";
    } else if (paciente.getGenero() == Paciente.Genero.MASCULINO) {
        icone = "male";
    } else if (paciente.getGenero() == Paciente.Genero.FEMININO) {
        icone = "female";
    }
    request.setAttribute("icone", icone);

    String genero = "";
    if (crianca) {
        if (paciente.getGenero() == Paciente.Genero.MASCULINO) {
            genero = "Menino";
        } else if (paciente.getGenero() == Paciente.Genero.FEMININO) {
            genero = "Menina";
        }
    } else {
        genero = paciente.getGenero().asHomemMulher();
    }
    request.setAttribute("genero", genero);
%>
<t:mainlayout>
    <h1 class="pageheading">
        <i class="fa fa-${icone} fa-fw fa-2x left floated"></i>
        <div class="">
            ${display_nome}
            <br>
            <small class="secondary">
                <c:if test="${genero.isEmpty() == false}">
                    ${genero}
                    &bull;
                </c:if>
                ${paciente.getIdade()} anos
            </small>
        </div>
    </h1>

    <table>
        <thead>
            <tr>
                <th colspan="6" class="center aligned">
                    INFORMAÇÕES CADASTRADAS
                </th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th class="compact nowrap right aligned">NOME COMPLETO</th>
                <td colspan="5">${paciente.getNomeCompleto().toUpperCase()}</td>
            </tr>
            <tr>
                <th class="compact nowrap right aligned">DATA DE NASCIMENTO</th>
                <td class="">${paciente.getDataNascimento()}</td>
                <th class="compact nowrap right aligned">IDADE</th>
                <td class="">${paciente.getIdade()} anos</td>
                <th class="compact nowrap right aligned">GÊNERO</th>
                <td class="">${paciente.getGenero().asString()}</td>
            </tr>
            <tr>
                <th class="compact nowrap right aligned">E-MAIL</th>
                <td>
                    <c:if test="${paciente.getEmail() == null || paciente.getEmail().isEmpty()}">
                        <span class="secondary">--</span>
                    </c:if>
                    ${paciente.getEmail()}
                </td>
                <th class="compact nowrap right aligned">CPF</th>
                <td class="nowrap">
                    <c:if test="${paciente.getCpf() == null || paciente.getCpf().isEmpty()}">
                        <span class="secondary">--</span>
                    </c:if>
                    ${paciente.getCpf()}
                </td>
                <th class="compact nowrap right aligned">RG</th>
                <td class="nowrap">
                    <c:if test="${paciente.getRg() == null || paciente.getRg().isEmpty()}">
                        <span class="secondary">--</span>
                    </c:if>
                    ${paciente.getRg()}
                </td>
            </tr>
        </tbody>
    </table>

    <div class="divider hidden"></div>

    <div class="noprint">
        <a href="./pacientes/avaliacao" class="primary button">
            <i class="fa fa-stethoscope fa-fw"></i> Iniciar uma avaliação</a>
        <a href="./pacientes/edicao?id=${paciente.getId()}" class="button">
            <i class="fa fa-pencil fa-fw"></i> Editar dados</a>
        <a href="./pacientes" class="button right floated">
            <i class="fa fa-arrow-left fa-fw"></i> Retornar à lista de pacientes</a>
        <div class="clearfix"></div>
    </div>

    <div class="divider hidden noprint"></div>

    <table class="selectable">
        <thead>
            <tr>
                <th class="center aligned">
                    AVALIAÇÕES REALIZADAS
                </th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>
                    Não há informação cadastrada para esta listagem.
                </td>
            </tr>
        </tbody>
    </table>

</t:mainlayout>
