<!-- author Christoph Tornau, created in course "Java EE mit Servlets und JSPs unter Tomcat und Eclipse" on udemy.com, changed by Ilia Chupakhin-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table>
<tr>
<th>First name</th><th>Last name</th><th>Street</th><th>House number</th><th>Postcode</th><th>City</th><th><strong>Actions</strong></th>
</tr>

<c:forEach items="${addresses}" var="address"> 
  <tr>
    <td>${address.firstName}</td>
    <td>${address.lastName}</td>
    <td>${address.street}</td>
    <td>${address.houseNumber}</td>
    <td>${address.postcode}</td>
    <td>${address.city}</td>
    <td><a href="update.html?id=${address.id}">Edit</a>, <a class="danger" href="delete.html?id=${address.id}">Delete</a></td>
  </tr>
</c:forEach>

</table>
<p>
<a href="create.html">Create new address</a>
</p>