package controller;

import dao.MentorDAO;
import entity.Mentor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/ViewMentorAccountDetail")
public class ViewMentorAccountDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        MentorDAO dao = new MentorDAO();
        Mentor mentor = dao.getMentorDetail(id); // lấy theo mentor.id

        request.setAttribute("mentor", mentor);
        request.getRequestDispatcher("views/ViewMentorAccountDetail.jsp").forward(request, response);
    }
}
