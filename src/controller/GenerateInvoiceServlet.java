// package controller;

// import dao.TransactionDAO;
// import model.*;

// import javax.servlet.*;
// import javax.servlet.http.*;
// import java.io.IOException;
// import java.util.*;


// public class GenerateInvoiceServlet extends HttpServlet {
//     protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//         HttpSession session = req.getSession();
//         List<Product> cart = (List<Product>) session.getAttribute("cart");
//         User user = (User) session.getAttribute("user");

//         if (cart == null || cart.isEmpty()) {
//             resp.sendRedirect("jsp/checkout.jsp");
//             return;
//         }

//         double subtotal = 0, tax = 0;
//         for (Product p : cart) {
//             subtotal += p.getPrice() * p.getQuantity();
//             tax += (p.getPrice() * p.getTaxRate() / 100) * p.getQuantity();
//         }

//         double total = subtotal + tax;

//         int transactionId = TransactionDAO.saveTransaction(user.getUserId(), cart, subtotal, tax, total);
//         session.removeAttribute("cart");

//         req.setAttribute("transactionId", transactionId);
//         req.setAttribute("subtotal", subtotal);
//         req.setAttribute("tax", tax);
//         req.setAttribute("total", total);
//         req.setAttribute("cart", cart);
//         RequestDispatcher rd = req.getRequestDispatcher("jsp/invoice.jsp");
//         rd.forward(req, resp);
//     }
// }

package controller;

import dao.TransactionDAO;
import dao.PromotionDAO;
import dao.CreditDAO;
import model.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

public class GenerateInvoiceServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");

        if (cart == null || cart.isEmpty()) {
            resp.sendRedirect("jsp/checkout.jsp");
            return;
        }

        double subtotal = 0, tax = 0;
        Map<Integer, Double> discounts = PromotionDAO.getPromotionsForProducts(cart);

        for (Product p : cart) {
            double discount = discounts.getOrDefault(p.getProductId(), 0.0);
            double priceAfterDiscount = p.getPrice() * (1 - discount / 100);
            subtotal += priceAfterDiscount * p.getQuantity();
            tax += (priceAfterDiscount * p.getTaxRate() / 100) * p.getQuantity();
        }

        double total = subtotal + tax;

        // Apply available store credit
        double availableCredit = CreditDAO.getAvailableCredit(user.getUserId());
        double creditUsed = Math.min(availableCredit, total);
        total -= creditUsed;
        CreditDAO.consumeCredit(user.getUserId(), creditUsed);

        int transactionId = TransactionDAO.saveTransaction(user.getUserId(), cart, subtotal, tax, total);

        session.removeAttribute("cart");

        req.setAttribute("transactionId", transactionId);
        req.setAttribute("subtotal", subtotal);
        req.setAttribute("tax", tax);
        req.setAttribute("total", total);
        req.setAttribute("creditUsed", creditUsed);
        req.setAttribute("cart", cart);
        req.setAttribute("discountMap", discounts);
        RequestDispatcher rd = req.getRequestDispatcher("jsp/invoice.jsp");
        rd.forward(req, resp);
    }
}
