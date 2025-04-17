package controller;

import dao.ReportDAO;
import model.ReportItem;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ReportServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");

        List<ReportItem> report = ReportDAO.generateReport(type);
        double totalSales = report.stream().mapToDouble(ReportItem::getTotalRevenue).sum();
        double totalTax = ReportDAO.getTotalTax(type);

        req.setAttribute("reportData", report);
        req.setAttribute("totalSales", totalSales);
        req.setAttribute("totalTax", totalTax);
        req.getRequestDispatcher("jsp/report.jsp").forward(req, resp);
    }
}
