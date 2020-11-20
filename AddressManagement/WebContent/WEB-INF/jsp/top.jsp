<!-- author Christoph Tornau, created in course "Java EE mit Servlets und JSPs unter Tomcat und Eclipse" on udemy.com, changed by Ilia Chupakhin-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="now" class="java.util.Date" />
<c:set var="starttime" scope="request" value="${now}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${title}</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
<header><h1>${title}</h1></header>
<article>