package controller;

import dao.ProductDAO;
import model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class SearchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getParameter("query");

        List<Product> results = ProductDAO.searchProduct(query);
        req.setAttribute("results", results);

        RequestDispatcher rd = req.getRequestDispatcher("jsp/search.jsp");
        rd.forward(req, resp);
    }
}
