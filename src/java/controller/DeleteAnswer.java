package controller;

import dao.MenteeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="DeleteAnswer", urlPatterns={"/DeleteAnswer"})
public class DeleteAnswer extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr != null) {
            int id = Integer.parseInt(idStr);
            MenteeDAO dao = new MenteeDAO();
            dao.deleteAnswer(id);
        }
        response.sendRedirect("AdminViewAllAnswer");
    }
}
