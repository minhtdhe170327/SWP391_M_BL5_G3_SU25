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
    
    // Mentee
public void insertMentee(int accountid, String firstname, String lastname,
                         String address, String phone, java.sql.Date birthday,
                         String sex, String avatar, String introduce) {
    String sql = "INSERT INTO Mentee(accountid, firstname, lastname, address, phone, birthday, sex, avatar, introduce) "
               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try {
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1, accountid);
        st.setString(2, firstname);
        st.setString(3, lastname);
        st.setString(4, address);
        st.setString(5, phone);
        st.setDate(6, birthday);
        st.setString(7, sex);
        st.setString(8, avatar);
        st.setString(9, introduce);
        st.executeUpdate();
    } catch (Exception e) { 
        e.printStackTrace(); 
    }
}

// Mentor
public void insertMentor(int accountid, String firstname, String lastname,
                         String address, String phone, java.sql.Date birthday,
                         String sex, String introduce, String achievement,
                         String avatar, float costHire) {
    String sql = "INSERT INTO Mentor(accountid, firstname, lastname, address, phone, birthday, sex, introduce, achievement, avatar, costHire) "
               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try {
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1, accountid);
        st.setString(2, firstname);
        st.setString(3, lastname);
        st.setString(4, address);
        st.setString(5, phone);
        st.setDate(6, birthday);
        st.setString(7, sex);
        st.setString(8, introduce);
        st.setString(9, achievement);
        st.setString(10, avatar);
        st.setFloat(11, costHire);
        st.executeUpdate();
    } catch (Exception e) { 
        e.printStackTrace(); 
    }
}

    //Admin Skill Mânger
    public void addSkillAdmin(String name) {
        String sql = "INSERT INTO Skill(name) VALUES(?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void updateSkillAdmin(int id, String name) {
        String sql = "UPDATE Skill SET name=? WHERE id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void deleteSkillAdmin(int id) {
        String sql = "DELETE FROM Skill WHERE id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public List<entity.Skill> getAllSkills() {
        List<entity.Skill> list = new ArrayList<>();
        String sql = "SELECT * FROM Skill";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                entity.Skill s = new entity.Skill();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<entity.Skill> pagingSkill(String search, int page, int pageSize) {
        List<entity.Skill> list = new ArrayList<>();
        String sql = "SELECT * FROM Skill WHERE (? IS NULL OR name LIKE ?) ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, search == null || search.isEmpty() ? null : search);
            st.setString(2, "%" + (search == null ? "" : search) + "%");
            st.setInt(3, (page - 1) * pageSize);
            st.setInt(4, pageSize);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                entity.Skill s = new entity.Skill();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                list.add(s);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public int countSkill(String search) {
        String sql = "SELECT COUNT(*) FROM Skill WHERE (? IS NULL OR name LIKE ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, search == null || search.isEmpty() ? null : search);
            st.setString(2, "%" + (search == null ? "" : search) + "%");
            ResultSet rs = st.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }
    
    //Account Manager    
    public List<Account> pagingAccount(int index){
        List<Account> list=new ArrayList<>();
         query = "SELECT * FROM Account \n"
                 + "ORDER BY id\n"
                 + "OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY";
         try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, (index - 1) * 4);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String accountname = rs.getString("accountname");
                String pasword = rs.getString("password");
                int roleid = rs.getInt("roleid");
                String email = rs.getString("email");
                list.add(new Account(id, accountname, pasword, roleid, email));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public void deleteAccountById(int id) {
        query = "DELETE FROM Account WHERE id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public boolean updateAccountByAdmin(int id, String accountname, String email, int roleid) {
        String sql = "UPDATE Account SET accountname=?, email=?, roleid=? WHERE id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, accountname);
            ps.setString(2, email);
            ps.setInt(3, roleid);
            ps.setInt(4, id);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateAccountByAdminWithPassword(int id, String accountname, String email, int roleid, String password) {
        String sql = "UPDATE Account SET accountname=?, email=?, roleid=?, password=? WHERE id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, accountname);
            ps.setString(2, email);
            ps.setInt(3, roleid);
            ps.setString(4, password);
            ps.setInt(5, id);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public int getTotalAccount(){
        query = "SELECT COUNT(*) count FROM Account";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int x = rs.getInt("count");
                return x;
            }
        } catch (Exception e) {
        }
        return 0;
    }
    public void changePassword(int accid, String newpassword) {
    query = "UPDATE Account SET Password=? WHERE id=?";
    try {
        ps = connection.prepareStatement(query);
        ps.setString(1, newpassword); // newpassword đã hash
        ps.setInt(2, accid);
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

}
