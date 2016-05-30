<%-- 
    Document   : historico
    Created on : 20/05/2016, 21:25:57
    Author     : Jonathan Souza <jonathan.ralison@gmail.com>
--%>

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
