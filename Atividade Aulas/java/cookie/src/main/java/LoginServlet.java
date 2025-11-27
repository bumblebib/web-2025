import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;



@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String usuario = req.getParameter("usuario");
        String senha = req.getParameter("senha");

        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        if ("luciana".equals(usuario) && "123".equals(senha)) {
            Cookie c = new Cookie("usuario", usuario);
            c.setMaxAge(60 * 10); // 10 minutos
            resp.addCookie(c);
            out.print("{\"sucesso\": true, \"usuario\": \"" + usuario + "\"}");
        } else {
            out.print("{\"sucesso\": false}");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String action = req.getParameter("action");
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        if ("status".equals(action)) {
            String usuario = null;
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if (c.getName().equals("usuario")) {
                        usuario = c.getValue();
                    }
                }
            }
            if (usuario != null)
                out.print("{\"logado\": true, \"usuario\": \"" + usuario + "\"}");
            else
                out.print("{\"logado\": false}");
        }
    }
}
