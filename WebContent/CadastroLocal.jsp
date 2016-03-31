<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.sql.*" %>
<%@ page import="br.projetointegrador.model.*" %>
<%@ page import="br.projetointegrador.DAO.*" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AnestWeb</title>
</head>
<body>

<h1>Cadastro de Locais</h1>

<%
	Local local = (Local) session.getAttribute("Local");
%>

<form action="CadastraLocalServlet" method="post">
<%
	if(local != null){
%>
	<P>
		<input type="hidden" name="id" value="${Local.getId()}">
	</P>
<%
	}
%>
	<p>
		<input type="text" name="nomeCurto" placeholder="Nome curto" value="${Local.getNomeCurto()}" required autofocus>
	</p>
	<p>
		<input type="text" name="nomeLongo" placeholder="Nome longo" value="${Local.getNomeLongo()}" required>
	</p>
	<p>
		<input type="text" name="cnpj" placeholder="CNPJ" value="${Local.getCnpj()}" required>
	</p>
	<p>
		<input type="submit" value="Enviar">
	</p> 
</form>

<form action="HomeSistema.jsp">
		<input type="submit" value="Voltar">
</form>
</body>
</html>