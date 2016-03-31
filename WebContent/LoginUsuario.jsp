<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AnestWeb</title>
</head>
<body>

<h1>Login do Sistema</h1>

<form action="EfetuaLoginServlet" method="post">
	<p>
		<input type="text" name="loginEmail" placeholder="Email" required>
	</p>
	<p>
		<input type="password" name="loginSenha" placeholder="Senha" required>
	<p>
		<input type="submit" value="Entrar"> 
	<p>
</form>
</body>
</html>