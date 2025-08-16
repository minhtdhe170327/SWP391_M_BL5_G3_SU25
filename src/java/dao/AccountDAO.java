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
        String hashedPassword = md5(password);
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
private String md5(String input) {
    try {
        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
        byte[] array = md.digest(input.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : array) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    } catch (java.security.NoSuchAlgorithmException e) {
        e.printStackTrace();
    }
    return null;
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
    public boolean checkExistedEmail(String email) {
        String sql = "select email from account where email = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println("checkExistedEmail: " + e.getMessage());
        }
        return false;
    }
    public boolean checkExistedUserWithUsername(String username) {
        String sql = "SELECT * FROM account WHERE username = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println("checkExistedUserWithUsername: " + e.getMessage());
        }
        return false;
    }  
    
    public int insertAccountAndGetId(String username, String password, int roleid, String email) {
        String sql = "INSERT INTO Account(accountname, password, roleid, email) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, username);
            st.setString(2, password);
            st.setInt(3, roleid);
            st.setString(4, email);
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return -1;
    }
    
    public void insertMentee(int accountid, String name, String address, String phone, java.sql.Date birthday, String sex, String introduce, String avatar) {
        String sql = "INSERT INTO Mentee(accountid, name, address, phone, birthday, sex, introduce, avatar) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, accountid);
            st.setString(2, name);
            st.setString(3, address);
            st.setString(4, phone);
            st.setDate(5, birthday);
            st.setString(6, sex);
            st.setString(7, introduce);
            st.setString(8, avatar);
            st.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void insertMentor(int accountid, String name, String address, String phone, java.sql.Date birthday, String sex, String introduce, String achievement, String avatar, float costHire) {
        String sql = "INSERT INTO Mentor(accountid, name, address, phone, birthday, sex, introduce, achievement, avatar, costHire) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, accountid);
            st.setString(2, name);
            st.setString(3, address);
            st.setString(4, phone);
            st.setDate(5, birthday);
            st.setString(6, sex);
            st.setString(7, introduce);
            st.setString(8, achievement);
            st.setString(9, avatar);
            st.setFloat(10, costHire);
            st.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
     public void changePassword(int accid, String newpassword) {
    String hashedPassword = md5(newpassword); // băm mật khẩu mới
    query = "UPDATE Account SET password=? WHERE id=?";
    try {
        ps = connection.prepareStatement(query);
        ps.setString(1, hashedPassword);
        ps.setInt(2, accid);
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

}
