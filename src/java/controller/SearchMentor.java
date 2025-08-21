/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.MentorDAO;
import entity.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author
 */
@WebServlet(name = "SearchMentor", urlPatterns = {"/SearchMentor"})
public class SearchMentor extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mentorname = request.getParameter("name");
        if (mentorname == null) {
            mentorname = ""; // nếu null, set thành rỗng
        }

        String index = request.getParameter("index");
        if (index == null || index.isEmpty()) {
            index = "1";
        }
        int indexp = Integer.parseInt(index);

        MentorDAO dao = new MentorDAO();
        List<Mentor> list;
        int totalMentors; // tổng số mentor dùng để tính trang

        if (mentorname.trim().isEmpty()) {
            totalMentors = dao.getTotalMentor(); // tổng mentor không tìm kiếm
            list = dao.pagingMentor(indexp);     // lấy mentor cho trang hiện tại
        } else {
            list = dao.searchMentor(mentorname, indexp);
            totalMentors = list.size(); // tổng mentor theo tên
// lấy mentor theo trang và tên
        }

// tính số trang cuối (mỗi trang 3 mentor)
        int end = totalMentors / 3;
        if (totalMentors % 3 != 0) {
            end++;
        }

        request.setAttribute("endpage", end);
        request.setAttribute("allMentor", list);
        request.setAttribute("searchtext", mentorname);
        request.setAttribute("currentPage", indexp); // trang hiện tại
        request.getRequestDispatcher("views/MentorList.jsp").forward(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
