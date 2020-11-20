package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @author Christoph Tornau, created in course "Java EE mit Servlets und JSPs unter Tomcat und Eclipse" on udemy.com
 * @author Changed by Ilia Chupakhin
 *
 */
public interface Command {
	
	public static final String JSPPATH = "/WEB-INF/jsp/";
	
	public String getTitel();
	
	public void executeGET (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	public void executePOST (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
