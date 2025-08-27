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
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Asus TUF
 */
public class UpdateAnswer extends HttpServlet {
   
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
         String mentorid = request.getParameter("mentorid");
        String answerid = request.getParameter("answerid");
        String coderequestid = request.getParameter("coderequestid");
		// Validate and safely parse IDs to avoid NumberFormatException on empty input
        if (answerid == null || answerid.trim().isEmpty() || mentorid == null || mentorid.trim().isEmpty() || coderequestid == null || coderequestid.trim().isEmpty()) {
            request.setAttribute("error", "Missing required identifiers to update the answer.");
            request.getRequestDispatcher("views/UpdateAnswer.jsp").forward(request, response);
            return;
        }
        int aid;
        int mid;
        int rid;
        try {
            aid = Integer.parseInt(answerid.trim());
            mid = Integer.parseInt(mentorid.trim());
            rid = Integer.parseInt(coderequestid.trim());
        } catch (NumberFormatException ex) {
            request.setAttribute("error", "Invalid identifier format.");
            request.getRequestDispatcher("views/UpdateAnswer.jsp").forward(request, response);
            return;
        }
        String content = request.getParameter("content");
        MentorDAO dao=new MentorDAO();
            dao.updateAnswer(aid,content );
            // Use redirect so the target controller receives the query parameters and loads fresh data
            response.sendRedirect(request.getContextPath()+"/ViewMentorRequestDetail?reid=" + rid+"&mentorid="+mid);
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
