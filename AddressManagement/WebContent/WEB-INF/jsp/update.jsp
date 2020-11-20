<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<form action="update.html" method="POST">
	<table>
		<tr>
			<td><label for="fn">First name</label></td>
			<td><input type="text" name="firstName" id="fn" value="${address.firstName}"></td>
		</tr>
		<tr>
			<td><label for="ln">Last name</label></td>
			<td><input type="text" name="lastName" id="ln" value="${address.lastName}"></td>
		</tr>
		<tr>
			<td><label for="st">Street</label></td>
			<td><input type="text" name="street" id="st" value="${address.street}"></td>
		</tr>
		<tr>
			<td><label for="hn">House number</label></td>
			<td><input type="text" name="houseNumber" id="hn" value="${address.houseNumber}"></td>
		</tr>
		<tr>
			<td><label for="pc">Postcode</label></td>
			<td><input type="text" name="postcode" id="pc" value="${address.postcode}"></td>
		</tr>
		<tr>
			<td><label for="ci">City</label></td>
			<td><input type="text" name="city" id="ci" value="${address.city}"></td>
		</tr>
		<tr>
			<td>
			    <input type="hidden" name="id" value="${address.id}">
				<input type="submit" value="Update">
			</td>
			<td></td>
		</tr>
	</table>
</form>