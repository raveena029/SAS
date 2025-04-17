<%@ page import="java.util.*, model.Product" %>
<%@ page session="true" %>
<html>
<head>
    <title>Checkout - SNU Mart</title>
</head>
<body>
<h2>Checkout Page</h2>

<form action="../CheckoutServlet" method="post">
    <label>Product ID:</label>
    <input type="number" name="productId" required/><br/>
    <label>Quantity:</label>
    <input type="number" name="quantity" required/><br/>
    <input type="submit" value="Add to Cart"/><br/><br/>
</form>

<a href="../GenerateInvoiceServlet">Generate Invoice</a>

<% 
    List<Product> cart = (List<Product>) session.getAttribute("cart");
    if (cart != null && !cart.isEmpty()) {
%>
    <h3>Items in Cart:</h3>
    <ul>
    <% for(Product p : cart) { %>
        <li><%= p.getName() %> - Qty: <%= p.getQuantity() %> - ₹<%= p.getPrice() %></li>
    <% } %>
    </ul>
<% } %>

</body>
</html>
