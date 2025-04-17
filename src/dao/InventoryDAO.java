package dao;

import model.InventoryItem;
import java.sql.*;
import java.util.*;

public class InventoryDAO {
    public static List<InventoryItem> getInventoryStatus() {
        List<InventoryItem> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(
                "SELECT i.product_id, p.name, i.quantity, i.threshold " +
                "FROM inventory i JOIN products p ON i.product_id = p.product_id"
            );
            while (rs.next()) {
                InventoryItem item = new InventoryItem();
                item.setProductId(rs.getInt("product_id"));
                item.setProductName(rs.getString("name"));
                item.setQuantity(rs.getInt("quantity"));
                item.setThreshold(rs.getInt("threshold"));
                list.add(item);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
}
