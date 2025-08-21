package dao;

import dbcontext.DBContext;
import entity.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenteeDAO extends DBContext {

    public Mentee getMenteebyAccID(int accid) {
        query = "SELECT * FROM Mentee WHERE accountid=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, accid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int accountid = rs.getInt("accountid");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                java.sql.Date birthday = rs.getDate("birthday");
                String avatar = rs.getString("avatar");
                String sex = rs.getString("sex");
                String introduce = rs.getString("introduce");

                return new Mentee(id, accountid, firstname, lastname, address, phone, birthday, sex, introduce, avatar);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<CodeRequest> pagingMenteeRequest(int mid, int index) {
        List<CodeRequest> list = new ArrayList<>();
        query = "SELECT cr.id, cr.title, cr.content, cr.deadline, cr.menteeID "
                + "FROM coderequest cr "
                + "JOIN mentee m ON cr.menteeID = m.id "
                + "WHERE m.accountid = ? "
                + "ORDER BY cr.id "
                + "OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, mid);
            ps.setInt(2, (index - 1) * 4);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                java.sql.Date deadline = rs.getDate("deadline");
                int menteeid = rs.getInt("menteeID");
                list.add(new CodeRequest(id, title, content, deadline, menteeid));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public int getTotalMenteeRequest(int menteeid) {
        query = "SELECT COUNT(*) count "
                + "FROM coderequest cr "
                + "JOIN mentee m ON cr.menteeID = m.id "
                + "WHERE m.accountid = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, menteeid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int x = rs.getInt("count");
                return x;
            }
        } catch (Exception e) {
        }
        return 0;
    }
}
