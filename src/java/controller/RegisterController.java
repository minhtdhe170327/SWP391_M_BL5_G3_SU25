package controller;

import dao.*;
import util.*;
import java.io.IOException;
import java.sql.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        request.getRequestDispatcher("/views/Register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        // Lấy dữ liệu từ form
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmpassword = request.getParameter("confirmpass");
        String email = request.getParameter("email");
        String role = request.getParameter("role");

        // các field mới
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String sex = request.getParameter("sex");
        String phone = request.getParameter("phone");
        String birthday = request.getParameter("birthday");
        String address = request.getParameter("address");

        // Khởi tạo Validation
        Validation validation = new Validation();
        PasswordUtil passwordUtil = new PasswordUtil();

        // Kiểm tra input
        String errorMsg = validation.validateSignUpInput(username, password, confirmpassword, email);

        if (errorMsg.isEmpty() && 
            (firstname == null || firstname.trim().isEmpty() ||
             lastname == null || lastname.trim().isEmpty())) {
            errorMsg = "Firstname and Lastname are required.";
        }

        if (!errorMsg.isEmpty()) {
            request.setAttribute("errorMsg", errorMsg);
            request.getRequestDispatcher("views/Register.jsp").forward(request, response);
        } else {
            String hashedPassword = passwordUtil.hashPasswordMD5(password);
            if (hashedPassword == null) {
                request.setAttribute("errorMsg", "Error processing password. Please try again.");
                request.getRequestDispatcher("views/Register.jsp").forward(request, response);
                return;
            }

            // xác định roleid
            int roleid = role.equals("Mentor") ? 2 : 1;
            AccountDAO dao = new AccountDAO();
            int accountid = dao.insertAccountAndGetId(username, hashedPassword, roleid, email);

            if (accountid > 0) {
                if (roleid == 2) { // Mentor
                    dao.insertMentor(accountid, firstname, lastname, address, phone,
                            (birthday != null && !birthday.isEmpty()) ? Date.valueOf(birthday) : null,
                            sex, "default", "default", "mentor1.jpg", 0f);
                } else if (roleid == 1) { // Mentee
                    dao.insertMentee(accountid, firstname, lastname, address, phone,
                            (birthday != null && !birthday.isEmpty()) ? Date.valueOf(birthday) : null,
                            sex, "mentee1.jpg", "Xin chào, tôi là mentee mới!");
                }

                request.setAttribute("msg", "success");
                request.getRequestDispatcher("/views/Login.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMsg", "Registration failed. Please try again.");
                request.getRequestDispatcher("views/Register.jsp").forward(request, response);
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Register new account with personal info";
    }

}
