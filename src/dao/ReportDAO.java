package dao;

import model.ReportItem;
import java.sql.*;
import java.util.*;

public class ReportDAO {
    public static List<ReportItem> generateReport(String type) {
        List<ReportItem> report = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT p.name, SUM(ti.quantity) as qty, SUM(ti.quantity * ti.price) as revenue " +
                           "FROM transaction_items ti " +
                           "JOIN products p ON ti.product_id = p.product_id " +
                           "JOIN transactions t ON ti.transaction_id = t.transaction_id " +
                           "WHERE ";

            if ("month".equals(type)) {
                query += "MONTH(t.date) = MONTH(CURRENT_DATE())";
            } else {
                query += "QUARTER(t.date) = QUARTER(CURRENT_DATE())";
            }

            query += " GROUP BY p.name";

            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ReportItem item = new ReportItem();
                item.setProductName(rs.getString("name"));
                item.setQuantitySold(rs.getInt("qty"));
                item.setTotalRevenue(rs.getDouble("revenue"));
                report.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return report;
    }

    public static double getTotalTax(String type) {
        double tax = 0;
        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT SUM(tax) FROM transactions WHERE ";

            if ("month".equals(type)) {
                query += "MONTH(date) = MONTH(CURRENT_DATE())";
            } else {
                query += "QUARTER(date) = QUARTER(CURRENT_DATE())";
            }

            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tax = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tax;
    }
}
