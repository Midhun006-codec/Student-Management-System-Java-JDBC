package dao;

import model.Admin;
import java.util.List;

public interface AdminDAO {
    void addAdmin(Admin admin);
    List<Admin> getAllAdmins();
    Admin getAdminById(int id);
    Admin getAdminByUsername(String username);
    void updateAdmin(Admin admin);
    void deleteAdmin(int id);
}
