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
public class UpdateRequest extends HttpServlet {
   
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateRequest</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateRequest at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String menteeid = request.getParameter("menteeid");
        String requestid = request.getParameter("requestid");
        int rid = Integer.parseInt(requestid);
        String title = request.getParameter("title");
        String deadline = request.getParameter("deadline");
        String content = request.getParameter("content");
        String[] mentorid = request.getParameterValues("choosementor");
        String[] skill = request.getParameterValues("skill");

        MenteeDAO dao = new MenteeDAO();
        if (title == null || content == null || deadline == null) {
            request.setAttribute("error", "you can't set title, content is empty");
            request.getRequestDispatcher("views/UpdateRequest.jsp").forward(request, response);
        } else {
            dao.updatecoderequest(rid, title, deadline, content);
            if (mentorid != null) {
                for (String s : mentorid) {
                    int a = Integer.parseInt(s);
                    if (dao.checkMentorCodeRequest(rid, a) == false) {
                        dao.inserMentorCodeRequest(rid, a);
                    }
                }

            }
            if (skill != null) {
                dao.deletecoderequestskill(rid);
                for (String t : skill) {
                    int b = Integer.parseInt(t);
                    dao.inserCodeRequestSkill(rid, b);
                }

            }
            request.setAttribute("done", "update successful");
            request.getRequestDispatcher("viewss/ViewRequestDetail?reid=" + rid).forward(request, response);
        }
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
