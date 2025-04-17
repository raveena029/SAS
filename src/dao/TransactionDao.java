package dao;

import model.Product;
import java.sql.*;
import java.util.*;

public class TransactionDAO {
    public static int saveTransaction(int userId, List<Product> cart, double subtotal, double tax, double total) {
        int transactionId = -1;
        try {
            Connection con = DBConnection.getConnection();
            con.setAutoCommit(false);

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO transactions (user_id, total, tax, net_total) VALUES (?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );
            ps.setInt(1, userId);
            ps.setDouble(2, subtotal);
            ps.setDouble(3, tax);
            ps.setDouble(4, total);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                transactionId = rs.getInt(1);
            }

            PreparedStatement itemStmt = con.prepareStatement(
                "INSERT INTO transaction_items (transaction_id, product_id, quantity, price) VALUES (?, ?, ?, ?)"
            );

            for (Product p : cart) {
                itemStmt.setInt(1, transactionId);
                itemStmt.setInt(2, p.getProductId());
                itemStmt.setInt(3, p.getQuantity());
                itemStmt.setDouble(4, p.getPrice());
                itemStmt.addBatch();

                PreparedStatement invUpdate = con.prepareStatement(
                    "UPDATE inventory SET quantity = quantity - ? WHERE product_id = ?"
                );
                invUpdate.setInt(1, p.getQuantity());
                invUpdate.setInt(2, p.getProductId());
                invUpdate.executeUpdate();
            }

            itemStmt.executeBatch();
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactionId;
    }
}
