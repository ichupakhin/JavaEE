package controller.command;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Address;
import persistence.AddressDAO;
/**
 * @author Christoph Tornau, created in course "Java EE mit Servlets und JSPs unter Tomcat und Eclipse" on udemy.com
 * @author Changed by Ilia Chupakhin
 *
 */
public class Delete implements Command {

	@Override
	public String getTitel() {
		return "Delete an address";
	}

	@Override
	public void executeGET(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Find the address in the data base and then set this address in jsp as attribute. 
		//In this case, it is possible to access the address as well as its attributes for example address.firstname within delete.jsp
		try {
			request.setAttribute("address", ((AddressDAO) request.getServletContext().getAttribute("addressDAO")).findById(request.getParameter	("id")));
		} catch (Exception e) {
			throw new ServletException(e);
		}
		
		RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher(JSPPATH + "delete.jsp");
		requestDispatcher.include(request, response);
	}
	

	@Override
	public void executePOST(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Create dummy address with the id of the address, that will be removed from the data base
		Address addressToDelete = new Address();
		addressToDelete.setId(Integer.parseInt(request.getParameter("id")));
		
		try {
			((AddressDAO) request.getServletContext().getAttribute("addressDAO")).deleteAddress(addressToDelete);
		} catch (SQLException e) {
			throw new ServletException(e);
		}
		
		response.sendRedirect("read.html");
		
	}
	
}
