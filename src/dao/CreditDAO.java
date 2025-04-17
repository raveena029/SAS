package dao;

import java.sql.*;

public class CreditDAO {
    public static double getAvailableCredit(int userId) {
        double credit = 0;
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "SELECT SUM(amount) FROM purchase_credits WHERE user_id = ? AND expiry_date >= CURDATE()"
            );
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                credit = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return credit;
    }

    public static void consumeCredit(int userId, double amountToUse) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "UPDATE purchase_credits SET amount = amount - ? WHERE user_id = ? AND expiry_date >= CURDATE() LIMIT 1"
            );
            ps.setDouble(1, amountToUse);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
