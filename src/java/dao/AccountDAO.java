/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dbcontext.DBContext;
import entity.Account;
import entity.Role;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

/**
 *
 * @author Acer
 */
public class AccountDAO extends DBContext {

    public List<Role> getRole() {
        List<Role> role = new ArrayList<>();
        query = "SELECT * FROM Roles WHERE [name] NOT LIKE 'admin'";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String rname = rs.getString("name");
                role.add(new Role(id, rname));
            }
        } catch (Exception e) {
        }
        return role;
    }

    public Boolean getForgotedStatusByUsername(String username) {
        String query = "SELECT forgoted FROM Account WHERE accountname = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("forgoted");
            }
        } catch (Exception e) {
            System.err.println("Lỗi trong getForgotedStatusByUsername: " + e.getMessage());
            e.printStackTrace();
        }
        return null; // Trả về null nếu không tìm thấy tài khoản hoặc có lỗi
    }

    public Account getAccount(String username, String password) {
        Account account = null;
        query = "SELECT * FROM Account WHERE accountname=? AND password=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("accountname");
                String pass = rs.getString("password");
                int roleid = rs.getInt("roleid");
                String email = rs.getString("email");
                account = new Account(id, name, pass, roleid, email);
            }
        } catch (Exception e) {
        }
        return account;
    }

    public int checkrole(int accid) {
        query = "SELECT r.id id FROM roles r, ACCOUNT a WHERE r.id=a.roleid AND a.id=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, accid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int role = rs.getInt("id");
                return role;
            }
        } catch (Exception e) {
        }
        return 0;
    }

}
