import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;


@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Cookie c = new Cookie("usuario", "");
        c.setMaxAge(0); // apaga cookie
        resp.addCookie(c);

        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().print("{\"logout\": true}");
    }
}
