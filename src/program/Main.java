package program;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import database.Database;

public class Main {

	public static void main(String[] args) {
		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			
			
			
			connection = Database.getConnection();
			
			
			preparedStatement = connection.prepareStatement(
					"INSERT INTO seller " 
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) " 
					+ "VALUES "
					+ "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			
			
			
			
			preparedStatement.setString(1, "Conor McGregor");
			preparedStatement.setString(2, "notorious@gmail.com");
			preparedStatement.setDate(3, new java.sql.Date(dateFormat.parse("02/12/1987").getTime()));
			preparedStatement.setDouble(4, 7000.0);
			preparedStatement.setInt(5, 2);
			
			
			
			int HowManyRowsWasAffected = preparedStatement.executeUpdate();
			
			if (HowManyRowsWasAffected > 0) {

				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				
				while(resultSet.next()) {
					int Id = resultSet.getInt(1);
					System.out.println("Feito. Id = " + Id);
				}
			} else {
				
				System.out.println("No rows was affected.");
				
			}
			
			
			System.out.println("Done! - " + HowManyRowsWasAffected + " row(s) was affected into your database.");
			
		} catch(SQLException e) {
						
			e.printStackTrace();
			
		} catch (ParseException e) {
			
			e.printStackTrace();
		
		} finally {
			
			Database.closeStatement(preparedStatement);
			Database.closeConnection();
			
		}
		
	}

}
