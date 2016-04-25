<%-- 
    Document   : errorSummary
    Created on : 21/04/2016, 19:50:13
    Author     : Jonathan Souza <jonathan.ralison@gmail.com>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag import="java.util.List"%>
<%@tag description="Resumo de Erros de Validação" pageEncoding="UTF-8"%>

<c:if test="${validations.size() > 0}">
    <div class="message message-error">
        Por favor, revise o formulário e corrija os seguintes erros:
        <ul>
            <c:forEach items="${validations}" var="message">
                <li>${message}</li>
            </c:forEach>
        </ul>
    </div>
</c:if>
