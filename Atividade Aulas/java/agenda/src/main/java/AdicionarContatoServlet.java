import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AdicionarContatoServlet", urlPatterns = {"/AdicionarContatoServlet"})
public class AdicionarContatoServlet extends HttpServlet { 
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        PrintWriter out = response.getWriter();

        // pegando os parametros do request 
        String nome = request.getParameter("tnome");
        String endereco = request.getParameter("tend");
        String email = request.getParameter("temail");
        String telefone = request.getParameter("tphone");

        // monta o html apresenta os dados digitados 
        out.println("<html>");
        out.println("<body>");
        out.println("Contato: " + nome + " - " + email + " - " + telefone + " - " + endereco);
        out.println("</body>");
        out.println("</html>");
    }
}