<%-- 
    Document   : sidebar
    Created on : 21/04/2016, 03:19:48
    Author     : Jonathan Souza <jonathan.ralison@gmail.com>
--%>

<%@tag description="Navegação Lateral" pageEncoding="UTF-8"%>

<%
    final String contextUrl = request.getContextPath();
    final String requestUrl = request.getRequestURI();
    final String[][] items = {
        {"Pacientes", "./pacientes", "flaticon-unhealthy-medical-condition"},
        {"Patologias", "./patologias", "flaticon-three-hexagons-cell-symbol"},
        {"Medicamentos", "./medicamentos", "flaticon-drugs-capsules-and-pills"},
        {"Profissionais", "./profissionais", "flaticon-medical-doctor-specialist"}
    };
%>

<aside class="sidebar">
    <nav class="sidebar-menu">
        <% for (int i = 0; i < items.length; i++) { %>
            <%
                String clsAtivo = "";
                if (items[i][1].charAt(0) == '.') {
                    String linkCompare = contextUrl + items[i][1].substring(1);
                    clsAtivo = requestUrl.startsWith(linkCompare) ? "active" : "";
                }
            %>
            <a href="<%= items[i][1] %>" class="<%= clsAtivo %>">
                <i class="<%= items[i][2] %>"></i>
                <span><%= items[i][0] %></span>
            </a>
        <% } %>
    </nav>
</aside>
