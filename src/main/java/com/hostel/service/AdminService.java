package com.hostel.service;
import com.hostel.dao.AdminDAO;
import com.hostel.model.Admin;

public class AdminService {
    private final AdminDAO adminDAO = new AdminDAO();

    // Authenticate Admin from web input
    public Admin login(String username, String password) {
        Admin admin = adminDAO.getAdminByUsername(username);
        if (admin != null && admin.getPassword().equals(password)) {
            return admin;
        }
        return null;
    }

    // Add more business methods here as needed (e.g., CRUD/or admin-only actions)
}
