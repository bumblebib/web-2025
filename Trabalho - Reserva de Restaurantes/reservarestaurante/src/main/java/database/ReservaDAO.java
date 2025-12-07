package database;

import model.Reserva;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class ReservaDAO {
    
    //precisa listar todas as mesas, 
    // inserir nova reserva, buscar uma reserva por id 
    // e remover uma reserva

    public List<Reserva> listarReservas() {
        List<Reserva> reservas = new ArrayList<>();

        String sql = "SELECT * FROM Reserva WHERE status = 'ATIVA'";

        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

                while(rs.next()) {
                    Reserva r = new Reserva(
                        rs.getInt("numeromesa"),
                        rs.getString("nomecliente"),
                        rs.getDate("data").toLocalDate()
                    );
                        r.setId(rs.getInt("id"));

                    reservas.add(r);
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }

        return reservas;
    }

    public void inserir(Reserva r) {

        String sql = "INSERT INTO Reserva(numeromesa, nomecliente, data) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

                //ao inserir, o BD vai gerar o id 
                ps.setInt(1, r.getNumeroMesa());
                ps.setString(2, r.getNomeCliente());
                ps.setDate(3, Date.valueOf(r.getData()));

                //execute update pra ações que modificam o BD 
                ps.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public Reserva buscarPorId(int id) {

        String sql = "SELECT * FROM Reserva WHERE id = ?";
        Reserva r = null; 

        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();

                if(rs.next()) {
                    r = new Reserva(
                        rs.getInt("numeromesa"),
                        rs.getString("nomecliente"),
                        rs.getDate("data").toLocalDate()
                    );
                    r.setId(rs.getInt("id"));
                }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return r;
    }

    public boolean remover(int id) {

        String sql = "UPDATE Reserva SET status = 'CANCELADA' WHERE id = ?";
        boolean removido = false;

        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, id);
                int linhasAfetadas = ps.executeUpdate();
                removido = linhasAfetadas > 0;

            } catch (SQLException e) {
                e.printStackTrace();
            }

        return removido;
    }

}

