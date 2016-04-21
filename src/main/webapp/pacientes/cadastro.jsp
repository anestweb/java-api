<%-- 
    Document   : cadastro
    Created on : 10/04/2016, 20:16:38
    Author     : Jonathan Souza <jonathan.ralison@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<t:mainlayout>
    <h1 class="pageheading">
        Cadastrar Novo Paciente
    </h1>

    <p class="error">${requestScope.error}</p>

    <form action="./pacientes/novo" method="post" enctype="application/x-www-form-urlencoded">
        <div class="form">
            <div class="input">
                <input type="text" id="y1" name="nome_completo" value="${param.nome_completo}" required>
                <label for="y1">Nome do Paciente</label>
            </div>

            <div class="input">
                <input type="text" id="y2" name="data_nascimento"
                       value="${param.data_nascimento}"
                       pattern="^(?:0[1-9]|[12][0-9]|3[01])\/(?:0[1-9]|1[0-2])\/(?:19|20)[0-9]{2}$" required>
                <label for="y2">Data de Nascimento</label>
            </div>

            <div class="input">
                <select name="sexo" id="sexo">
                    <option value=""></option>
                    <option value="M">Masculino</option>
                    <option value="F">Feminino</option>
                    <option value="O">Outro</option>
                </select>
                <label for="sexo">Sexo</label>
            </div>

            <div class="input">
                <input type="email" id="y3" name="email" value="${param.email}" required>
                <label for="y3">E-mail</label>
            </div>

            <div class="input">
                <input type="text" id="y4" name="cpf" value="${param.cpf}"
                       pattern="^(?:[0-9]{3}\.){2}[0-9]{3}\-[0-9]{2}$" required>
                <label for="y4">CPF</label>
            </div>

            <div class="input">
                <input type="text" id="y5" name="rg" value="${param.rg}"
                       pattern="^\d{1,3}(?:\.\d{3})*$">
                <label for="y5">RG</label>
            </div>
        </div>

        <div class="actions">
            <button type="submit" class="primary button"><i class="fa fa-save fa-fw"></i>Salvar</button>
            <a href="./pacientes" class="button">Cancelar</a>
        </div>
    </form>
</t:mainlayout>
