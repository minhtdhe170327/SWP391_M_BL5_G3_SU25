package controller;

import dao.AccountDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

//@WebServlet("/ViewMenteeAccountDetail")
public class ViewMenteeAccountDetail extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            AccountDAO dao = new AccountDAO();
            Map<String, Object> mentee = dao.getMenteeDetail(id);

            if (mentee != null && !mentee.isEmpty()) {
                request.setAttribute("mentee", mentee);
                request.getRequestDispatcher("views/ViewMenteeAccountDetail.jsp").forward(request, response);
            } else {
                response.sendRedirect("MenteeAccountList?error=NotFound");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("MenteeAccountList?error=InvalidId");
        }
    }
}
