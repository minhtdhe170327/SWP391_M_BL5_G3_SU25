package dao;

import dbcontext.DBContext;
import entity.Feedback;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO extends DBContext {

    public List<Feedback> getAllFeedback(int index, int pageSize) {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT f.id, f.menteeid, f.star, f.comment, " +
                     "m.firstname, m.lastname " +
                     "FROM Feedback f " +
                     "JOIN Mentee m ON f.menteeid = m.id " +
                     "ORDER BY f.id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (index - 1) * pageSize);
            ps.setInt(2, pageSize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Feedback fb = new Feedback(
                        rs.getInt("id"),
                        rs.getInt("menteeid"),
                        rs.getInt("star"),
                        rs.getString("comment")
                );
                fb.setMenteeName(rs.getString("firstname") + " " + rs.getString("lastname"));
                list.add(fb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int countAllFeedback() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM Feedback";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public void deleteFeedback(int id) {
    String sql1 = "DELETE FROM feedbackanswer WHERE feedbackid=?";
    String sql2 = "DELETE FROM Feedback WHERE id=?";
    try {
        PreparedStatement ps1 = connection.prepareStatement(sql1);
        ps1.setInt(1, id);
        ps1.executeUpdate();
        ps1.close();

        PreparedStatement ps2 = connection.prepareStatement(sql2);
        ps2.setInt(1, id);
        int rows = ps2.executeUpdate();
        System.out.println("Deleted feedback rows = " + rows);
        ps2.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
}

}
