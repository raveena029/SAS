package controller;

import dao.UserDAO;
import model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = UserDAO.authenticate(username, password);

        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            if ("manager".equals(user.getRole())) {
                resp.sendRedirect("jsp/manager_dashboard.jsp");
            } else {
                resp.sendRedirect("jsp/employee_dashboard.jsp");
            }
        } else {
            req.setAttribute("error", "Invalid username or password!");
            RequestDispatcher rd = req.getRequestDispatcher("jsp/login.jsp");
            rd.forward(req, resp);
        }
    }
}
