package model;

public class Product {
    private int productId;
    private String name;
    private double price;
    private double taxRate;
    private int quantity;

    // Getters & Setters
    public int getProductId() { return productId; }
    public void setProductId(int id) { this.productId = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public double getTaxRate() { return taxRate; }
    public void setTaxRate(double tax) { this.taxRate = tax; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int q) { this.quantity = q; }
    private String category;
    private String section;

    public String getCategory() { return category; }
    public void setCategory(String c) { this.category = c; }

    public String getSection() { return section; }
    public void setSection(String s) { this.section = s; }

}
