package controller;

import dao.MentorDAO;
import entity.Mentor;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet; // 1. Import annotation
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet này dùng Annotation để xem chi tiết một Mentor
 */
// 2. Thêm Annotation để khai báo URL
@WebServlet(name = "ViewMentorDetail", urlPatterns = {"/ViewMentorDetail"})
public class ViewMentorDetail extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            // Lấy mentorid từ URL
            String id = request.getParameter("mentorid");
            int mentorid = Integer.parseInt(id);

            // Gọi DAO để lấy thông tin chi tiết của Mentor
            MentorDAO dao = new MentorDAO();
            Mentor mentor = dao.getMentorDetail(mentorid);

            // 3. Tối ưu: Dùng request.setAttribute thay vì session
            request.setAttribute("Mentor", mentor);

            // Chuyển tiếp đến trang JSP
            request.getRequestDispatcher("views/MentorDetail.jsp").forward(request, response);
            
        } catch (NumberFormatException e) {
            // Xử lý trường hợp mentorid không phải là số
            response.sendRedirect("ViewAllMentor"); // Chuyển hướng về trang danh sách
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet to view a specific mentor's details";
    }
}