package controller;

import dao.AccountDAO;
import dao.MentorDAO;
import entity.Account;
import entity.Mentor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "AdminEditMentorAccount", urlPatterns = {"/AdminEditMentorAccount"})
public class AdminEditMentorAccount extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            AccountDAO accDao = new AccountDAO();
            MentorDAO mentorDao = new MentorDAO();

            Account editAccount = accDao.getAccountByid(id);
            if (editAccount == null || editAccount.getRoleid() != 2) {
                response.sendRedirect("ViewMentorAccount");
                return;
            }

            Mentor mentor = mentorDao.getMentorbyAccID(id);

            request.setAttribute("editAccount", editAccount);
            request.setAttribute("editMentor", mentor);

            request.getRequestDispatcher("views/AdminEditMentorAccount.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("ViewMentorAccount");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        MentorDAO mentorDAO = new MentorDAO();
        AccountDAO accountDAO = new AccountDAO();

        try {
            int accountId = Integer.parseInt(request.getParameter("accountId"));
            Mentor mentor = mentorDAO.getMentorbyAccID(accountId);
            Account account = accountDAO.getAccountByid(accountId);

            if (mentor == null || account == null) {
                response.sendRedirect("ViewMentorAccount");
                return;
            }

            // === Update Mentor Info ===
            mentor.setFirstname(request.getParameter("firstname"));
            mentor.setLastname(request.getParameter("lastname"));
            mentor.setPhone(request.getParameter("phone"));
            mentor.setAddress(request.getParameter("address"));
            mentor.setIntroduce(request.getParameter("introduce"));
            mentor.setAchievement(request.getParameter("achievement"));

            String birthdayStr = request.getParameter("birthday");
            if (birthdayStr != null && !birthdayStr.isEmpty()) {
                Date birthday = new SimpleDateFormat("yyyy-MM-dd").parse(birthdayStr);
                mentor.setBirthday(new java.sql.Date(birthday.getTime()));
            }

            mentor.setSex(request.getParameter("sex"));

            String costStr = request.getParameter("cost");
            if (costStr != null && !costStr.isEmpty()) {
                mentor.setCost(Float.parseFloat(costStr));
            }

            mentorDAO.updateMentorProfile(
                mentor.getId(),
                mentor.getFirstname(),
                mentor.getLastname(),
                mentor.getSex(),
                mentor.getAddress(),
                mentor.getPhone(),
                (java.sql.Date) mentor.getBirthday(),
                mentor.getIntroduce(),
                mentor.getAchievement(),
                mentor.getCost()
            );

            // === Update Password nếu có nhập mới ===
            String newPassword = request.getParameter("password");
            if (newPassword != null && !newPassword.trim().isEmpty()) {
                account.setPassword(newPassword); // ⚠️ nếu cần thì mã hóa trước khi lưu
                accountDAO.updateAccount(account);
            }

            // Redirect về list mentor
            response.sendRedirect("ViewMentorAccount");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("err", "Có lỗi xảy ra khi cập nhật Mentor.");
            request.getRequestDispatcher("views/AdminEditMentorAccount.jsp").forward(request, response);
        }
    }
}
