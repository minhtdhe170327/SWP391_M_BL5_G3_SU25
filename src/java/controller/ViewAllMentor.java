package controller;

import dao.MentorDAO;
import entity.Mentor;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet; // Quan trọng: import annotation
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Servlet này sử dụng Annotation để khai báo URL
 */
// =======================================================
// ANNOTATION ĐƯỢC THÊM VÀO ĐÂY
// =======================================================
@WebServlet(name = "ViewAllMentor", urlPatterns = {"/ViewAllMentor"})
public class ViewAllMentor extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Lấy tham số trang, mặc định là trang 1
        String index = request.getParameter("index");
        if (index == null || index.isEmpty()) {
            index = "1";
        }
        int indexp = Integer.parseInt(index);

        // Gọi DAO để lấy dữ liệu
        MentorDAO dao = new MentorDAO();
        int totalMentors = dao.getTotalMentor(); // Lấy tổng số mentor
        List<Mentor> list = dao.pagingMentor(indexp); // Lấy mentor cho trang hiện tại

        // Tính toán số trang cuối (mỗi trang có 3 mentor)
        int endPage = totalMentors / 3;
        if (totalMentors % 3 != 0) {
            endPage++;
        }

        // Đặt các thuộc tính vào request để gửi cho JSP
        request.setAttribute("endpage", endPage);
        request.setAttribute("allMentor", list);
        request.setAttribute("currentPage", indexp); // Gửi cả trang hiện tại để JSP xử lý 'active'

        // Chuyển tiếp đến trang JSP
        request.getRequestDispatcher("views/MentorList.jsp").forward(request, response);
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
        return "Servlet to view all mentors with pagination";
    }
}