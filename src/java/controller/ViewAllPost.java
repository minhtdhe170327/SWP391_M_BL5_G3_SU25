    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
     */

    package controller;

    import dao.PostDAO;
    import java.util.List;
    import entity.Post;
    import java.io.IOException;
    import java.io.PrintWriter;
    import jakarta.servlet.ServletException;
    import jakarta.servlet.http.HttpServlet;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;

    /**
     *
     * @author Administrator
     */

    public class ViewAllPost extends HttpServlet {

        /** 
         * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
         * @param request servlet request
         * @param response servlet response
         * @throws ServletException if a servlet-specific error occurs
         * @throws IOException if an I/O error occurs
         */
        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String keyword = request.getParameter("keyword"); // Lấy từ form search
            String pageStr = request.getParameter("page");
            int page = 1;
            if (pageStr != null && !pageStr.isEmpty()) {
                page = Integer.parseInt(pageStr);
            }

            int pageSize = 6;
            PostDAO postDAO = new PostDAO();
            List<Post> posts;
            int totalPosts;
            int totalPages;

            if (keyword != null && !keyword.trim().isEmpty()) {
                // Nếu có keyword thì search
                posts = postDAO.searchPosts(keyword); // hàm bạn viết trong DAO
                totalPosts = posts.size();
                totalPages = 1; // search thì thường không cần phân trang, hoặc bạn tự cắt tiếp
                request.setAttribute("keyword", keyword);
            } else {
                // Nếu không search thì lấy tất cả có phân trang
                posts = postDAO.getPostsWithPagination(page, pageSize);
                totalPosts = postDAO.getTotalPosts();
                totalPages = (int) Math.ceil((double) totalPosts / pageSize);
            }

            request.setAttribute("posts", posts);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);

            request.getRequestDispatcher("views/ViewAllPost.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error loading posts: " + e.getMessage());
            request.getRequestDispatcher("views/ViewAllPost.jsp").forward(request, response);
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
            processRequest(request, response);
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
            processRequest(request, response);
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
