package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Reserva;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import model.ReservaService;
import database.ReservaDAO;

@WebServlet("/api/reservas")
public class ReservaServlet extends HttpServlet {

    private ReservaService reservaService;
    private Gson gson;

    @Override
    public void init() {
        ReservaDAO reservaDAO = new ReservaDAO();

        reservaService = new ReservaService(reservaDAO);

        gson = new GsonBuilder()
        .registerTypeAdapter(LocalDate.class, 
            (JsonSerializer<LocalDate>) (src, typeOfSrc, context) -> 
                new JsonPrimitive(src.toString())
        )
        .registerTypeAdapter(LocalDate.class, 
            (JsonDeserializer<LocalDate>) (json, typeOfT, context) -> 
                LocalDate.parse(json.getAsString())
        )
        .create();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        JsonObject body = gson.fromJson(req.getReader(), JsonObject.class);

        int numeroMesa = body.get("numeroMesa").getAsInt();
        String nomeCliente = body.get("nomeCliente").getAsString();
        LocalDate data = LocalDate.parse(body.get("data").getAsString());

        boolean sucesso = reservaService.realizarReserva(numeroMesa, nomeCliente, data);
        resp.setContentType("application/json; charset=UTF-8");

        JsonObject resposta = new JsonObject();
        resposta.addProperty("sucesso", sucesso);

        resp.getWriter().write(resposta.toString());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        List<Reserva> reservas = reservaService.listarReservas();

        String json = gson.toJson(reservas);
        resp.getWriter().write(json);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("application/json;charset=UTF-8");
        JsonObject resposta = new JsonObject();

        String idParam = req.getParameter("id");
        if (idParam == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resposta.addProperty("sucesso", false);
            resp.getWriter().write(resposta.toString());
            return;
        }

        int id = Integer.parseInt(idParam);
        boolean sucesso = reservaService.liberarReserva(id);

        resposta.addProperty("sucesso", sucesso);

        if (sucesso) {
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        resp.getWriter().write(resposta.toString());
    }

}
