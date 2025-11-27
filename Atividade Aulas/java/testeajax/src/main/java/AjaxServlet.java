import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/mensagem")
public class AjaxServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String nome = req.getParameter("nome");
        resp.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        if (nome == null || nome.isEmpty()) {
            out.print("Olá visitante! Informe seu nome.");
        } else {
            out.print("Olá " + nome + "! Resposta do Servlet sem recarregar a página.");
        }
    }
}
