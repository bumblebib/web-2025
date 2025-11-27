package database;

import model.Empregado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpregadoDao {

    private static final String INSERT_SQL =
        "INSERT INTO employee (first_name, last_name, username, password, email, phone, country) "
      + "VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String LIST_SQL =
        "SELECT id, first_name, last_name, username, password, email, phone, country "
      + "FROM employee ORDER BY id DESC";

    private static final String sql = "SELECT * FROM employee WHERE country = ?";

    public int registerEmployee(Empregado e) {
        int rows = 0;
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {

            ps.setString(1, e.getFirstName());
            ps.setString(2, e.getLastName());
            ps.setString(3, e.getUsername());
            ps.setString(4, e.getPass()); 
            ps.setString(5, e.getEmail());
            ps.setString(6, e.getPhone());
            ps.setString(7, e.getCountry());
          
            rows = ps.executeUpdate();
           //System.out.println("   executeUpdate() = " + rows);

        } catch (SQLException ex) {
           // System.err.println(" Erro ao inserir empregado: " + ex.getMessage());
            ex.printStackTrace();
        }
        return rows;
    }

    public List<Empregado> buscarPorPais(String country) {
        List<Empregado> lista = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, country);
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    Empregado e = new Empregado();
                    e.setId(rs.getInt("id"));
                    e.setFirstName(rs.getString("first_name"));
                    e.setLastName(rs.getString("last_name"));
                    e.setUsername(rs.getString("username"));
                    e.setPass(rs.getString("pass"));
                    e.setEmail(rs.getString("email"));
                    e.setPhone(rs.getString("phone"));
                    e.setCountry(rs.getString("country"));
                    lista.add(e);
                }
            }catch (SQLException ex) {
                System.err.println("Erro ao buscar empregados por país: " + ex.getMessage());
            }
            return lista;
    }

  /*
    public List<Empregado> listar() {
        List<Empregado> out = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(LIST_SQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Empregado e = new Empregado();
                e.setId(rs.getInt("id"));
                e.setFirstName(rs.getString("first_name"));
                e.setLastName(rs.getString("last_name"));
                e.setUsername(rs.getString("username"));
                e.setPass(rs.getString("password"));
                e.setEmail(rs.getString("email"));
                e.setPhone(rs.getString("phone"));
                e.setCountry(rs.getString("country"));
                out.add(e);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao listar empregados: " + ex.getMessage());
            ex.printStackTrace();
        }
        return out;
    }
*/

}
