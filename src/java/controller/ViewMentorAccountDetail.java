package controller;

import dao.AccountDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import entity.Account;


//@WebServlet("/ViewMentorAccountDetail")
public class ViewMentorAccountDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        AccountDAO dao = new AccountDAO();
        Map<String, Object> mentorDetail = dao.getMentorDetail(id);

        request.setAttribute("mentor", mentorDetail);
        request.getRequestDispatcher("views/ViewMentorAccountDetail.jsp").forward(request, response);
    }
}






