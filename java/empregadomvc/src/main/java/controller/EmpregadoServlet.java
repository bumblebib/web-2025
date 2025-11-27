package controller;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.List;
import model.Empregado;
import model.EmpregadoService;

@WebServlet("/EmpregadoServlet")
public class EmpregadoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final EmpregadoService service = new EmpregadoService();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=UTF-8");
        List<Empregado> lista = service.listar();
        resp.getWriter().write(gson.toJson(lista));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        Empregado novo = gson.fromJson(reader, Empregado.class);
        service.adicionar(novo);
        resp.setContentType("text/plain; charset=UTF-8");
        resp.getWriter().write("Empregado cadastrado com sucesso!");
    }
 }