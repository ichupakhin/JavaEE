package controller.command;

import java.io.IOException;

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
public class Create implements Command {

	@Override
	public String getTitel() {
		return "Create an address";
	}
	
	
	@Override
	public void executeGET(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RequestDispatcher requestDispatcher = request.getServletContext().getRequestDispatcher(JSPPATH + "create.jsp");
		requestDispatcher.include(request, response);
		
	}

	@Override
	public void executePOST(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Address address = new Address(request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("street"), request.getParameter("houseNumber"), request.getParameter("postcode"), request.getParameter("city"));
		
		try {
			((AddressDAO) request.getServletContext().getAttribute("addressDAO")).addAddress(address);
		} catch (Exception e) {
			throw new ServletException(e);
		}
		
		response.sendRedirect("read.html");
	}
	
}
