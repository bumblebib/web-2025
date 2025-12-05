package controller;

import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Mesa;

import java.io.IOException;
import java.util.List;
import service.MesaService;

@WebServlet("/api/mesas")
public class MesaServlet extends HttpServlet {

    private MesaService mesaService;
    private final Gson gson = new Gson();

    @Override
    public void init() {
        mesaService = new MesaService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String dispo = req.getParameter("disponivel");
        List<Mesa> mesas;

        if ("true".equalsIgnoreCase(dispo)) {
            mesas = mesaService.listarDisponiveis();
        } else {
            mesas = mesaService.listar();
        }

        String json = gson.toJson(mesas);
        resp.getWriter().write(json);
    }

} 


