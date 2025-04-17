<!-- <%@ page import="java.util.*, model.Product" %>
<html>
<head><title>Invoice</title></head>
<body>
<h2>Invoice Generated</h2>
<p>Transaction ID: <%= request.getAttribute("transactionId") %></p>
<hr>
<table border="1">
    <tr><th>Product</th><th>Qty</th><th>Price</th><th>Total</th></tr>
    <%
        List<Product> cart = (List<Product>) request.getAttribute("cart");
        for (Product p : cart) {
    %>
        <tr>
            <td><%= p.getName() %></td>
            <td><%= p.getQuantity() %></td>
            <td>₹<%= p.getPrice() %></td>
            <td>₹<%= p.getPrice() * p.getQuantity() %></td>
        </tr>
    <% } %>
</table>
<p>Subtotal: ₹<%= request.getAttribute("subtotal") %></p>
<p>Tax: ₹<%= request.getAttribute("tax") %></p>
<h3>Total: ₹<%= request.getAttribute("total") %></h3>
<a href="checkout.jsp">← Back to Checkout</a>
</body>
</html> -->


<%@ page import="java.util.*, model.Product" %>
<%
    Map<Integer, Double> discountMap = (Map<Integer, Double>) request.getAttribute("discountMap");
%>
<html>
<head><title>Invoice</title></head>
<body>
<h2>Invoice Generated</h2>
<p>Transaction ID: <%= request.getAttribute("transactionId") %></p>
<hr>
<table border="1">
    <tr><th>Product</th><th>Qty</th><th>Price</th><th>Discount</th><th>Total</th></tr>
    <%
        List<Product> cart = (List<Product>) request.getAttribute("cart");
        for (Product p : cart) {
            double discount = discountMap.getOrDefault(p.getProductId(), 0.0);
            double finalPrice = p.getPrice() * (1 - discount / 100);
    %>
        <tr>
            <td><%= p.getName() %></td>
            <td><%= p.getQuantity() %></td>
            <td>₹<%= p.getPrice() %></td>
            <td><%= discount %>%</td>
            <td>₹<%= finalPrice * p.getQuantity() %></td>
        </tr>
    <% } %>
</table>
<p>Subtotal: ₹<%= request.getAttribute("subtotal") %></p>
<p>Tax: ₹<%= request.getAttribute("tax") %></p>
<p>Credit Used: ₹<%= request.getAttribute("creditUsed") %></p>
<h3>Final Total: ₹<%= request.getAttribute("total") %></h3>
<a href="checkout.jsp">← Back to Checkout</a>
</body>
</html>
