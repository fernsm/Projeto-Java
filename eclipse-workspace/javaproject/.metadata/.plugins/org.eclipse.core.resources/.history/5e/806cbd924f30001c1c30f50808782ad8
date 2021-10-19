package br.com.infox.dal;

import java.sql.Connection;
import java.sql.DriverManager;

public class ModuloConexao {

//Metodo responsavel por estabelecer a conexao com o banco 
	
	
	public static Connection conector() {
		
		java.sql.Connection conexao = null;
		String driver = "com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/dbprojeto";
		String user ="root";
		String password ="f74067972";
		try {
			
			Class.forName(driver);
			conexao = DriverManager.getConnection(url, user, password);
			return conexao;
			
			
		} catch (Exception e) {
			
			System.out.println(e);
			return null;
			
		}
		
	}
	
	
	
	
}
