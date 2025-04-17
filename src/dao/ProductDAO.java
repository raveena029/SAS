package dao;

import model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public static Product getProductById(int productId) {
        Product product = null;
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM products WHERE product_id = ?");
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setTaxRate(rs.getDouble("tax_rate"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }
    public static List<Product> searchProduct(String query) {
        List<Product> products = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "SELECT p.*, s.section_name FROM products p " +
                "LEFT JOIN store_layout s ON p.product_id = s.product_id " +
                "WHERE p.name LIKE ? OR p.product_id = ?"
            );
            ps.setString(1, "%" + query + "%");

            try {
                ps.setInt(2, Integer.parseInt(query));
            } catch (NumberFormatException e) {
                ps.setInt(2, -1); // invalid ID fallback
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt("product_id"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));
                p.setCategory(rs.getString("category"));
                p.setSection(rs.getString("section_name"));
                products.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public static List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM products");
            while (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt("product_id"));
                p.setName(rs.getString("name"));
                p.setCategory(rs.getString("category"));
                p.setPrice(rs.getDouble("price"));
                p.setTaxRate(rs.getDouble("tax_rate"));
                list.add(p);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
    
    public static void addProduct(Product p) {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO products (name, description, category, price, tax_rate) VALUES (?, ?, ?, ?, ?)"
            );
            ps.setString(1, p.getName());
            ps.setString(2, p.getDescription());
            ps.setString(3, p.getCategory());
            ps.setDouble(4, p.getPrice());
            ps.setDouble(5, p.getTaxRate());
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
    

    
}

