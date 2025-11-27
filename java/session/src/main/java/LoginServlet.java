import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");

        if ("luciana".equals(usuario) && "123".equals(senha)) {
            HttpSession sessao = request.getSession(true);
            sessao.setAttribute("usuario", usuario);
            response.sendRedirect("sucesso.html?usuario=" + usuario + "&sessionId=" + sessao.getId());
        } else {
            response.sendRedirect("index.html?erro=true");
        }
    }
}
