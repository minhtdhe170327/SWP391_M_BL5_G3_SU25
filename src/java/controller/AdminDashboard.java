package controller;

import dao.MenteeDAO;
import dao.PostDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminDashboard extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MenteeDAO menteeDAO = new MenteeDAO();
        int totalRequest = menteeDAO.countAllRequest();
        int totalHireRequest = menteeDAO.countAllHireRequest();
        int totalFeedback = menteeDAO.getTotalFeedback();
        int totalAnswer = menteeDAO.getTotalAnswer();

        PostDAO postDAO = new PostDAO();
        int totalPost = postDAO.getTotalPosts();

        request.setAttribute("totalRequest", totalRequest);
        request.setAttribute("totalHireRequest", totalHireRequest);
        request.setAttribute("totalPost", totalPost);
        request.setAttribute("totalFeedback", totalFeedback);
        request.setAttribute("totalAnswer", totalAnswer);

        request.getRequestDispatcher("/views/AdminDashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Admin Dashboard Controller";
    }
}
