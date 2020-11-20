package controller;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.command.Command;
import controller.command.Create;
import controller.command.Delete;
import controller.command.Read;
import controller.command.Update;



/**
 * Servlet implementation class FrontControllerServlet
 */
//@WebServlet("/FrontControllerServlet")
/**
 * @author Christoph Tornau, created in course "Java EE mit Servlets und JSPs unter Tomcat und Eclipse" on udemy.com
 * @author Changed by Ilia Chupakhin
 *
 */
public class FrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConcurrentHashMap<String, Command> registry;
        
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontControllerServlet() {
        super();
        registry = new ConcurrentHashMap<String, Command>();
        
        registry.put("create.html", new Create());
		registry.put("read.html", new Read());
		registry.put("update.html", new Update());
		registry.put("delete.html", new Delete());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Command command = determineCommand(request);
		request.setAttribute("titel", "Address Management - " + command.getTitel());
		
		RequestDispatcher requestDispatcherTop = request.getRequestDispatcher(Command.JSPPATH + "top.jsp");
		requestDispatcherTop.include(request, response);
		
		command.executeGET(request, response);
		
		RequestDispatcher requestDispatcherBottom = request.getRequestDispatcher(Command.JSPPATH + "bottom.jsp");
		requestDispatcherBottom.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Command command = determineCommand(request);
		command.executePOST(request, response);
	}
	
	private Command determineCommand(HttpServletRequest request) {
		String[] segmentsURI = request.getRequestURI().split("/");
		String lastSegmentURI = segmentsURI[segmentsURI.length - 1];
		
		Command command = registry.get(lastSegmentURI);
		
		if (command == null)  {
			System.out.println(lastSegmentURI + "does not exist. Return to read.html");
			command = registry.get("read.html");
		}
		
		return command;
	}

}
