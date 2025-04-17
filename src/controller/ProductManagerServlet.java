package controller;

import dao.ProductDAO;
import model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ProductManagerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = ProductDAO.getAllProducts();
        req.setAttribute("products", products);
        RequestDispatcher rd = req.getRequestDispatcher("jsp/manage_products.jsp");
        rd.forward(req, resp);
    }
}
