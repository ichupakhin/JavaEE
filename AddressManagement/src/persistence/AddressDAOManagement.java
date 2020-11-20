package persistence;

import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AddressDAOManagement implements ServletContextListener {
	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent servletContextEvent)
    {
    	try {
			DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	servletContextEvent.getServletContext().setAttribute("addressDAO", new AddressDAO());
    	System.out.println ("init");
    }

	
	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent servletContextEvent)  { 
    	((AddressDAO) servletContextEvent.getServletContext().getAttribute("addressDAO")).closeConnection();
    	System.out.println ("destroy");
    }
}
