<%-- 
    Document   : mainlayout
    Created on : 10/04/2016, 18:22:11
    Author     : Jonathan Souza <jonathan.ralison@gmail.com>
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/"%>
<%@tag description="Layout Principal" pageEncoding="UTF-8"%>

<c:set var="req" value="${pageContext.request}" />

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <base href="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}/">

        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>AnestWeb</title>

        <!-- Mobile Meta Tags -->
        <meta name="theme-color" content="#b71c1c">
        <meta name="msapplication-navbutton-color" content="#b71c1c">
        <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
        <meta name="mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-capable" content="yes">

        <link rel="icon" href="./assets/img/appicon.png" sizes="128x128">

        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" type="text/css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="./assets/3rd/flaticon-medical/flaticon.css" type="text/css">
        <link rel="stylesheet" href="./assets/3rd/pace-1.0.0/pace.flat-top.css" media="screen" type="text/css">
        <link rel="stylesheet" href="./assets/css/anestweb.css" media="screen,print" type="text/css">
    </head>
    <body>
        <div id="wrapper-all">
            <header>
                <a href="./" class="noprint"><img src="./assets/img/applogo.png" alt="AnestWeb" class="applogo"></a>
                <div class="print-only display-1">AnestWeb</div>
                <div class="options">
                    <c:if test="${conectado != null}">
                        <div class="userinfo item">
                            <i class="fa fa-user-md fa-fw fa-lg"></i> ${conectado.getNome().toUpperCase()}<br>
                            <small>CRM ${conectado.getCrm()}</small>
                        </div>
                        <div class="item">
                            <a href="./sair" class="flat inverse button"><i
                                    class="fa fa-power-off fa-fw"></i> Sair</a>
                        </div>
                    </c:if>
                </div>
            </header>

            <div id="wrapper-main">
                <c:if test="${conectado != null}">
                    <t:sidebar/>
                </c:if>

                <div class="page-content">
                    <jsp:doBody/>
                </div>
            </div>
        </div>

        <script defer src="./assets/3rd/pace-1.0.0/pace.min.js"></script>
        <script src="./assets/js/anestweb.js"></script>
    </body>
</html>
