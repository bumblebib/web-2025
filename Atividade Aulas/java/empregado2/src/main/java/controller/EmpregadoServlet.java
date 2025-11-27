package controller;

import com.google.gson.Gson;
import database.EmpregadoDao;
import model.Empregado;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;

@WebServlet("/api/empregados")
public class EmpregadoServlet extends HttpServlet{
    private final EmpregadoDao dao = new EmpregadoDao();
    private final Gson gson = new Gson();

    @Override 
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Empregado e = gson.fromJson(req.getReader(), Empregado.class);
        int r = dao.registerEmployee(e);
        resp.setContentType("application/json; charset=UTF-8");
        resp.getWriter().write("{\"sucesso\": " + (r > 0) + "}");
    }
    
}
