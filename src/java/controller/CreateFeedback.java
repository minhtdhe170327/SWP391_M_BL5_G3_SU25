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
        if (menteeid == null && star == null && comment == null) {
            request.setAttribute("answerid", answeid);
            request.getRequestDispatcher("views/CreateFeedback.jsp").forward(request, response);
        } else {
            if (star == null) {
                request.setAttribute("error", "you don't choose the star");
                request.getRequestDispatcher("views/CreateFeedback.jsp").forward(request, response);
            } else {
                int idmentee = Integer.parseInt(menteeid);
                int stars = Integer.parseInt(star);
                int answer = Integer.parseInt(answeid);
                dao.createFeedback(idmentee, stars, comment);
                Feedback f = dao.getfeedbackadd();
                dao.createFeedbackAnswer(f.getId(), answer);
                request.setAttribute("menteeid", menteeid);
                request.setAttribute("coderequestid", coderequstid);
                request.setAttribute("mentorid", mentorid);
                request.setAttribute("done", "Create success");
                request.getRequestDispatcher("views/DetailAnswer.jsp").forward(request, response);
            }
        }
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
