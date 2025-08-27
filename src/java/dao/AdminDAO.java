package dao;

import dbcontext.DBContext;
import entity.CodeRequest;
import entity.Mentee;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO extends DBContext {

    public List<CodeRequest> getAllRequests(int index) {
        List<CodeRequest> list = new ArrayList<>();
        query = "SELECT c.id, c.title, c.content, c.deadline, c.menteeid, " +
                "m.firstname, m.lastname, m.avatar as mentee_avatar " +
                "FROM coderequest c " +
                "JOIN mentee m ON c.menteeid = m.id " +
                "ORDER BY c.id " +
                "OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, (index - 1) * 4);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                Date deadline = rs.getDate("deadline");
                int menteeid = rs.getInt("menteeid");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String avatar = rs.getString("mentee_avatar");

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
        query = "SELECT COUNT(*) count FROM coderequest";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
