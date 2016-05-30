<%-- 
    Document   : paciente_ficha
    Created on : 24/05/2016, 22:41:20
    Author     : Jonathan Souza <jonathan.ralison@gmail.com>
--%>

<%@tag description="Tabela de Informações do Paciente" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
