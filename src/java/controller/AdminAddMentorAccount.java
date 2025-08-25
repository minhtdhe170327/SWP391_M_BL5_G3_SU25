package controller;

import dao.AccountDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

//@WebServlet("/AdminAddMentorAccount")
public class AdminAddMentorAccount extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("views/AdminAddMentorAccount.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String accountname = request.getParameter("accountname");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            int roleid = 2; // mentor cố định

            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String birthdayStr = request.getParameter("birthday");
            Date birthday = (birthdayStr != null && !birthdayStr.isEmpty())
                    ? Date.valueOf(birthdayStr) : null;
            String sex = request.getParameter("sex");
            String avatar = request.getParameter("avatar");
            String introduce = request.getParameter("introduce");
            String achievement = request.getParameter("achievement");
            String costHireStr = request.getParameter("costHire");
            float costHire = (costHireStr != null && !costHireStr.isEmpty())
                    ? Float.parseFloat(costHireStr) : 0f;

            if (accountname == null || accountname.trim().isEmpty()
                    || email == null || email.trim().isEmpty()
                    || password == null || password.trim().isEmpty()
                    || firstname == null || firstname.trim().isEmpty()
                    || lastname == null || lastname.trim().isEmpty()) {
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
                dao.insertMentor(
                        accountid,
                        firstname,
                        lastname,
                        address,
                        phone,
                        birthday,
                        sex,
                        introduce,
                        achievement,
                        (avatar != null && !avatar.isEmpty()) ? avatar : "mentor-default.jpg",
                        costHire
                );
                response.sendRedirect(request.getContextPath() + "/ViewMentorAccount");
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
