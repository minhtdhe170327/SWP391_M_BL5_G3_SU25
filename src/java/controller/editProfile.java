/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import dao.MenteeDAO;
import dao.MentorDAO;
import entity.Account;
import entity.Mentor;
import entity.Mentee;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author 84395
 */
@WebServlet(name = "editProfile", urlPatterns = {"/editProfile"})
public class editProfile extends HttpServlet {

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
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("account") == null) {
            // Nếu chưa đăng nhập thì quay về Login
            response.sendRedirect("LoginController");
            return;
        }

        Account acc = (Account) session.getAttribute("account");
        Mentor mentor = (Mentor) session.getAttribute("getmentor");
        Mentee mentee = (Mentee) session.getAttribute("getmentee");

        // Đưa vào request để JSP hiển thị
        request.setAttribute("account", acc);
        if (mentor != null) {
            request.setAttribute("getmentor", mentor);
        } else if (mentee != null) {
            request.setAttribute("getmentee", mentee);
        }

        request.getRequestDispatcher("views/editProfile.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // đảm bảo nhận dữ liệu tiếng Việt
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");

        if (acc == null) {
            response.sendRedirect("ViewTop3Mentor");
            return;
        }

        AccountDAO accountDAO = new AccountDAO();
        MenteeDAO menteeDAO = new MenteeDAO();
        MentorDAO mentorDAO = new MentorDAO();

        try {
            int role = acc.getRoleid();

            if (role == 1) { // Mentee
                Mentee mentee = (Mentee) session.getAttribute("getmentee");
                if (mentee != null) {
                    String firstname = request.getParameter("firstname");
                    String lastname = request.getParameter("lastname");
                    String phone = request.getParameter("phone");
                    String address = request.getParameter("address");
                    String introduce = request.getParameter("introduce");
                    String sex = request.getParameter("sex");

                    java.sql.Date birthday = null;
                    String birthdayStr = request.getParameter("birthday");
                    if (birthdayStr != null && !birthdayStr.isEmpty()) {
                        Date parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthdayStr);
                        birthday = new java.sql.Date(parsedDate.getTime());
                    }

//                    System.out.println("Updating mentee: " + name + ", phone: " + phone);

                    // Gọi phương thức update mới với tham số
                    menteeDAO.updateMenteeProfile(mentee.getId(), firstname,lastname, sex, address, phone, birthday, introduce);

                    // Cập nhật lại session
                    mentee.setFirstname(firstname);
                    mentee.setLastname(lastname);
                    mentee.setPhone(phone);
                    mentee.setAddress(address);
                    mentee.setSex(sex);
                    mentee.setBirthday(birthday);
                    mentee.setIntroduce(introduce);
                    session.setAttribute("getmentee", mentee);

                    System.out.println("Update completed for mentee id: " + mentee.getId());
                }

            } else if (role == 2) { // Mentor
                Mentor mentor = (Mentor) session.getAttribute("getmentor");
                if (mentor != null) {
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

                    String sex = request.getParameter("sex");
                    mentor.setSex(sex);

                    String costStr = request.getParameter("cost");
                    float cost = 0;
                    if (costStr != null && !costStr.isEmpty()) {
                        cost = Float.parseFloat(costStr);
                        mentor.setCost(cost);
                    }

                    // Gọi hàm update mới
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

                    session.setAttribute("getmentor", mentor);
                }

            }
//            else if (role == 3) { // Admin
//                acc.setAccountname(request.getParameter("accountname"));
//                accountDAO.updateAccount(acc);
//                session.setAttribute("account", acc);
//            }

            // Sau khi lưu xong, quay về profile
            response.sendRedirect("viewProfile");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("err", "Đã xảy ra lỗi khi cập nhật thông tin. Vui lòng thử lại.");
            request.getRequestDispatcher("viewProfile").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Hiển thị hồ sơ người dùng (Mentor/Mentee)";
    }// </editor-fold>

}
