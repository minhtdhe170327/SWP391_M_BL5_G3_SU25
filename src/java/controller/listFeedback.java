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
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;

/**
 *
 * @author Asus TUF
 */
@WebServlet(name = "listFeedback", urlPatterns = {"/listFeedback"})
public class listFeedback extends HttpServlet {

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

        try {
            // Check if user is logged in and is a mentor
            if (request.getSession().getAttribute("account") == null
                    && request.getSession().getAttribute("getmentor") == null
                    && request.getSession().getAttribute("getmentee") == null) {
                response.sendRedirect("ViewTop3Mentor");
                return;
            }
            String fbid = null;
            fbid = request.getParameter("fbid");
            MentorDAO mdao = new MentorDAO();
            List<Feedback> list = new ArrayList<>();
            List<Mentee> menteelist1 = mdao.getAllMentee();
            List<Mentor> mentorlist1 = mdao.getAllMentor();
            int id = Integer.parseInt(fbid);
            if (fbid == null) {
                response.sendRedirect("ViewTop3Mentor");
                return;
            }
            if (request.getSession().getAttribute("getmentee") != null) {
                list = mdao.getTotalFeedback(id);

            }
            if (request.getSession().getAttribute("getmentor") != null) {
                list = mdao.pagingFeedBack(id);

            }
//            String index = request.getParameter("index");

//            if (index == null) {
//                index = "1";
//            }
//            int indexp = Integer.parseInt(index);
//
//            List<Feedback> list = mdao.pagingFeedBack(id, indexp);
//            int total = mdao.getTotalFeedback(id);
//            int end = total / 4;
//            if (total % 4 != 0) {
//                end++;
//            }
//            request.setAttribute("endpage", end);
//            request.setAttribute("tag", indexp);
            request.setAttribute("menteelist1", menteelist1);
            request.setAttribute("mentorlist1", mentorlist1);
            request.setAttribute("fbid", fbid);
            request.setAttribute("listfeedback", list);

            request.getRequestDispatcher("views/listfeedback.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
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
