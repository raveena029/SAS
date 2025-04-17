package dao;

import model.Product;
import java.sql.*;
import java.util.*;

public class PromotionDAO {
    public static Map<Integer, Double> getPromotionsForProducts(List<Product> cart) {
        Map<Integer, Double> promoMap = new HashMap<>();
        try {
            Connection con = DBConnection.getConnection();
            for (Product p : cart) {
                PreparedStatement ps = con.prepareStatement(
                    "SELECT discount_percent FROM promotions WHERE product_id = ? AND expiry_date >= CURDATE()"
                );
                ps.setInt(1, p.getProductId());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    promoMap.put(p.getProductId(), rs.getDouble("discount_percent"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return promoMap;
    }
}
