/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.*;
import entity.*;
import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.PasswordUtil;


/**
 *
 * @author Administrator
 */
public class ChangePass extends HttpServlet {
   
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
            out.println("<title>Servlet ChangePass</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePass at " + request.getContextPath () + "</h1>");
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
        request.getRequestDispatcher("/views/ChangePassword.jsp").forward(request, response);
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
    Account account = (Account) request.getSession().getAttribute("account");
    if (account == null) {
       
        return;
    }

    String oldpassword = request.getParameter("oldpassword");
    String newpassword = request.getParameter("newpassword");
    String confirmnewpassword = request.getParameter("confirmnewpassword");

    AccountDAO dao = new AccountDAO();
    PasswordUtil passUtil = new PasswordUtil();

    // hash old password để so sánh
    String oldPassHash = passUtil.hashPasswordMD5(oldpassword);

    // kiểm tra mật khẩu cũ có đúng không (so với mật khẩu trong session hoặc DB)
    if (!account.getPassword().equals(oldPassHash)) {
        request.setAttribute("error", "Old password is incorrect!");
        request.getRequestDispatcher("views/ChangePassword.jsp").forward(request, response);
        return;
    }

    // kiểm tra new password và confirm
    if (!newpassword.equals(confirmnewpassword)) {
        request.setAttribute("error", "New password and confirm password do not match!");
        request.getRequestDispatcher("views/ChangePassword.jsp").forward(request, response);
        return;
    }

    // hash new password
    String newPassHash = passUtil.hashPasswordMD5(newpassword);

    // update DB
    dao.changePassword(account.getId(), newPassHash);

    // cập nhật lại account trong session (tránh lỗi lần sau check mật khẩu cũ)
    account.setPassword(newPassHash);
    request.getSession().setAttribute("account", account);

    request.setAttribute("success", "Password changed successfully!");
    request.getRequestDispatcher("views/ChangePassword.jsp").forward(request, response);
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
