package controller;

import dao.AccountDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/AdminAddAccount")
public class AdminAddAccount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountDAO dao = new AccountDAO();
        request.setAttribute("roleList", dao.getRole());
        request.getRequestDispatcher("views/AdminAddAccount.jsp").forward(request, response);
    }

    @Override
    
    
    
    //post
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String accountname = request.getParameter("accountname");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            int roleid = Integer.parseInt(request.getParameter("roleid"));

            if (accountname == null || accountname.trim().isEmpty() ||
                email == null || email.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
                request.setAttribute("error", "Please fill in all required fields");
                doGet(request, response);
                return;
            }

            AccountDAO dao = new AccountDAO();
            if (dao.checkEmail(email) != null) {
                request.setAttribute("error", "Email already exists");
                doGet(request, response);
                return;
            }

            int accountid = dao.insertAccountAndGetId(accountname, password, roleid, email);
            if (accountid > 0) {
                if (roleid == 2) { // Mentor
    dao.insertMentor(
        accountid,
        "default",   // firstname
        "default",   // lastname
        "default",   // address
        "default",   // phone
        null,        // birthday
        "M",         // sex
        "default",   // introduce
        "default",   // achievement
        "mentor1.jpg", // avatar
        0f           // costHire
    );
} else if (roleid == 1) { // Mentee
    dao.insertMentee(
        accountid,
        "default",   // firstname
        "default",   // lastname
        "default",   // address
        "default",   // phone
        null,        // birthday
        "M",         // sex
        "mentor1.jpg", // avatar
        "default"    // introduce
    );
}
                response.sendRedirect(request.getContextPath() + "/ViewAllAccount");
            } else {
                request.setAttribute("error", "Failed to create account");
                doGet(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while creating account");
            doGet(request, response);
        }
    }
} 