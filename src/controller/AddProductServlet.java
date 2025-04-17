package controller;

import dao.ProductDAO;
import model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AddProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product p = new Product();
        p.setName(req.getParameter("name"));
        p.setDescription(req.getParameter("description"));
        p.setCategory(req.getParameter("category"));
        p.setPrice(Double.parseDouble(req.getParameter("price")));
        p.setTaxRate(Double.parseDouble(req.getParameter("tax")));
        ProductDAO.addProduct(p);
        resp.sendRedirect("ProductManagerServlet");
    }
}
