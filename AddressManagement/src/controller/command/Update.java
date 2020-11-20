package controller.command;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Address;
import persistence.AddressDAO;

public class Update implements Command {

	@Override
	public String getTitel() {
		return "Update an address";
	}

	@Override
	public void executeGET(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setAttribute("address", ((AddressDAO) request.getServletContext().getAttribute("addressDAO")).findById(request.getParameter("id")));
		} catch (SQLException e) {
			throw new ServletException();
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(JSPPATH + "update.jsp");
		requestDispatcher.include(request, response);
	}

	@Override
	public void executePOST(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Address addressToUpdate = new Address(request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("street"), request.getParameter("houseNumber"), request.getParameter("postcode"), request.getParameter("city"));
		addressToUpdate.setId(Integer.parseInt(request.getParameter("id")));
		
		try {
			((AddressDAO) request.getServletContext().getAttribute("addressDAO")).setAddress(addressToUpdate);
		} catch (SQLException e) {
			throw new ServletException(e);
		}
		
		response.sendRedirect("read.html");
		
	}

}
