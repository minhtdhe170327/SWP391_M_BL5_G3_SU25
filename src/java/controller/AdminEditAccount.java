package controller;

import dao.*;
import entity.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class AdminEditAccount extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            AccountDAO accDao = new AccountDAO();
            MenteeDAO menteeDao = new MenteeDAO();
            MentorDAO mentorDao = new MentorDAO();
            Account editAccount = accDao.getAccountByid(id);
            if (editAccount == null) {
                response.sendRedirect("ViewMenteeAccount");
                return;
            }
            request.setAttribute("editAccount", editAccount);
            if (editAccount.getRoleid() == 1) {
                Mentee mentee = menteeDao.getMenteebyAccID(id);
                request.setAttribute("mentee", mentee);
            } else if (editAccount.getRoleid() == 2) {
                Mentor mentor = mentorDao.getMentorbyAccID(id);
                request.setAttribute("mentor", mentor);
            }
            request.getRequestDispatcher("/views/AdminEditAccount.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("ViewMenteeAccount");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String accountname = request.getParameter("accountname");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            int roleid = Integer.parseInt(request.getParameter("roleid"));
            AccountDAO accDao = new AccountDAO();
            MenteeDAO menteeDao = new MenteeDAO();
            MentorDAO mentorDao = new MentorDAO();
            Account acc = accDao.getAccountByid(id);
            if (acc == null) {
                response.sendRedirect("ViewMenteeAccount");
                return;
            }
            if (password != null && !password.trim().isEmpty()) {
                accDao.updateAccountByAdminWithPassword(id, accountname, email, roleid, password);
            } else {
                accDao.updateAccountByAdmin(id, accountname, email, roleid);
            }
            if (roleid == 1) {
                Mentee mentee = menteeDao.getMenteebyAccID(id);
                if (mentee != null) {
                    String firstname = request.getParameter("firstname");
                    String lastname = request.getParameter("lastname");
                    String sex = request.getParameter("sex");
                    String address = request.getParameter("address");
                    String phone = request.getParameter("phone");
                    String birthday = request.getParameter("birthday");
                    String introduce = request.getParameter("introduce");
                    menteeDao.updateMenteeProfile(
                            mentee.getId(),
                            firstname, lastname, sex, address, phone,
                            (birthday != null && !birthday.isEmpty() ? java.sql.Date.valueOf(birthday) : null),
                            introduce
                    );
                }
            }
            if (roleid == 2) {
                Mentor mentor = mentorDao.getMentorbyAccID(id);
                if (mentor != null) {
                    String firstname = request.getParameter("firstname");
                    String lastname = request.getParameter("lastname");
                    String sex = request.getParameter("sex");
                    String address = request.getParameter("address");
                    String phone = request.getParameter("phone");
                    String birthday = request.getParameter("birthday");
                    String introduce = request.getParameter("introduce");
                    String achievement = request.getParameter("achievement");
                    String costStr = request.getParameter("cost");
                    float costHire = 0;
                    try { costHire = Float.parseFloat(costStr); } catch (Exception ex) {}
                    mentorDao.updateMentorProfile(
                            mentor.getId(),
                            firstname, lastname, sex, address, phone,
                            (birthday != null && !birthday.isEmpty() ? java.sql.Date.valueOf(birthday) : null),
                            introduce, achievement, costHire
                    );
                }
            }
            response.sendRedirect("ViewMenteeAccount");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("ViewMenteeAccount");
        }
    }
}
