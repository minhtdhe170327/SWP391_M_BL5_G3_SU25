package controller;

import dao.MenteeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCodeRequest extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            MenteeDAO dao = new MenteeDAO();
            dao.deleteCodeRequest(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("AdminViewAllRequest");
    }
}
