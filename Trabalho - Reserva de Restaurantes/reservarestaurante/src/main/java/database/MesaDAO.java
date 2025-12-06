package database;

import model.Mesa;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class MesaDAO {

    //precisa de um método pra listar as mesas
    public List<Mesa> listar() {
        List<Mesa> mesas = new ArrayList<>();

        //pegar no BD
        String sql = "SELECT * FROM Mesa";

        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

                while(rs.next()) {
                    Mesa m = new Mesa(
                        rs.getInt("numero"),
                        rs.getInt("capacidade"),
                        rs.getBoolean("disponivel")
                    );
                    mesas.add(m);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        return mesas; 
    }

    public List<Mesa> listarDisponiveis() {
        List<Mesa> mesas = new ArrayList<>();

        //pegar no BD
        String sql = "SELECT * FROM Mesa WHERE disponivel = TRUE";

        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

                while(rs.next()) {
                    Mesa m = new Mesa(
                        rs.getInt("numero"),
                        rs.getInt("capacidade"),
                        rs.getBoolean("disponivel")
                    );
                    mesas.add(m);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        return mesas; 
    }

    public boolean setDisponivel(int numMesa) {

        String sql = "UPDATE Mesa SET disponivel = TRUE WHERE numero = ?";

        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, numMesa);
                ps.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }

            return true;
    }

    public boolean setIndisponivel(int numMesa) {

        String sql = "UPDATE Mesa SET disponivel = FALSE WHERE numero = ?";

        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setInt(1, numMesa);
                ps.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }

            return true;
    }
    
}

