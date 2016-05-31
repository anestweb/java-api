<%-- 
    Document   : nova
    Created on : 24/05/2016, 22:37:34
    Author     : Jonathan Souza <jonathan.ralison@gmail.com>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<t:mainlayout>
    <a href="./pacientes/historico?id=${paciente.getId()}" class="primary button right floated">
        <i class="fa fa-arrow-left fa-fw"></i> Retornar à ficha do paciente</a>
    <div class="clearfix"></div>

    <t:paciente_ficha />

    <div class="divider hidden"></div>

    <table>
        <thead>
            <tr>
                <th colspan="4" class="center aligned">RESUMO DA CONSULTA</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th class="nowrap compact right aligned">CIRURGIA / PATOLOGIA</th>
                <td>${avaliacao.getCirurgia()}</td>
                <th class="nowrap compact right aligned">PROCEDIMENTO PROPOSTO</th>
                <td>${avaliacao.getProcedimento()}</td>
            </tr>
            <tr>
                <th class="nowrap compact right aligned">DATA DA AVALIAÇÃO</th>
                <td>${avaliacao.getDataAvaliacao()}</td>
                <th class="nowrap compact right aligned"></th>
                <td></td>
            </tr>
        </tbody>
    </table>

    <div class="divider hidden"></div>

    <table>
        <thead>
            <tr>
                <th colspan="8" class="center aligned">AVALIAÇÃO FÍSICA</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th class="nowrap compact right aligned" style="width: 12.5%">PESO</th>
                <td style="width: 12.5%">${avaliacao.getPeso()}</td>
                <th style="width: 12.5%" class="nowrap compact right aligned">ALTURA</th>
                <td style="width: 12.5%">${avaliacao.getAltura()}</td>
                <th style="width: 12.5%" class="nowrap compact right aligned">PRESSÃO ARTERIAL (PA)</th>
                <td style="width: 12.5%">${avaliacao.getPa()}</td>
                <th style="width: 12.5%" class="nowrap compact right aligned">PALIDEZ</th>
                <td style="width: 12.5%">${avaliacao.getPalidez()}</td>
            </tr>
            <tr>
                <th class="nowrap compact right aligned">DENTADURA / PRÓTESE</th>
                <td colspan="3">${avaliacao.getDentadura()}</td>
                <th class="nowrap compact right aligned">DENTES</th>
                <td colspan="3">${avaliacao.getDentes()}</td>
            </tr>
            <tr>
                <th class="nowrap compact right aligned">PESCOÇO</th>
                <td colspan="3">${avaliacao.getPescoco()}</td>
                <th class="nowrap compact right aligned">FLEXÃO DO PESCOÇO</th>
                <td colspan="3">${avaliacao.getPescocoFlexao()}</td>
            </tr>
            <tr>
                <th class="nowrap compact right aligned">ABERTURA DA BOCA</th>
                <td colspan="3">${avaliacao.getBoca()}</td>
                <th class="nowrap compact right aligned">MALLAMPATI</th>
                <td colspan="3">${avaliacao.getMallampati()}</td>
            </tr>
            <tr>
                <th class="nowrap compact right aligned">ANOTAÇÃO COMPLEMENTAR</th>
                <td colspan="7">${avaliacao.getAnotacaoFisico()}</td>
            </tr>
        </tbody>
    </table>

    <div class="divider hidden"></div>

    <table>
        <thead>
            <tr>
                <th colspan="2" class="center aligned">OUTRAS INFORMAÇÕES COMPLEMENTARES</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th class="nowrap compact right aligned">OUTRAS ANOTAÇÕES COMPLEMENTARES</th>
                <td>${avaliacao.getAnotacoes()}</td>
            </tr>
        </tbody>
    </table>

</t:mainlayout>
