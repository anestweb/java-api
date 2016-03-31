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

	String urlL = "jdbc:mysql://localhost:3306/AnestWeb";
	Class.forName("com.mysql.jdbc.Driver");
	Connection connectionL = DriverManager.getConnection(urlL, "root", "root");	
	
		Statement statementL = connectionL.createStatement();
		
		String sqlL = "SELECT local.nome_curto, local.nome_longo, local.cnpj, local.id FROM local";
		
		ResultSet resultSetL = statementL.executeQuery(sqlL);
%>

<div>
<table>
	<tr>
		<td><b>Nome curto</td>
		<td><b>Nome longo</td>
		<td><b>CNPJ</td>
		<td><b>Operação</td>
	</tr>
<%
		while(resultSetL.next()){
%>
	<tr>
		<td><%= resultSetL.getString(1) %></td>	
		<td><%= resultSetL.getString(2) %></td>	
		<td><%= resultSetL.getString(3) %></td>	
		<td><a href="RemoveLocalServlet?id=<%= resultSetL.getString(4) %>">X</a>
		<a href="AtualizaLocalServlet?id=<%= resultSetL.getString(4) %>">E</a></td>
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