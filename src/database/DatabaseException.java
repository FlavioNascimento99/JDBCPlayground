package database;


/* 
 * Classe criada herdando de RuntimeException
 * 
 * But, why?
 * 
 * 		Em casos como este, utilizamos de RuntimeException para receber-mos 
 * feedback dos mais variados erros (sejam de conexão ou execução de 
 * consultas) evitando assim uso de try/catch's para tratamento de exceções.
 * 
 */
public class DatabaseException extends RuntimeException {
	
	/*
	 * Prática padrão para serialização(?)
	 * */	
	private static final long serialVersionUID = 1L;
	
	
	/*
	 * Construtor da classe
	 * 
	 * 		Possui a chamada super(message), que tem como papel, chamar o construtor 
	 * da sua classe pai (RuntimeException) com a sua respectiva mensagem (nesse caso 
	 * de exceção)
	 * 
	 * */
	public DatabaseException(String message) {
		super(message);
	}
	
}
