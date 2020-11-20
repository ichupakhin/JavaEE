package controller.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Address;
import persistence.AddressDAO;

public class Read implements Command {

	@Override
	public String getTitel() {
		return "All addresses";
	}

	@Override
	public void executeGET(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			ArrayList<Address> addresses = ((AddressDAO) request.getServletContext().getAttribute("addressDAO")).findAll();
			request.setAttribute("addresses", addresses);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(JSPPATH + "read.jsp");
			requestDispatcher.include(request, response);
			
		} catch (SQLException e) {
			throw new ServletException();
		}
		
	}

	@Override
	public void executePOST(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Implementaition is not needed
	}

}
