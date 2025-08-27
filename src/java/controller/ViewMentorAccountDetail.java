package controller;

import dao.MentorDAO;
import entity.Mentor;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class ViewMentorAccountDetail extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idRaw = request.getParameter("id");
        if (idRaw != null) {
            try {
                int accountId = Integer.parseInt(idRaw);

                MentorDAO dao = new MentorDAO();
                // lấy mentor theo accountid
                Mentor mentor = dao.getMentorDetailByAccountId(accountId);

                request.setAttribute("mentor", mentor);

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        request.getRequestDispatcher("/views/ViewMentorAccountDetail.jsp").forward(request, response);
    }
}
