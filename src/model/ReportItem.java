package model;

public class ReportItem {
    private String productName;
    private int quantitySold;
    private double totalRevenue;

    public String getProductName() { return productName; }
    public void setProductName(String n) { this.productName = n; }

    public int getQuantitySold() { return quantitySold; }
    public void setQuantitySold(int q) { this.quantitySold = q; }

    public double getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(double r) { this.totalRevenue = r; }
}
