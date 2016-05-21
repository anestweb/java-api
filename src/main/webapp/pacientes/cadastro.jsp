<%-- 
    Document   : cadastro
    Created on : 10/04/2016, 20:16:38
    Author     : Jonathan Souza <jonathan.ralison@gmail.com>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="br.projetointegrador.anestwebm.model.Paciente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<%
    if ((boolean) request.getAttribute("isEditing")) {
        Integer id = ((Paciente) request.getAttribute("paciente")).getId();
        request.setAttribute("actionUrl", "./pacientes/edicao?id=" + id);
    } else {
        request.setAttribute("actionUrl", "./pacientes/novo");
    }

    final char genero = ((Paciente) request.getAttribute("paciente")).getGenero().asChar();
    request.setAttribute("genero", String.valueOf(genero));
%>

<t:mainlayout>
    <h1 class="pageheading">
        Cadastrar Novo Paciente
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
                <div class="field grid-10">
                    <label for="y1">Nome do Paciente</label>
                    <input type="text" id="y1" name="nome_completo"
                           value="${paciente.getNomeCompleto()}" required>
                </div>

                <div class="field grid-3">
                    <label for="y2">Data de Nascimento</label>
                    <input type="text" id="y2" name="data_nascimento"
                           value="${paciente.getDataNascimento()}"
                           data-mask="data" required
                           pattern="^(?:0[1-9]|[12][0-9]|3[01])\/(?:0[1-9]|1[0-2])\/(?:19|20)[0-9]{2}$">
                </div>

                <div class="field grid-3">
                    <label for="sexo">Sexo</label>
                    <select name="sexo" id="sexo" required>
                        <option value="O" ${genero == 'O' ? "selected" : ""}>Não declarado</option>
                        <option value="M" ${genero == 'M' ? "selected" : ""}>Masculino</option>
                        <option value="F" ${genero == 'F' ? "selected" : ""}>Feminino</option>
                    </select>
                </div>
            </div>

            <div class="flex-row">
                <div class="field grid-8">
                    <label for="y4">CPF</label>
                    <input type="text" id="y4" name="cpf" value="${paciente.getCpf()}"
                           data-mask="cpf"
                           pattern="^[0-9]{3}(\.?)[0-9]{3}\1[0-9]{3}\-?[0-9]{2}$">
                </div>

                <div class="field grid-8">
                    <label for="y5">RG</label>
                    <input type="text" id="y5" name="rg" value="${paciente.getRg()}">
                </div>
            </div>

            <div class="field">
                <label for="y3">E-mail</label>
                <input type="email" id="y3" name="email" value="${paciente.getEmail()}">
            </div>
        </div>

        <div class="actions">
            <button type="submit" class="primary button"><i class="fa fa-save fa-fw"></i>Salvar</button>
            <a href="./pacientes" class="button">Cancelar</a>
        </div>
    </form>
</t:mainlayout>
