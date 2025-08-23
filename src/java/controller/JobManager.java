package controller;

import dao.*;
import entity.Job;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "JobManager", urlPatterns = {"/JobManager"})
public class JobManager extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountDAO dao = new AccountDAO();
        String search = request.getParameter("search");
        String statusFilter = request.getParameter("statusFilter");
        int page = 1;
        int pageSize = 5;
if (statusFilter == null) statusFilter = "all";
        if (request.getParameter("page") != null) {
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException ignored) {}
        }

        List<Job> jobList = dao.pagingJob(search,statusFilter, page, pageSize);
        int totalJob = dao.countJob(search,statusFilter);
        int totalPage = (int) Math.ceil((double) totalJob / pageSize);

        request.setAttribute("jobList", jobList);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPage", totalPage);
         request.setAttribute("statusFilter", statusFilter);
        request.setAttribute("search", search == null ? "" : search);
       
        request.getRequestDispatcher("views/JobManager.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        AccountDAO dao = new AccountDAO();

        if ("add".equals(action)) {
    String jobName = request.getParameter("jobName");
    String description = request.getParameter("description");
    String status = request.getParameter("status");
    dao.addJobAdmin(jobName, description, status);

} else if ("update".equals(action)) {
    int jobId = Integer.parseInt(request.getParameter("jobId"));
    String jobName = request.getParameter("jobName");
    String description = request.getParameter("description");
    String status = request.getParameter("status");
    dao.updateJobAdmin(jobId, jobName, description, status);
} else if ("delete".equals(action)) {
            int jobId = Integer.parseInt(request.getParameter("jobId"));
            dao.deleteJobAdmin(jobId);
        }

        response.sendRedirect("JobManager");
    }
}
