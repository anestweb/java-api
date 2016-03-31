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

<h1>Lista de Pacientes</h1>

<%
	DaoPacientes daoPacientes = new DaoPacientesImpl();

	String urlP = "jdbc:mysql://localhost:3306/AnestWeb";
	Class.forName("com.mysql.jdbc.Driver");
	Connection connectionP = DriverManager.getConnection(urlP, "root", "root");	
	
		Statement statementP = connectionP.createStatement();
		
		String sqlP = "SELECT pacientes.nome_completo, pacientes.cpf, pacientes.rg, " +
		"pacientes.sexo, pacientes.data_nascimento, pacientes.id, local.nome_curto, profissional.nome FROM pacientes " +
		"INNER JOIN local " +
		"ON local.id = pacientes.id_local " +
		"INNER JOIN profissional " +
		"ON profissional.id = pacientes.id_profissional";
		
		ResultSet resultSetP = statementP.executeQuery(sqlP);
%>
<div>
<table>
	<tr>
		<td><b>Nome</td>
		<td><b>CPF</td>
		<td><b>RG</td>
		<td><b>Sexo</td>
		<td><b>Data de Nascimento</td>
		<td><b>Local</td>
		<td><b>Profissional</td>
		<td><b>Operação</td>
	</tr>
<%
		while(resultSetP.next()){
%>
	<tr>
		<td><%= resultSetP.getString(1) %></td>
		<td><%= resultSetP.getString(2) %></td>
		<td><%= resultSetP.getString(3) %></td>
		<td><%= resultSetP.getString(4) %></td>
		<td><%= resultSetP.getString(5) %></td>
		<td><%= resultSetP.getString(7) %></td>
		<td><%= resultSetP.getString(8) %></td>	
		<td><a href="RemovePacienteServlet?id=<%= resultSetP.getString(6) %>">X</a>
		<a href="AtualizaPacienteServlet?id=<%= resultSetP.getString(6) %>">E</a></td>
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