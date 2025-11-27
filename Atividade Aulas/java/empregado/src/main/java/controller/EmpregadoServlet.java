package controller;

import com.google.gson.Gson;
import database.EmpregadoDao;
import model.Empregado;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import java.io.*;
import java.util.List;

@WebServlet("/api/empregados")
public class EmpregadoServlet extends HttpServlet {
    private final EmpregadoDao dao = new EmpregadoDao();
    private final Gson gson = new Gson();

   
    /*@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=UTF-8");
        List<Empregado> lista = dao.listar();
        resp.getWriter().write(gson.toJson(lista));
    } 
        */

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        Empregado e = gson.fromJson(req.getReader(), Empregado.class);
        int r = dao.registerEmployee(e);
        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().write("{\"sucesso\": " + (r > 0) + "}");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String country = request.getParameter("country");

        try (PrintWriter out = response.getWriter()) {
            if(country != null && !country.isEmpty()) {
                // nova funcionalidade: busca por país 
                List<Empregado> lista = dao.buscarPorPais(country);
                new Gson().toJson(lista, out);
            }
        } catch (Exception e) { 
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao processar requisição" + e.getMessage());
        }    
    }
}
