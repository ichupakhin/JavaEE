<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

Do you want to delete the address of ${address.firstName} ${address.lastName} ?

<form action="delete.html" method="POST">
    <input type="hidden" name="id" value="${address.id}">
	<input type="submit" value="Delete">
</form>