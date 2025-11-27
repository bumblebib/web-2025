package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = 
    "jdbc:mysql://localhost:3306/javateste?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // carregado uma unica fez quando a JVM executa a classe
    static {
        try {
            // Carrega o driver apenas uma vez quando a classe é carregada
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver MySQL carregado com sucesso!");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver MySQL não encontrado no classpath!", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
