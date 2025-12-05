package service;

import java.util.List;

import model.Mesa;
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

    public int mesasDisponiveis() {
        int count = 0;
        for (Mesa mesa : mesaDAO.listar()) {
            if (mesa.isDisponivel()) {
                count++;
            }
        }
        return count;
    }

    public List<Mesa> listarDisponiveis() {
        return mesaDAO.listarDisponiveis();
    }

    public int mesasPorCapacidade(int capacidade) {
        int count = 0;
        for (Mesa mesa : mesaDAO.listar()) {
            if (mesa.isDisponivel() && mesa.getCapacidade() == capacidade) {
                count++;
            }
        }
        return count;
    }

    public int mesasNaoDisponiveis() {
        int count = 0;
        for (Mesa mesa : mesaDAO.listar()) {
            if (!mesa.isDisponivel()) {
                count++;
            }
        }
        return count;
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
