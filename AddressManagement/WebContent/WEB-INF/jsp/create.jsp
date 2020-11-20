<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<form action="create.html" method="POST">
	<table>
		<tr>
			<td><label for="fn">First name</label></td>
			<td><input type="text" name="firstName" id="fn" value=""></td>
		</tr>
		<tr>
			<td><label for="ln">Last name</label></td>
			<td><input type="text" name="lastName" id="ln" value=""></td>
		</tr>
		<tr>
			<td><label for="st">Street</label></td>
			<td><input type="text" name="street" id="st" value=""></td>
		</tr>
		<tr>
			<td><label for="hn">House number</label></td>
			<td><input type="text" name="houseNumber" id="hn" value=""></td>
		</tr>
		<tr>
			<td><label for="pc">Postcode</label></td>
			<td><input type="text" name="postcode" id="pc" value=""></td>
		</tr>
		<tr>
			<td><label for="ci">City</label></td>
			<td><input type="text" name="city" id="ci" value=""></td>
		</tr>
		<tr>
			<td><input type="submit" value="Add"></td>
			<td></td>
		</tr>
	</table>
</form>