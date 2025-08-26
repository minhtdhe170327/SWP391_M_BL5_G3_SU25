package controller;

import dao.*;
import entity.*;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CreateHireRequest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Chỉ hiển thị form lần đầu, không kiểm tra lỗi
        request.getRequestDispatcher("views/CreateHireRequest.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String menteeid = request.getParameter("menteeid");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String mentorid = request.getParameter("choosementor");

        // Nếu chưa chọn mentor
        if (mentorid == null || mentorid.trim().isEmpty()) {
            request.setAttribute("error", "You forgot to choose mentor");
            request.getRequestDispatcher("views/CreateHireRequest.jsp").forward(request, response);
            return;
        }

        MenteeDAO dao = new MenteeDAO();
        int id = Integer.parseInt(menteeid);
        int idmentor = Integer.parseInt(mentorid);

        // Check nếu mentee đã thuê mentor này rồi
        List<HireRelationship> rela = dao.getHireRelationship();
        for (HireRelationship h : rela) {
            if (h.getMenteeid() == id && h.getMentorid() == idmentor) {
                request.setAttribute("error", "You are already hiring this mentor");
                request.getRequestDispatcher("views/CreateHireRequest.jsp").forward(request, response);
                return;
            }
        }

        // Nếu mọi thứ ok → thêm hire request
        dao.inserHireRequest(id, idmentor, title, content);
        request.setAttribute("done", "Create success");
        request.getRequestDispatcher("views/CreateHireRequest.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet for creating hire request";
    }
}
