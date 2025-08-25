/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.*;
import entity.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;

public class ViewAllRequest extends HttpServlet {
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
        String index = request.getParameter("index");
        String menteeid = request.getParameter("menteeid");
        String mentorid = request.getParameter("mentorid");
        
        // Handle admin viewing all requests
        /* if (menteeid == null && mentorid == null) {
            if (index == null) {
                index = "1";
            }
            int indexp = Integer.parseInt(index);

            MenteeDAO dao = new MenteeDAO();
            List<CodeRequest> list = dao.getAllRequests(indexp);
            
            int total = dao.getTotalAllRequests();
            int end = total / 4;
            if (total % 4 != 0) {
                end++;
            }

            request.setAttribute("endpage", end);
            request.setAttribute("coderequest", list);
            request.getRequestDispatcher("views/MyRequest.jsp").forward(request, response);
            return;
        } */
        
        if (menteeid != null) {
            int id = Integer.parseInt(menteeid);
            if (index == null) {
                index = "1";
            }
            int indexp = Integer.parseInt(index);

            MenteeDAO dao = new MenteeDAO();
            MentorDAO mdao = new MentorDAO();
            List<Mentor> listmentor = mdao.getAllMentor();
            List<CodeRequest> list = dao.pagingMenteeRequest(id, indexp);

            int total = dao.getTotalMenteeRequest(id);
            int end = total / 4;
            if (total % 4 != 0) {
                end++;
            }

            request.setAttribute("endpage", end);
            request.setAttribute("tag", indexp);
            request.setAttribute("coderequest", list);
            request.getSession().setAttribute("listallmentor", listmentor);
            request.getRequestDispatcher("views/MyRequest.jsp").forward(request, response);
            return;
        }

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
