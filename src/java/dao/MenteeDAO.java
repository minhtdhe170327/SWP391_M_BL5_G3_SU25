package dao;

import dbcontext.DBContext;
import entity.*;
import java.sql.*;
import java.util.*;
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
        query = "SELECT m.id,m.accountid,m.name,m.address,m.phone,m.birthday,m.sex\n"
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
                list.add(new Mentor(id, accountid, firstname,lastname, address, phone, birthday, sex, introduce, achievement, avatar, costHire));
            }
        } catch (Exception e) {
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

}
