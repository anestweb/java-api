<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>AnestWeb</title>
</head>
<body>

<h1>Cadastro de Usuarios</h1>

<form action="CadastraUsuarioServlet" method="post">
	<p>
		<input type="text" name="nome" placeholder="Nome Completo" required autofocus>
	</p>
	<p>
		<input type="email" name="email" placeholder="Email" required>
	</p>
	<p>
		<input type="password" name="senha" placeholder="Senha" required>
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