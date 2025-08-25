package dao;

import dbcontext.DBContext;
import entity.*;
import java.sql.*;
import java.util.*;
public class MenteeDAO extends DBContext {

    public void updateMenteeProfile(int menteeId, String firstname,String lastname, String sex, String address, String phone, java.sql.Date birthday, String introduce) {
    String query = "UPDATE Mentee SET firstname=?,lastname=?, sex=?, address=?, phone=?, birthday=?, introduce=? WHERE id=?";
    try {
        ps = connection.prepareStatement(query);
        ps.setString(1, firstname);
        ps.setString(2, lastname);
        ps.setString(3, sex);
        ps.setString(4, address);
        ps.setString(5, phone);
        ps.setDate(6, birthday);
        ps.setString(7, introduce);
        ps.setInt(8, menteeId);
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}


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
        } 
        
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<CodeRequest> pagingMenteeRequest(int mid, int index) {
        List<CodeRequest> list = new ArrayList<>();
        query = "SELECT cr.id, cr.title, cr.content, cr.deadline, cr.menteeID "
                + "FROM coderequest cr "
                + "WHERE cr.menteeID = ? "
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
            e.printStackTrace();
        }
        return list;
    }

    public int getTotalMenteeRequest(int menteeid) {
        query = "SELECT COUNT(*) count "
                + "FROM coderequest cr "
                + "WHERE cr.menteeID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, menteeid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int x = rs.getInt("count");
                return x;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public void inserCodeRequest(int mid, String title, String content, java.sql.Date deadline) {
        query = "INSERT INTO coderequest (title, content, deadline, menteeID) VALUES (?,?,?,?);";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, title);
            ps.setString(2, content);
            ps.setDate(3, deadline);
            ps.setInt(4, mid);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public CodeRequest getNewInsertReqeust() {
        query = "SELECT TOP 1 * FROM coderequest ORDER BY id DESC";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                java.sql.Date deadline = rs.getDate("deadline");
                int menteeid = rs.getInt("menteeID");
                return new CodeRequest(id, title, content, deadline, menteeid);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void inserMentorCodeRequest(int requestid, int mentorid) {
        query = "INSERT INTO mentorcoderequest (coderequestid, mentorid) VALUES(?,?);";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, requestid);
            ps.setInt(2, mentorid);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inserCodeRequestSkill(int requestid, int skillid) {
        query = "INSERT INTO coderequestskill (coderequestid, skillid) VALUES(?,?);";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, requestid);
            ps.setInt(2, skillid);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CodeRequest getAReqeustByID(int requestid) {
        query = "SELECT * FROM coderequest WHERE id=? \n";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, requestid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                java.sql.Date deadline = rs.getDate("deadline");
                int menteeid = rs.getInt("menteeid");
                return new CodeRequest(id, title, content, deadline, menteeid);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Answer getAnswer(int mentorid,int requestid){
        query = "SELECT a.id,a.mentorcoderequestid,a.content FROM answer a, mentorcoderequest mc \n"
                + "WHERE a.mentorcoderequestid=mc.id AND mc.mentorid=? AND mc.coderequestid=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1,mentorid);
            ps.setInt(2,requestid);
            rs=ps.executeQuery();
            while(rs.next()){
                int id=rs.getInt("id");
                int mcrid=rs.getInt("mentorcoderequestid");
                String content=rs.getString("content");
                return new Answer(id, mcrid, content);
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public List<Mentor> getMentorOfRequest(int rid) {
        List<Mentor> list = new ArrayList<>();
        query = "SELECT m.id,m.accountid,m.firstname,m.lastname,m.address,m.phone,m.birthday,m.sex\n"
                + ",m.introduce,m.achievement,m.avatar,m.costHire\n"
                + "FROM coderequest c, mentor m, mentorcoderequest mc\n"
                + "WHERE c.id=mc.coderequestid AND m.id=mc.mentorid AND c.id=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, rid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int accountid = rs.getInt("accountid");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                java.sql.Date birthday = rs.getDate("birthday");
                String sex = rs.getString("sex");
                String introduce = rs.getString("introduce");
                String achievement = rs.getString("achievement");
                String avatar = rs.getString("avatar");
                float costHire = rs.getFloat("costHire");
                list.add(new Mentor(id, accountid, firstname, lastname, address, phone, birthday, sex, introduce, achievement, avatar, costHire));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<Skill> getSkillARequest(int rid) {
        List<Skill> skill = new ArrayList<>();
        query = "SELECT s.id,s.name\n"
                + "FROM coderequest c, Skill s, coderequestskill ms\n"
                + "WHERE c.id=ms.coderequestid AND s.id=ms.skillid AND c.id=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, rid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                skill.add(new Skill(id, name));
            }
        } catch (Exception e) {
        }
        return skill;
    }
    
    public void updatecoderequest(int requestid, String title, String deadline, String content) {
        query = "UPDATE coderequest SET title=?,content=?,deadline=? WHERE id=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, title);
            ps.setString(2, content);
            ps.setDate(3, java.sql.Date.valueOf(deadline));
            ps.setInt(4, requestid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public boolean checkMentorCodeRequest(int requestid, int mentorid) {
        List<MentorRequest> mr = new ArrayList<>();
        query = "SELECT * FROM mentorcoderequest WHERE coderequestid=? AND mentorid=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, requestid);
            ps.setInt(2, mentorid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int requesti = rs.getInt("coderequestid");
                int mentori = rs.getInt("mentorid");
                mr.add(new MentorRequest(id, requesti, mentori));
            }
        } catch (Exception e) {
        }
        if (mr.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public void deletecoderequestskill(int requestid) {
        query = "DELETE FROM coderequestskill WHERE coderequestid=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, requestid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
   public List<HireRequestlist> pagingMentorHireRequest(int mentorid, int index) {
    List<HireRequestlist> list = new ArrayList<>();
    query = "SELECT h.id, h.menteeid, h.mentorid, h.title, h.content, h.statusid, " +
            "s.[Status] as statusname, m.firstname, m.lastname, mt.costHire " +
            "FROM hirerequest h " +
            "JOIN mentee m ON h.menteeid = m.id " +
            "JOIN [status] s ON h.statusid = s.id " +
            "JOIN mentor mt ON h.mentorid = mt.id " +
            "WHERE h.mentorid = ? " +
            "ORDER BY h.id " +
            "OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY";
    try {
        ps = connection.prepareStatement(query);
        ps.setInt(1, mentorid);
        ps.setInt(2, (index - 1) * 4);
        rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String firstname = rs.getString("firstname");
            String lastname = rs.getString("lastname");
            String title = rs.getString("title");
            String content = rs.getString("content");
            float costhire = rs.getFloat("costHire");
            String status = rs.getString("statusname");

            list.add(new HireRequestlist(id, firstname, lastname, title, content, costhire, status));
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.err.println("Error in pagingMentorHireRequest: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return list;
}

    public int getTotalMentorHireRequest(int mentorid) {
        query = "SELECT COUNT(*) count FROM hirerequest WHERE mentorid = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, mentorid);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Log the error
            System.err.println("Error in getTotalMentorHireRequest: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
    
public List<CodeRequest> searchRequest(String name, int index, int mid) {
        List<CodeRequest> list = new ArrayList<>();
        query = "SELECT * FROM coderequest c WHERE menteeid=? AND (c.title LIKE ? OR c.content LIKE ?)\n"
                + "ORDER BY id\n"
                + "OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, mid);
            ps.setString(2, "%" + name + "%");
            ps.setString(3, "%" + name + "%");
            ps.setInt(4, (index - 1) * 4);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                java.sql.Date deadline = rs.getDate("deadline");
                int menteeid = rs.getInt("menteeid");
                list.add(new CodeRequest(id, title, content, deadline, menteeid));
            }
        } catch (Exception e) {
        }
        return list;
    }


// Đếm tổng số request toàn hệ thống

    public int countAllRequest() {
        int count = 0;
        try {
            query = "SELECT COUNT(*) FROM coderequest";
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
        }
        return count;
    }

    // Đếm tổng số hire request toàn hệ thống
    
    public int countAllHireRequest() {
        int count = 0;
        try {
            query = "SELECT COUNT(*) FROM hirerequest";
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
        }
        return count;
    }
    
     public List<HireRequestlist> getAllHireRequests(int index) {
        List<HireRequestlist> list = new ArrayList<>();
        query = "SELECT h.id,  m.firstname, m.lastname, h.title, h.content, m.costHire, s.[Status] " +
                "FROM hirerequest h, [status] s, mentor m " +
                "WHERE h.mentorid = m.id AND h.statusid = s.id " +
                "ORDER BY h.id " +
                "OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, (index - 1) * 4);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstname = rs.getString("firstname");
            String lastname = rs.getString("lastname");
                String title = rs.getString("title");
                String content = rs.getString("content");
                float cost = rs.getFloat("costHire");
                String status = rs.getString("Status");
                list.add(new HireRequestlist(id, firstname,lastname, title, content, cost, status));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
      public int getTotalAllHireRequests() {
        query = "SELECT COUNT(*) count FROM hirerequest";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int x = rs.getInt("count");
                return x;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
      public List<HireRequestlist> pagingMenteeHireRequest(int mid, int index) {
         List<HireRequestlist> list = new ArrayList<>();
         query = "SELECT h.id, m.firstname, m.lastname,h.title,h.content,m.costHire,s.[Status] FROM hirerequest h, [status] s,mentor m \n"
                 + "WHERE h.mentorid=m.id AND h.statusid=s.id AND menteeid=?\n"
                 + "ORDER BY id\n"
                 + "OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY";
         try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, mid);
            ps.setInt(2, (index - 1) * 4);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstname = rs.getString("firstname");
            String lastname = rs.getString("lastname");
                String title = rs.getString("title");
                String content = rs.getString("content");
                float cost=rs.getFloat("costhire");
                String status = rs.getString("status");
                list.add(new HireRequestlist(id, firstname,lastname, title, content, cost, status));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public int getTotalMenteeHireRequest(int menteeid) {
        query = "SELECT COUNT(*) count FROM hirerequest WHERE menteeid=?";
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
    public List<HireRelationship> getHireRelationship(){
        List<HireRelationship> list=new ArrayList<>();
        query = "SELECT * FROM HireRelatitonship";
        try {
            ps = connection.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next()){
                int id=rs.getInt("id");
                int menteeid=rs.getInt("menteeid");
                int mentorid=rs.getInt("mentorid");
                list.add(new HireRelationship(id, menteeid, mentorid));
            }
        } catch (Exception e) {
        }
        return list;
    }
    public void inserHireRequest(int menteeid, int mentorid, String title, String content) {
        query = "INSERT INTO hirerequest VALUES(?,?,?,?,3);";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, menteeid);
            ps.setInt(2, mentorid);
            ps.setString(3, title);
            ps.setString(4, content);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

}
