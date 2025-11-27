import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/acessarBD")
public class AcessarBD extends HttpServlet { 
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Configurações do banco de dados
        String url = "jdbc:mysql://localhost:3306/javateste?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String user = "root";
        String password = ""; 

        resp.setContentType("text/plain; chaset=UTF-8");

        try (PrintWriter out = resp.getWriter()) {

            try (Connection conn = DriverManager.getConnection(url, user, password)) {
                if (conn != null && !conn.isClosed()){
                    out.println("Conexão OK");
                }else{
                    out.println("Erro: conexão nula ou fechada");
                }
            } catch (SQLException e) {
                out.println("Erro ao conectar: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}