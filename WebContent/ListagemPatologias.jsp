<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.sql.*" %>
<%@ page import="br.projetointegrador.DAO.*" %>
<%@ page import="br.projetointegrador.model.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AnestWeb</title>
</head>
<body>

<h1>Lista de Patologias</h1>

<%
	DaoPatologias daoPatologias = new DaoPatologiasImpl();

	String urlP = "jdbc:mysql://localhost:3306/AnestWeb";
	Class.forName("com.mysql.jdbc.Driver");
	Connection connectionP = DriverManager.getConnection(urlP, "root", "root");	
	
		Statement statementP = connectionP.createStatement();
		
		String sqlP = "SELECT patologia.descricao, patologia.id FROM patologia";
		
		ResultSet resultSetP = statementP.executeQuery(sqlP);
%>

<div>
<table>
	<tr>
		<td><b>Descrição</td>
		<td><b>Operação</td>
	</tr>
<%
		while(resultSetP.next()){
%>
	<tr>
		<td><%= resultSetP.getString(1) %></td>	
		<td><a href="RemovePatologiaServlet?id=<%= resultSetP.getString(2) %>">X</a>
		<a href="AtualizaPatologiaServlet?id=<%= resultSetP.getString(2) %>">E</a></td>
	</tr>
	<%
		}
	%>
</table>
</div>

<p>	
	<form action="HomeSistema.jsp">
		<input type="submit" value="Voltar">
	</form>
</p>

</body>
</html>