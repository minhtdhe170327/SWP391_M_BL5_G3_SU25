package controller;

import dao.AccountDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

//@WebServlet("/ViewMentorAccountDetail")
public class ViewMentorAccountDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        AccountDAO dao = new AccountDAO();
        Map<String, Object> mentor = dao.getMentorDetail(id);

        if (mentor.isEmpty()) {
            request.setAttribute("error", "Mentor not found");
            request.getRequestDispatcher("MentorAccountList.jsp").forward(request, response);
            return;
        }

        request.setAttribute("mentor", mentor);
        request.getRequestDispatcher("ViewMentorAccountDetail.jsp").forward(request, response);
    }
}

