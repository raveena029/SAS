package model;

public class InventoryItem {
    private int productId;
    private String productName;
    private int quantity;
    private int threshold;

    public int getProductId() { return productId; }
    public void setProductId(int id) { this.productId = id; }

    public String getProductName() { return productName; }
    public void setProductName(String n) { this.productName = n; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int q) { this.quantity = q; }

    public int getThreshold() { return threshold; }
    public void setThreshold(int t) { this.threshold = t; }
}
