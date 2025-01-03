package program;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.Database;

public class Main {

	public static void main(String[] args) {
		
		
		/*
		 * Instanciação JDBC
		 * 
		 * Connection: Abre e Fecha conexão com banco.
		 * Statement: Envia requisições ao banco.
		 * ResultSet: Traz os dados resultantes da consulta.
		 * 
		 * */
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		
		/*
		 * Bloco de tentativa de conexão com Banco.
		 * 
		 *  1. Instanciamos e fazemos conexão com Banco de Dados.
		 *  2. Instanciamos e criamos ponto de entrada para consultas.
		 *  3. Criamos nossa primeira consulta ao Banco, armazenando em resultSet.
		 * */
		try {
			
			// Criamos com sucesso conexão com Banco.
			connection = Database.getConnection();
			
			// Utilizamos dados da conexão armazenadas anteriormente para inicializarmos o Statement.
			statement = connection.createStatement();
			
			// Armazenamos dentro do objeto instanciado de ResultSet, a consulta feita pelo statement.
			resultSet = statement.executeQuery("SELECT * FROM department");
			
			/*
			 * Enquanto o método next() de resultSet for True, irá executar o primeiro bloco do laço, que por sua vez, irá imprimir em tela uma linha 
			 * da tabela. 
			 * 
			 * Obs: next() tem como comportamento padrão, retornar false quando não existir um próximo item dentro da respectiva tabela.
			 * */
			while(resultSet.next()) {
				
				System.out.println(
						resultSet.getInt("Id") + " - " + resultSet.getString("Name")
					);
				
			} 
		
		} catch (SQLException e) {
			
			e.printStackTrace();
	
		} finally { 
			/*
			 * Por que close Statement e ResultSet precisam de parâmetros, diferente de Connection? 
			 * 
			 * Isso se da por que, diferente de Statement e ResultSet, Connection é tratado dentro da Classe Database, então sua lógica que 
			 * se resume a inicializar conexão com banco, já está devidamente trabalhada por lá mesmo (no Database.java).
			 * 
			 * Enquanto que Statement e ResultSet, trabalham a partir de uma conexão bem sucedida de Connection.
			 * */
			Database.closeStatement(statement);
			Database.closeResultSet(resultSet);
			Database.closeConnection();
			
			
		}
	}

}
