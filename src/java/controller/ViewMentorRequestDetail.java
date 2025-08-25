/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.*;
import entity.*;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Asus TUF
 */
public class ViewMentorRequestDetail extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            // Ensure mentor is logged in
            if (request.getSession().getAttribute("account") == null
                    || request.getSession().getAttribute("getmentor") == null) {
                response.sendRedirect("views/Login.jsp");
                return;
            }

            String reid = request.getParameter("reid");
            String mentorid = request.getParameter("mentorid");
            if (reid == null || mentorid == null) {
                response.sendRedirect("ViewTop3Mentor");
                return;
            }

            int requestId = Integer.parseInt(reid);
            int mentorId = Integer.parseInt(mentorid);

            MenteeDAO menteeDAO = new MenteeDAO();
            MentorDAO mentorDAO = new MentorDAO();

            CodeRequest codeRequest = menteeDAO.getAReqeustByID(requestId);
            List<Skill> skills = menteeDAO.getSkillARequest(requestId);
            Answer answer = mentorDAO.getAnswer(mentorId, requestId);

            request.setAttribute("coderequest", codeRequest);
            request.setAttribute("skill", skills);
            request.setAttribute("answer", answer);

            request.getRequestDispatcher("views/MentorRequestDetail.jsp").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.getWriter().println("Error: " + ex.getMessage());
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
