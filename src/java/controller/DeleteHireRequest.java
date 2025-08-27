package controller;

import dao.MenteeDAO;
import entity.HireRequest;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteHireRequest extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String hid = request.getParameter("hireid");
        if (hid == null || hid.isEmpty()) {
            hid = request.getParameter("id");
        }

        if (hid == null || hid.isEmpty()) {
            request.getSession().setAttribute("error", "Missing hire request id!");
            response.sendRedirect("AdminViewAllHireRequest");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(hid);
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("error", "Invalid hire request id!");
            response.sendRedirect("AdminViewAllHireRequest");
            return;
        }

        MenteeDAO dao = new MenteeDAO();
        HireRequest hr = dao.getHireRequestbyid(id);

        if (hr != null) {
            dao.deleteHireRequest(id);
            request.getSession().setAttribute("msg", "Delete success!");
        } else {
            request.getSession().setAttribute("error", "Hire request not found!");
        }

        response.sendRedirect("AdminViewAllHireRequest");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
