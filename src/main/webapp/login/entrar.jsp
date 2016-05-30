<%-- 
    Document   : entrar
    Created on : 29/05/2016, 19:36:08
    Author     : Jonathan Souza <jonathan.ralison@gmail.com>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="br.projetointegrador.anestwebm.util.StringUtil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<t:mainlayout>

    <div style="max-width: 420px; margin: 0 auto">
        <h1 class="pageheading">Identifique-se</h1>

        <c:if test="${error != null}">
            <p class="message message-error">${error}</p>
        </c:if>

        <form action="${actionUrl}" method="post"
              enctype="application/x-www-form-urlencoded"
              accept-charset="utf-8">
            <div class="form" style="width: 80%; margin: 0 auto">
                <div class="flex-row">
                    <div class="field grid-16">
                        <label for="y1">Informe seu CRM</label>
                        <input type="text" id="y1" name="crm" value="${crm}"
                               data-mask="crm" pattern="^[0-9]{4,6}\/[A-Z]{2}$"
                               placeholder="0000/PB" required>
                    </div>
                </div>
                <div class="flex-row">
                    <div class="field grid-16">
                        <label for="y2">Informe sua senha</label>
                        <input type="password" id="y2" name="senha" value=""
                               placeholder="Senha" required>
                    </div>
                </div>

                <div class="actions">
                    <button type="submit" class="primary button">
                        <i class="fa fa-sign-in"></i>
                        Entrar
                    </button>
                </div>
            </div>
        </form>
    </div>

</t:mainlayout>