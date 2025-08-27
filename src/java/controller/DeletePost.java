package controller;

import dao.PostDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class DeletePost extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pid = request.getParameter("postId");
        if (pid == null || pid.isEmpty()) {
            pid = request.getParameter("id");
        }

        if (pid == null || pid.isEmpty()) {
            request.getSession().setAttribute("error", "Missing post id!");
            response.sendRedirect("AdminViewAllPost");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(pid);
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("error", "Invalid post id!");
            response.sendRedirect("AdminViewAllPost");
            return;
        }

        PostDAO dao = new PostDAO();
        dao.deletePost(id);
        request.getSession().setAttribute("msg", "Delete success!");
        response.sendRedirect("AdminViewAllPost");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
