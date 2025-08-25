/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dbcontext.DBContext;
import entity.*;
import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus TUF
 */
public class MentorDAO extends DBContext {

    public void updateMentorProfile(int mentorId, String firstname,String lastname, String sex, String address, String phone, java.sql.Date birthday, String introduce, String achievement, float costHire) {
        String query = "UPDATE Mentor SET firstname=?,lastname=?, sex=?, address=?, phone=?, birthday=?, introduce=?, achievement=?, costHire=? WHERE id=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setString(3, sex);
            ps.setString(4, address);
            ps.setString(5, phone);
            ps.setDate(6, birthday);
            ps.setString(7, introduce);
            ps.setString(8, achievement);
            ps.setFloat(9, costHire);
            ps.setInt(10, mentorId);

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
    public List<Mentor> searchMentor(String name, int index) {
        List<Mentor> list = new ArrayList<>();
        query = "SELECT * FROM Mentor m WHERE (m.lastname + m.firstname) LIKE ?\n"
                + "ORDER BY id\n"
                + "OFFSET ? ROWS FETCH NEXT 3 ROWS ONLY";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, "%" + name + "%");
            ps.setInt(2, (index - 1) * 3);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int accountid = rs.getInt("accountid");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                Date birthday = rs.getDate("birthday");
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

    public List<Mentor> getTop3Mentor() {
        List<Mentor> list = new ArrayList<>();
        query = "WITH t AS(SELECT mc.mentorid id,AVG(CAST (f.star AS FLOAT(2))) averageStar FROM Feedback f, feedbackanswer fa, answer a, mentorcoderequest mc\n"
                + "WHERE f.id=fa.feedbackid and fa.answerid=a.id and a.mentorcoderequestid=mc.id\n"
                + "GROUP BY mc.mentorid)\n"
                + "SELECT TOP (3) m.id,m.accountid, m.firstname,m.lastname,m.address,m.phone,m.birthday,m.sex,m.introduce,m.achievement,m.avatar,m.costHire,\n"
                + "COALESCE(t.averageStar, 0) as averageStar\n"
                + "FROM mentor m\n"
                + "LEFT JOIN t ON m.id=t.id\n"
                + "ORDER BY COALESCE(t.averageStar, 0) DESC";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int accountid = rs.getInt("accountid");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                Date birthday = rs.getDate("birthday");
                String sex = rs.getString("sex");
                String introduce = rs.getString("introduce");
                String achievement = rs.getString("achievement");
                String avatar = rs.getString("avatar");
                float costHire = rs.getFloat("costHire");
                float averageStar = rs.getFloat("averageStar");
                list.add(new Mentor(id, accountid, firstname, lastname, address, phone, birthday,
            sex, introduce, achievement, avatar, costHire, averageStar));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Mentor getMentorbyAccID(int accid) {
        query = "SELECT * FROM Mentor WHERE accountid=?";
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
                Date birthday = rs.getDate("birthday");
                String sex = rs.getString("sex");
                String introduce = rs.getString("introduce");
                String achievement = rs.getString("achievement");
                String avatar = rs.getString("avatar");
                float costHire = rs.getFloat("costHire");
                return new Mentor(id, accountid, firstname,lastname, address, phone, birthday, sex, introduce, achievement, avatar, costHire);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Mentor getMentorDetail(int mentorid) {
        Mentor mentor = new Mentor();
        query = "SELECT * FROM Mentor WHERE id=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, mentorid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int accountid = rs.getInt("accountid");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                Date birthday = rs.getDate("birthday");
                String sex = rs.getString("sex");
                String introduce = rs.getString("introduce");
                String achievement = rs.getString("achievement");
                String avatar = rs.getString("avatar");
                float costHire = rs.getFloat("costHire");
                mentor = new Mentor(id, accountid, firstname,lastname, address, phone, birthday, sex, introduce, achievement, avatar, costHire);
            }
        } catch (Exception e) {
        }
        return mentor;
    }

    public List<Mentor> getAllMentor() {
        List<Mentor> list = new ArrayList<>();
        query = "SELECT * FROM Mentor";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int accountid = rs.getInt("accountid");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                Date birthday = rs.getDate("birthday");
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

    public int getTotalMentor() {
        query = "SELECT COUNT(*) count FROM Mentor";
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

    public List<Mentor> pagingMentor(int index) {
        List<Mentor> list = new ArrayList<>();
        query = "SELECT * FROM Mentor\n"
                + "ORDER BY id\n"
                + "OFFSET ? ROWS FETCH NEXT 3 ROWS ONLY";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, (index - 1) * 3);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int accountid = rs.getInt("accountid");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                Date birthday = rs.getDate("birthday");
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

    public List<Skill> getallskill() {
        List<Skill> list = new ArrayList<>();
        query = "SELECT * FROM skill";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                list.add(new Skill(id, name));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public List<CodeRequest> searchRequest(String name, int index, int mid) {
        List<CodeRequest> list = new ArrayList<>();
        query = "SELECT c.id,c.title,c.content,c.deadline,c.menteeID FROM coderequest c,mentorcoderequest mc "
                + "WHERE c.id=mc.coderequestid AND mc.mentorid=? AND (c.title LIKE ? OR c.content LIKE ?)\n"
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
                int menteeid = rs.getInt("menteeID");
                list.add(new CodeRequest(id, title, content, deadline, menteeid));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public List<CodeRequest> pagingMentorRequest(int mid, int index) {
        List<CodeRequest> list = new ArrayList<>();
        query = "SELECT c.id,c.title,c.content,c.deadline,c.menteeID "
                 + "FROM coderequest c,mentorcoderequest mc WHERE c.id=mc.coderequestid AND mc.mentorid=?\n"
                 + "ORDER BY c.id\n"
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

    public int getTotalMentorRequest(int mentorId) {
        query = "SELECT COUNT(*) count "
                + "FROM mentorcoderequest mc "
                + "JOIN coderequest c ON c.id = mc.coderequestid "
                + "WHERE mc.mentorid = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, mentorId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("count");
            }
        } catch (Exception e) {
        }
        return 0;
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
} 
