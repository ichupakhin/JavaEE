package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Address;

/**
 * @author Christoph Tornau, created in course "Java EE mit Servlets und JSPs unter Tomcat und Eclipse" on udemy.com
 * @author Changed by Ilia Chupakhin
 *
 */
public class AddressDAO {
	
	Connection connection;
	Statement statement;
	
	private String connectionURL = "jdbc:derby:c://temp//AdressManagement;create=true";
	
	public AddressDAO() {
		try {
			connection = DriverManager.getConnection(connectionURL);
			statement = connection.createStatement();
			
			if(!tableExists(/*connection,*/ "ADDRESSES")) {
				System.out.println("Create table, because it does not exist yet");
				
				statement.execute("CREATE TABLE ADDRESSES (ID INT NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT ADRESSEN_PK PRIMARY KEY, "
						+ "FIRSTNAME VARCHAR(32), " + "LASTNAME VARCHAR(32), " + "STREET VARCHAR(32), "
						+ "HOUSENUMBER VARCHAR(6), " + "POSTCODE VARCHAR(5), " + "CITY VARCHAR(32))" );
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Address> findAll() throws SQLException {
		
		ArrayList<Address> result = new ArrayList<Address>();
		ResultSet resultSet = statement.executeQuery("select ID, FIRSTNAME, LASTNAME, STREET, HOUSENUMBER, POSTCODE, CITY from ADDRESSES");
		
		while(resultSet.next()) {
			Address address = new Address();
			address.setId(resultSet.getInt(1));
			address.setFirstName(resultSet.getString(2));
			address.setLastName(resultSet.getString(3));
			address.setStreet(resultSet.getString(4));
			address.setHouseNumber(resultSet.getString(5));
			address.setPostcode(resultSet.getString(6));
			address.setCity(resultSet.getString(7));
			
			result.add(address);
		}
		
		
		resultSet.close();
		return result;
		
	}
	
	
	public Address findById(String parameter) throws SQLException {
		
		PreparedStatement preparedStatement = connection.prepareStatement("select ID, FIRSTNAME, LASTNAME, STREET, HOUSENUMBER, POSTCODE, CITY from ADDRESSES where id = ?");
		preparedStatement.setString(1, parameter);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		resultSet.next();
		
		Address address = new Address(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7));
		address.setId(resultSet.getInt(1));
		
		preparedStatement.close();
		
		return address;
		
	}
	
	
	public void addAddress(Address address) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("insert into ADDRESSES (FIRSTNAME, LASTNAME, STREET, HOUSENUMBER, POSTCODE, CITY) values (?, ?, ?, ?, ?, ?)");
		
		preparedStatement.setString(1, address.getFirstName());
		preparedStatement.setString(2, address.getLastName());
		preparedStatement.setString(3, address.getStreet());
		preparedStatement.setString(4, address.getHouseNumber());
		preparedStatement.setString(5, address.getPostcode());
		preparedStatement.setString(6, address.getCity());
		
		preparedStatement.executeUpdate();
		
		preparedStatement.close();
	}
	
	public void setAddress(Address address) throws SQLException {
			
		PreparedStatement preparedStatement = connection.prepareStatement(
				"update ADDRESSES set FIRSTNAME = ?, LASTNAME = ?, STREET = ?, HOUSENUMBER = ?, POSTCODE = ?, CITY = ? where ID = ?");
		
		preparedStatement.setString(1,address.getFirstName());
		preparedStatement.setString(2,address.getLastName());
		preparedStatement.setString(3,address.getStreet());
		preparedStatement.setString(4,address.getHouseNumber());
		preparedStatement.setString(5,address.getPostcode());
		preparedStatement.setString(6,address.getCity());
		preparedStatement.setInt(7, address.getId());
        
		preparedStatement.executeUpdate(); 

		preparedStatement.close();
		
	}

	public void deleteAddress(Address address) throws SQLException {
		
		PreparedStatement preparedStatement = connection.prepareStatement(
				"delete from ADDRESSES where ID = ?");
		
		preparedStatement.setInt(1, address.getId());
        
		preparedStatement.executeUpdate(); 

		preparedStatement.close();
	}
	
	private /*static*/ boolean tableExists(/*Connection connection,*/String tableName) throws SQLException {
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM "+ tableName +" WHERE 0 = 1 "); //see https://stackoverflow.com/questions/9140606/why-would-you-use-where-1-0-statement-in-sql
			resultSet = preparedStatement.getResultSet();	
			return true; //table exists because we have got 0 rows as result
		} catch (Exception e) {
			return false; //a problem has appeared because the table does not exist
		}
		finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (resultSet != null) {
				resultSet.close();
			}
		}
	}
	
	
	public void closeConnection() {
		
		try {
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	
}
