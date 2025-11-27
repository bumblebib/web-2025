package database;

import model.Empregado;
import java.sql.*;


public class EmpregadoDao {
    private static final String INSERT_SQL = 
    "INSERT INTO employee (first_name, last_name, username, pass, email, phone, country)" + "VALUES (?, ?, ?, ?, ?, ?, ?)";

    public int registerEmployee(Empregado e){
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
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rows;
    }
}