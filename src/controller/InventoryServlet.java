package controller;

import dao.InventoryDAO;
import model.InventoryItem;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class InventoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<InventoryItem> inventory = InventoryDAO.getInventoryStatus();
        req.setAttribute("inventory", inventory);
        RequestDispatcher rd = req.getRequestDispatcher("jsp/manage_inventory.jsp");
        rd.forward(req, resp);
    }
}
