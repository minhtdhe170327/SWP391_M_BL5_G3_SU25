/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import entity.Account;
import entity.Mentor;
import entity.Mentee;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author 84395
 */
@WebServlet(name="viewProfile", urlPatterns={"/viewProfile"})
public class viewProfile extends HttpServlet {

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
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("account") == null) {
            // Nếu chưa đăng nhập thì quay về Login
            response.sendRedirect("ViewTop3Mentor");
            return;
        }

        Account acc = (Account) session.getAttribute("account");
        Mentor mentor = (Mentor) session.getAttribute("getmentor");
        Mentee mentee = (Mentee) session.getAttribute("getmentee");

        // Đưa vào request để JSP hiển thị
        request.setAttribute("account", acc);
        if (mentor != null) {
            request.setAttribute("getmentor", mentor);
        } else if (mentee != null) {
            request.setAttribute("getmentee", mentee);
        }

        request.getRequestDispatcher("views/userProfile.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        return "Hiển thị hồ sơ người dùng (Mentor/Mentee)";
    }// </editor-fold>

}
