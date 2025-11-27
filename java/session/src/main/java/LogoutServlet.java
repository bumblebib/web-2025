import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Invalida a sessão no servidor
        HttpSession sessao = request.getSession(false);
        if (sessao != null) {
            sessao.invalidate();
        }

        // Remove o cookie JSESSIONID no cliente
        Cookie cookie = new Cookie("JSESSIONID", "");
        cookie.setMaxAge(0); // expira imediatamente
        cookie.setPath(request.getContextPath()); // importante!
        response.addCookie(cookie);

        // Redireciona para o login
        response.sendRedirect("index.html?logout=true");
    }
}
