package controller;

import dao.FeedbackDAO;
import entity.Feedback;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminViewAllFeedback extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int index = 1;
        int pageSize = 5;
        if (request.getParameter("index") != null) {
            index = Integer.parseInt(request.getParameter("index"));
        }

        FeedbackDAO dao = new FeedbackDAO();
        int count = dao.countAllFeedback();
        int endPage = count / pageSize;
        if (count % pageSize != 0) {
            endPage++;
        }

        List<Feedback> feedbackList = dao.getAllFeedback(index, pageSize);

        request.setAttribute("feedbackList", feedbackList);
        request.setAttribute("tag", index);
        request.setAttribute("endpage", endPage);

        request.getRequestDispatcher("views/AdminViewAllFeedback.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
