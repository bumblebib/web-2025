package model;

import java.util.List;

import database.MesaDAO;

public class MesaService {

    private final MesaDAO mesaDAO;

    public MesaService() {
        this.mesaDAO = new MesaDAO();
    }

    public List<Mesa> listar() {
        return mesaDAO.listar();
    }

    public Mesa buscarMesa(int numero) {
        for (Mesa mesa : mesaDAO.listar()) {
            if (mesa.getNumero() == numero) {
                return mesa;
            }
        }
        return null;
    }

    public List<Mesa> listarDisponiveis() {
        return mesaDAO.listarDisponiveis();
    }

    public boolean atualizarDisponibilidade(boolean disponivel, Mesa mesa) {
        mesa.setDisponivel(disponivel);
        if (disponivel) {
            return mesaDAO.setDisponivel(mesa.getNumero());
        } else {
            return mesaDAO.setIndisponivel(mesa.getNumero());
        }
    }
}
