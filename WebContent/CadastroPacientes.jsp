<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%> 
<%@ page import="java.util.List"%>
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

<h1>Cadastro de Pacientes</h1>

<%
	Pacientes pacientes = (Pacientes) session.getAttribute("Pacientes");
%>

<form action="CadastraPacientesServlet" method="post">
<%
	if(pacientes != null){
%>
	<P>
		<input type="hidden" name="id" value="${Pacientes.getId()}">
	</P>
<%
	}
%>
	<p>
		<input type="text" name="nome" placeholder="Nome completo" value="${Pacientes.getNomeCompleto()}" required autofocus>
	</p>
	<p>
		<input type="text" name="dataNasc" placeholder="Data de nascimento" value="${Pacientes.getDataNascimento()}" pattern="^(?:0[1-9]|[12][0-9]|3[01])(.)(?:0[1-9]|1[0-2])\1(?:19|20)[0-9]{2}$" required>
	</p>
	<p>
		<input type="text" name="cpf" placeholder="CPF" value="${Pacientes.getCpf()}" pattern="^(?:[0-9]{3}\.){2}[0-9]{3}\-[0-9]{2}$" required>
	</p>
	<p>
		<input type="text" name="rg" placeholder="RG" value="${Pacientes.getRg()}" pattern="^\d{1,3}(?:\.\d{3})*$" required>
	</p>
	<p>
		<input type="text" name="sexo" placeholder="Sexo" value="${Pacientes.getSexo()}" required>
	</p>
	<p>
		<label>Profissional</label>
		<select type="text" name="profissional" required>
	<%
		String urlRp = "jdbc:mysql://localhost:3306/AnestWeb";
		Class.forName("com.mysql.jdbc.Driver");
		Connection connectionRp = DriverManager.getConnection(urlRp, "root", "root");	
		
			Statement statementRp = connectionRp.createStatement();
			
			String sqlRp = "SELECT profissional.id, profissional.nome FROM profissional";
			
			ResultSet resultSetRp = statementRp.executeQuery(sqlRp);
		while(resultSetRp.next()){
	%>
			<option value=<%= resultSetRp.getInt(1) %> ><%= resultSetRp.getString(2) %></option>
	<% }	%>
		</select>
	</p>
	<p>
		<label>Local</label>
		<select type="text" name="local" required>
	<%
		String urlRl = "jdbc:mysql://localhost:3306/AnestWeb";
		Class.forName("com.mysql.jdbc.Driver");
		Connection connectionRl = DriverManager.getConnection(urlRl, "root", "root");	
		
			Statement statementRl = connectionRl.createStatement();
			
			String sqlRl = "SELECT local.id, local.nome_curto FROM local";
			
			ResultSet resultSetRl = statementRl.executeQuery(sqlRl);
		while(resultSetRl.next()){
	%>
			<option value=<%= resultSetRl.getInt(1) %> ><%= resultSetRl.getString(2) %></option>
	<% }	%>
		</select>
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