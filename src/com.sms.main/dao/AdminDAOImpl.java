package dao;

import model.Admin;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl implements AdminDAO {

    @Override
    public void addAdmin(Admin admin) {
        String sql = "INSERT INTO admins(username,password,email) VALUES(?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, admin.getUsername());
            ps.setString(2, admin.getPassword());
            ps.setString(3, admin.getEmail());

            ps.executeUpdate();

        } catch (Exception e) {
            System.err.println("Error adding admin: " + e.getMessage());
        }
    }

    @Override
    public List<Admin> getAllAdmins() {
        List<Admin> list = new ArrayList<>();
        String sql = "SELECT * FROM admins";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Admin a = new Admin();
                a.setAdminId(rs.getInt("admin_id"));
                a.setUsername(rs.getString("username"));
                a.setPassword(rs.getString("password"));
                a.setEmail(rs.getString("email"));
                list.add(a);
            }

        } catch (Exception e) {
            System.err.println("Error fetching admins: " + e.getMessage());
        }
        return list;
    }

    @Override
    public Admin getAdminById(int id) {
        Admin a = null;
        String sql = "SELECT * FROM admins WHERE admin_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                a = new Admin();
                a.setAdminId(rs.getInt("admin_id"));
                a.setUsername(rs.getString("username"));
                a.setPassword(rs.getString("password"));
                a.setEmail(rs.getString("email"));
            }

        } catch (Exception e) {
            System.err.println("Error fetching admin: " + e.getMessage());
        }
        return a;
    }

    @Override
    public Admin getAdminByUsername(String username) {
        Admin a = null;
        String sql = "SELECT * FROM admins WHERE username=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                a = new Admin();
                a.setAdminId(rs.getInt("admin_id"));
                a.setUsername(rs.getString("username"));
                a.setPassword(rs.getString("password"));
                a.setEmail(rs.getString("email"));
            }

        } catch (Exception e) {
            System.err.println("Error fetching admin: " + e.getMessage());
        }
        return a;
    }

    @Override
    public void updateAdmin(Admin admin) {
        String sql = "UPDATE admins SET username=?, password=?, email=? WHERE admin_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, admin.getUsername());
            ps.setString(2, admin.getPassword());
            ps.setString(3, admin.getEmail());
            ps.setInt(4, admin.getAdminId());

            ps.executeUpdate();

        } catch (Exception e) {
            System.err.println("Error updating admin: " + e.getMessage());
        }
    }

    @Override
    public void deleteAdmin(int id) {
        String sql = "DELETE FROM admins WHERE admin_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.err.println("Error deleting admin: " + e.getMessage());
        }
    }
}
