/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.*;
import entity.*;
import java.sql.Date;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 */
@WebServlet(name = "CreateFeedback", urlPatterns = {"/CreateFeedback"})
public class CreateFeedback extends HttpServlet {

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
        processRequest(request, response);
        String mentorid = request.getParameter("mentorid");
        String menteeid = request.getParameter("menteeid");
        String coderequstid = request.getParameter("coderequestid");
        String answeid = request.getParameter("answerid");
        String star = request.getParameter("star");
        String comment = request.getParameter("comment");
        MenteeDAO dao = new MenteeDAO();
        // Initial load: no star and no comment selected yet -> show form
        if ((star == null || star.trim().isEmpty()) && (comment == null)) {
            request.setAttribute("answerid", answeid);
            request.getRequestDispatcher("views/CreateFeedback.jsp").forward(request, response);
            return;
        }

        // Validation: require star and required ids
        if (star == null || star.trim().isEmpty()) {
            request.setAttribute("error", "you don't choose the star");
            request.setAttribute("answerid", answeid);
            request.getRequestDispatcher("views/CreateFeedback.jsp").forward(request, response);
            return;
        }
        if (menteeid == null || menteeid.trim().isEmpty() || answeid == null || answeid.trim().isEmpty()) {
            request.setAttribute("error", "Missing identifiers to create feedback.");
            request.setAttribute("answerid", answeid);
            request.getRequestDispatcher("views/CreateFeedback.jsp").forward(request, response);
            return;
        }

        int idmentee = Integer.parseInt(menteeid.trim());
        int stars = Integer.parseInt(star.trim());
        int answer = Integer.parseInt(answeid.trim());
        dao.createFeedback(idmentee, stars, comment);
        Feedback f = dao.getfeedbackadd();
        dao.createFeedbackAnswer(f.getId(), answer);
        // Redirect to load fresh data via controller
        response.sendRedirect(request.getContextPath() + "/ViewDetailAnswer?mentorid=" + mentorid
                + "&menteeid=" + menteeid + "&coderequestid=" + coderequstid);
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
