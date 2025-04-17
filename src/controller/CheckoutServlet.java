package controller;

import dao.ProductDAO;
import model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

public class CheckoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId = Integer.parseInt(req.getParameter("productId"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));

        Product product = ProductDAO.getProductById(productId);
        if (product != null && quantity > 0) {
            product.setQuantity(quantity);
            HttpSession session = req.getSession();
            List<Product> cart = (List<Product>) session.getAttribute("cart");
            if (cart == null) cart = new ArrayList<>();
            cart.add(product);
            session.setAttribute("cart", cart);
        }

        resp.sendRedirect("jsp/checkout.jsp");
    }
}
