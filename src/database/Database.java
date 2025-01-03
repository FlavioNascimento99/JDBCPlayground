package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Database {
	
	
	// Inicializamos Classe Connection como null;
	private static Connection connection = null;
	
	// Método utilizado para forçar a conexão com banco.
	public static Connection getConnection() {
		
		// Com nosso objeto sendo inicializado propositalmente como null, sempre iremos cair dentro desta condicional.
		if (connection == null) {
			
			/*
			 * Properties é uma biblioteca utilizada para fazer armazenamento e leitura de documentos a partir de dados chave-valor.
			 * 1. Instanciamos a classe com método de leitura de dados.
			 * 2. Utilizamos a instanciação para capturar uma propriedade em específica, nesse caso "dburl", responsável por conter o endereço do Banco.
			 * 3. Após isso, iremos inicializar a conexão com a API do JDBC, DriverManager, responsável por criar a ponte de conexão entre o sistema
			 * e o Banco de Dados, é ele que será no fim, armazenado dentro de connection.
			 * */
			try {
				
				Properties properties = loadProperties();
				String URL = properties.getProperty("dburl");
				
				connection = DriverManager.getConnection(URL, properties);		
				
			} catch (SQLException e) {
				
				throw new DatabaseException(e.getMessage());
				
			}
		} 
		
		return connection;
	}
	
	
	/*
	 * Aqui temos três métodos escritos com o mesmo objetivo; Fechar operações do sistema com o Banco de Dados
	 * para liberar recursos da máquina.
	 * */
	
	
	public static void closeConnection() {
		
		if (connection != null) {
			
			try {
				
				connection.close();	
				
			}
			
			catch(SQLException e) {
				
				throw new DatabaseException(e.getMessage());
				
			}
			
		}
		
	}
	
	public static void closeStatement(Statement statement) {
		
		if (statement != null) {
			
			try { 
				
				statement.close();
				
			} catch (SQLException e) {
				
				throw new DatabaseException(e.getMessage());
				
			}
			
		}
		
	}
	
	public static void closeResultSet(ResultSet resultSet) {
		
		if (resultSet != null) {
			
			try { 
				
				resultSet.close();
				
			} catch (SQLException e) {
				
				throw new DatabaseException(e.getMessage());
				
			}
			
		}
		
	}
	
	
	/*
	 * Método utilizado em conjunto da biblioteca do Java Util.
	 * 
	 * 1. Tem como função, fazer leitura do arquivo de propriedades do banco de dados (db.properties) usando do FileInputStream
	 * que por sua vez é amplamente utilizado para leitura de arquivos geralmente de texto.
	 * 2. Instanciamos Properties em seguida utilizando-o para carregar (ou armazenar em memória) os pares chave-valor 
	 * contidos no arquivo em questão.
	 * 3. Por fim, teremos o retorno dos dados contidos no arquivo em formato de chave-valor armazenados em memória.
	 * 
	 * Detalhe: Catch tem como objetivo trazer um feedback visual relacionado ao erro que possa ocorrer caso o try não rode como esperado.
	 * */
	private static Properties loadProperties() {
		
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			
			Properties properties = new Properties();
			properties.load(fs);
			
			return properties;
			
		} catch (IOException e) {
			
			throw new DatabaseException(e.getMessage());
			
		}
	}
}
