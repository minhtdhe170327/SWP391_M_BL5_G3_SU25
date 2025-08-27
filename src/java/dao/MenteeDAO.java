package dao;

import dbcontext.DBContext;
import entity.*;
import java.sql.*;
import java.util.*;

public class MenteeDAO extends DBContext {

    public void updateMenteeProfile(int menteeId, String firstname, String lastname, String sex, String address, String phone, java.sql.Date birthday, String introduce) {
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
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
 public void updateFeedback(int id,int star,String comment){
        query = "UPDATE feedback SET star=?, comment=? WHERE id=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1,star);
            ps.setString(2,comment);
            ps.setInt(3,id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    } 

    public Feedback getfeedbackbyid(int fid) {
        query = "SELECT f.id,f.menteeid,f.star,f.comment from feedback f WHERE f.id=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, fid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int menteeid = rs.getInt("menteeid");
                int star = rs.getInt("star");
                String comment = rs.getString("comment");
                return new Feedback(id, menteeid, star, comment);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void createFeedbackAnswer(int fid, int aid) {
        query = "INSERT INTO feedbackanswer VALUES(?,?) ";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, fid);
            ps.setInt(2, aid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void createFeedback(int id, int star, String comment) {
        query = "INSERT INTO feedback VALUES(?,?,?) ";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.setInt(2, star);
            ps.setString(3, comment);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public Feedback getfeedbackadd() {
        query = "SELECT TOP 1 * FROM feedback ORDER BY id DESC";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int menteeid = rs.getInt("menteeid");
                int star = rs.getInt("star");
                String comment = rs.getString("comment");
                return new Feedback(id, menteeid, star, comment);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Feedback getfeedback(int mentorid, int requestid) {
        query = "SELECT f.id,f.menteeid,f.star,f.comment from feedback f, feedbackanswer fa,answer a, mentorcoderequest mcq \n"
                + "where f.id=fa.feedbackid  and fa.answerid=a.id and a.mentorcoderequestid=mcq.id\n"
                + "AND mcq.coderequestid=? AND mcq.mentorid=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, requestid);
            ps.setInt(2, mentorid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int menteeid = rs.getInt("menteeid");
                int star = rs.getInt("star");
                String comment = rs.getString("comment");
                return new Feedback(id, menteeid, star, comment);
            }
        } catch (Exception e) {
        }
        return null;
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
        } catch (SQLException e) {
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

    public Answer getAnswer(int mentorid, int requestid) {
        query = "SELECT a.id,a.mentorcoderequestid,a.content FROM answer a, mentorcoderequest mc \n"
                + "WHERE a.mentorcoderequestid=mc.id AND mc.mentorid=? AND mc.coderequestid=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, mentorid);
            ps.setInt(2, requestid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int mcrid = rs.getInt("mentorcoderequestid");
                String content = rs.getString("content");
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
        query = "SELECT h.id, h.menteeid, h.mentorid, h.title, h.content, h.statusid, "
                + "s.[Status] as statusname, m.firstname, m.lastname, mt.costHire "
                + "FROM hirerequest h "
                + "JOIN mentee m ON h.menteeid = m.id "
                + "JOIN [status] s ON h.statusid = s.id "
                + "JOIN mentor mt ON h.mentorid = mt.id "
                + "WHERE h.mentorid = ? "
                + "ORDER BY h.id "
                + "OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY";
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
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
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
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
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
public HireRequest getHireRequestbyid(int requestid){
        query = "SELECT * FROM hirerequest WHERE id=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1,requestid);
            rs=ps.executeQuery();
            while(rs.next()){
                int id=rs.getInt("id");
                int idmentee=rs.getInt("menteeid");
                int mentorid=rs.getInt("mentorid");
                String title=rs.getString("title");
                String content=rs.getString("content");
                int statusid=rs.getInt("statusid");
                return new HireRequest(id, idmentee, mentorid, title, content, statusid);
            }
        } catch (Exception e) {
        }
        return null;
    }
public void updateHireRequest(int id, int menteeid, int mentorid, String title, String content) {
    String query = "UPDATE hirerequest SET menteeid=?, mentorid=?, title=?, content=? WHERE id=?";
    try {
        ps = connection.prepareStatement(query);
        ps.setInt(1, menteeid);
        ps.setInt(2, mentorid);
        ps.setString(3, title);
        ps.setString(4, content);
        ps.setInt(5, id);
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
public void deleteHireRequest(int id) {
    String sql = "DELETE FROM hirerequest WHERE id = ?";
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        System.out.println("Deleted hirequest id=" + id + " successfully!");
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public List<HireRequestlist> searchHireRequest(String name, int index, int mid) {
        List<HireRequestlist> list = new ArrayList<>();
        query = "SELECT h.id,m.firstname, m.lastname,h.title,h.content,m.costhire,s.[Status] FROM hirerequest h, [status] s,mentor m \n"
                 + "WHERE h.mentorid=m.id AND h.statusid=s.id AND menteeid=?\n"
                + "AND (h.title LIKE ? OR h.content LIKE ? OR m.name LIKE ?)"
                 + "ORDER BY id\n"
                 + "OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, mid);
            ps.setString(2, "%" + name + "%");
            ps.setString(3, "%" + name + "%");
            ps.setString(4, "%" + name + "%");
            ps.setInt(5, (index - 1) * 4);
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


public int getTotalFeedback() {
        int total = 0;
        String sql = "SELECT COUNT(*) FROM Feedback";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }
    
    
    public int getTotalAnswer() {
    int total = 0;
    String sql = "SELECT COUNT(*) FROM answer";
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            total = rs.getInt(1);
        }
        rs.close();
        ps.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return total;
}
    
    
public List<CodeRequest> getAllRequests(int index) {
    List<CodeRequest> list = new ArrayList<>();
    String sql = "SELECT c.id, c.title, c.content, c.deadline, c.menteeid, " +
                 "m.firstname, m.lastname, m.avatar " +
                 "FROM coderequest c " +
                 "JOIN mentee m ON c.menteeid = m.id " +
                 "ORDER BY c.id " +
                 "OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY";
    try {
        ps = connection.prepareStatement(sql);
        ps.setInt(1, (index - 1) * 4);
        rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            String content = rs.getString("content");
            java.sql.Date deadline = rs.getDate("deadline");
            int menteeid = rs.getInt("menteeid");
            String firstname = rs.getString("firstname");
            String lastname = rs.getString("lastname");
            String avatar = rs.getString("avatar");

            Mentee mentee = new Mentee();
            mentee.setId(menteeid);
            mentee.setFirstname(firstname);
            mentee.setLastname(lastname);
            mentee.setAvatar(avatar);

            CodeRequest request = new CodeRequest(id, title, content, deadline, menteeid);
            request.setMentee(mentee);

            list.add(request);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


    public int getTotalAllRequests() {
    int total = 0;
    String sql = "SELECT COUNT(*) FROM coderequest";
    try {
        ps = connection.prepareStatement(sql);
        rs = ps.executeQuery();
        if (rs.next()) {
            total = rs.getInt(1);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return total;
}

    
    
    // Lấy tất cả answer (admin dùng)
public List<Answer> getAllAnswers(int index) {
    List<Answer> list = new ArrayList<>();
    query = "SELECT a.id, a.content, mc.mentorid, c.menteeid, c.title " +
            "FROM answer a " +
            "JOIN mentorcoderequest mc ON a.mentorcoderequestid = mc.id " +
            "JOIN coderequest c ON mc.coderequestid = c.id " +
            "ORDER BY a.id " +
            "OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY";
    try {
        ps = connection.prepareStatement(query);
        ps.setInt(1, (index - 1) * 4);
        rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String content = rs.getString("content");
            int mentorId = rs.getInt("mentorid");
            int menteeId = rs.getInt("menteeid");
            String requestTitle = rs.getString("title");

            Answer a = new Answer(id, mentorId, content);
            // nếu muốn mở rộng có thể tạo AnswerList DTO chứa menteeId, requestTitle
            list.add(a);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}

public int getTotalAllAnswers() {
    query = "SELECT COUNT(*) count FROM answer";
    try {
        ps = connection.prepareStatement(query);
        rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("count");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 0;
}

// Lấy answer theo mentee (mentee dùng)
public List<Answer> getAnswersByMentee(int menteeId, int index) {
    List<Answer> list = new ArrayList<>();
    query = "SELECT a.id, a.content, mc.mentorid, c.menteeid, c.title " +
            "FROM answer a " +
            "JOIN mentorcoderequest mc ON a.mentorcoderequestid = mc.id " +
            "JOIN coderequest c ON mc.coderequestid = c.id " +
            "WHERE c.menteeid=? " +
            "ORDER BY a.id " +
            "OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY";
    try {
        ps = connection.prepareStatement(query);
        ps.setInt(1, menteeId);
        ps.setInt(2, (index - 1) * 4);
        rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String content = rs.getString("content");
            int mentorId = rs.getInt("mentorid");
            Answer a = new Answer(id, mentorId, content);
            list.add(a);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}

public int getTotalAnswersByMentee(int menteeId) {
    query = "SELECT COUNT(*) count " +
            "FROM answer a " +
            "JOIN mentorcoderequest mc ON a.mentorcoderequestid = mc.id " +
            "JOIN coderequest c ON mc.coderequestid = c.id " +
            "WHERE c.menteeid=?";
    try {
        ps = connection.prepareStatement(query);
        ps.setInt(1, menteeId);
        rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("count");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 0;
}


public List<AnswerList> getAllAnswersWithNames(int index) {
    List<AnswerList> list = new ArrayList<>();
    query = "SELECT a.id, a.content, " +
            "me.firstname + ' ' + me.lastname AS menteeName, " +
            "mt.firstname + ' ' + mt.lastname AS mentorName, " +
            "c.title " +
            "FROM answer a " +
            "JOIN mentorcoderequest mc ON a.mentorcoderequestid = mc.id " +
            "JOIN coderequest c ON mc.coderequestid = c.id " +
            "JOIN mentee me ON c.menteeid = me.id " +
            "JOIN mentor mt ON mc.mentorid = mt.id " +
            "ORDER BY a.id " +
            "OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY";
    try {
        ps = connection.prepareStatement(query);
        ps.setInt(1, (index - 1) * 4);
        rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new AnswerList(
                rs.getInt("id"),
                rs.getString("content"),
                rs.getString("mentorName"),
                rs.getString("menteeName"),
                rs.getString("title")
            ));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}

public void deleteAnswer(int id) {
    query = "DELETE FROM answer WHERE id = ?";
    try {
        ps = connection.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
        System.out.println("Deleted answer id=" + id + " successfully!");
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public void deleteCodeRequest(int id) {
    try {
        connection.setAutoCommit(false);

        String sql1 = "DELETE FROM MentorCodeRequestStatus WHERE mentorcoderequestid IN (SELECT id FROM mentorcoderequest WHERE coderequestid=?)";
        ps = connection.prepareStatement(sql1);
        ps.setInt(1, id);
        ps.executeUpdate();

        String sql2 = "DELETE FROM answer WHERE mentorcoderequestid IN (SELECT id FROM mentorcoderequest WHERE coderequestid=?)";
        ps = connection.prepareStatement(sql2);
        ps.setInt(1, id);
        ps.executeUpdate();

        String sql3 = "DELETE FROM mentorcoderequest WHERE coderequestid=?";
        ps = connection.prepareStatement(sql3);
        ps.setInt(1, id);
        ps.executeUpdate();

        String sql4 = "DELETE FROM coderequestskill WHERE coderequestid=?";
        ps = connection.prepareStatement(sql4);
        ps.setInt(1, id);
        ps.executeUpdate();

        String sql5 = "DELETE FROM coderequest WHERE id=?";
        ps = connection.prepareStatement(sql5);
        ps.setInt(1, id);
        ps.executeUpdate();

        connection.commit();
    } catch (Exception e) {
        try { connection.rollback(); } catch (Exception ex) { ex.printStackTrace(); }
        e.printStackTrace();
    } finally {
        try { if (ps != null) ps.close(); } catch (Exception e) {}
    }
}


}
