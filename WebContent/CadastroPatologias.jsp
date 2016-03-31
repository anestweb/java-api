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

<h1>Cadastro de Patologias</h1>

<%
	Patologias patologias = (Patologias) session.getAttribute("Patologias");
%>

<form action="CadastraPatologiasServlet" method="post">

<%
	if(patologias != null){
%>
	<P>
		<input type="hidden" name="id" value="${Patologias.getId()}">
	</P>
<%
	}	
%>
	<p>
		<input type="text" name="descricao" placeholder="Descrição" value="${Patologias.getDescricao()}" required autofocus>
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