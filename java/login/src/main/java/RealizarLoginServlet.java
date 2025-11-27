import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/mensagem")
public class RealizarLoginServlet extends HttpServlet { 
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 

        PrintWriter out = response.getWriter();

        // pegando os parametros do request 
        String nome = request.getParameter("username");
        String senha = request.getParameter("password");

        // monta o html e apresenta os dados digitados 
        if (nome != null && nome.equals(senha)) {
            out.print("Welcome " + nome + "!");
        } else {
            out.print("Usuário ou senha incorretos!");
        }
    }
}
