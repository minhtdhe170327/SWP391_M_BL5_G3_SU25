//package controller;
//
//import dao.MenteeDAO;
//import entity.HireRequest;
//import java.io.IOException;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//public class DeleteHireRequest extends HttpServlet {
//
//   @Override
//protected void doPost(HttpServletRequest request, HttpServletResponse response)
//        throws ServletException, IOException {
//    String hid = request.getParameter("hireid");
//    int id = Integer.parseInt(hid);
//
//    MenteeDAO dao = new MenteeDAO();
//    HireRequest hr = dao.getHireRequestbyid(id); // entity gốc từ bảng hirequest
//
//    if (hr != null && hr.getStatusid() == 1) { // 1 = not yet
//        dao.deleteHireRequest(id);
//        request.getSession().setAttribute("msg", "Delete success!");
//    } else {
//        request.getSession().setAttribute("error", "Only requests with status 'not yet' can be deleted!");
//    }
//
//    // Redirect về list
//    response.sendRedirect("ViewAllHireRequest?menteeid=" + hr.getMenteeid());
//}
//}
