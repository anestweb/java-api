<%-- 
    Document   : errorSummary
    Created on : 21/04/2016, 19:50:13
    Author     : Jonathan Souza <jonathan.ralison@gmail.com>
--%>

<%@tag import="java.util.List"%>
<%@tag description="Resumo de Erros" pageEncoding="UTF-8"%>

<%
    if (request.getAttribute("validations") != null) {
        List<String> validations = (List<String>) request.getAttribute("validations");
        %>
        <div class="message message-error errorSummary">
            <ul>
                <% for (String msg : validations) { %>
                    <li><%= msg%></li>
                <% } %>
            </ul>
        </div>
        <%
    }
%>