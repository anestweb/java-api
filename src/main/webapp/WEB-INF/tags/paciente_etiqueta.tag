<%-- 
    Document   : paciente_etiqueta
    Created on : 24/05/2016, 22:45:48
    Author     : Jonathan Souza <jonathan.ralison@gmail.com>
--%>

<%@tag import="br.projetointegrador.anestwebm.model.Paciente"%>
<%@tag description="" pageEncoding="UTF-8"%>

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
