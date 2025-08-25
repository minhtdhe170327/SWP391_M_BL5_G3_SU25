/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.*;
import entity.*;
import java.util.*;
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
public class ViewMentorRequest extends HttpServlet {
   
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
            // Check if user is logged in and is a mentor
            if (request.getSession().getAttribute("account") == null || 
                request.getSession().getAttribute("getmentor") == null) {
                response.sendRedirect("views/Login.jsp");
                return;
            }
            
            String index = request.getParameter("index");
            String mentorid = request.getParameter("mentorid");
            
            if (mentorid == null) {
                response.sendRedirect("views/Login.jsp");
                return;
            }
            
            int id = Integer.parseInt(mentorid);
            if (index == null) {
                index = "1";
            }
            int indexp = Integer.parseInt(index);

            MentorDAO mdao = new MentorDAO();
            List<CodeRequest> list = mdao.pagingMentorRequest(id, indexp);

            int total = mdao.getTotalMentorRequest(id);
            int end = total / 4;
            if (total % 4 != 0) {
                end++;
            }

            request.setAttribute("endpage", end);
            request.setAttribute("tag", indexp);
            request.setAttribute("coderequest", list);
            
            // Debug logging
            System.out.println("ViewMentorRequest - Mentor ID: " + id);
            System.out.println("ViewMentorRequest - Total requests: " + total);
            System.out.println("ViewMentorRequest - List size: " + (list != null ? list.size() : "null"));
            System.out.println("ViewMentorRequest - End page: " + end);
            System.out.println("ViewMentorRequest - Forwarding to JSP...");
            
            request.getRequestDispatcher("views/MentorRequest.jsp").forward(request, response);
            System.out.println("ViewMentorRequest - Forward completed");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
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
