package controller;

import dao.MenteeDAO;
import entity.AnswerList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name="AdminViewAllAnswer", urlPatterns={"/AdminViewAllAnswer"})
public class AdminViewAllAnswer extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String index = request.getParameter("index");
        if (index == null) index = "1";
        int indexp = Integer.parseInt(index);

        MenteeDAO dao = new MenteeDAO();
        List<AnswerList> list = dao.getAllAnswersWithNames(indexp);

        int total = dao.getTotalAllAnswers();
        int end = total / 4;
        if (total % 4 != 0) end++;

        request.setAttribute("endpage", end);
        request.setAttribute("tag", indexp);
        request.setAttribute("answerList", list);

        request.getRequestDispatcher("views/AdminViewAllAnswer.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
