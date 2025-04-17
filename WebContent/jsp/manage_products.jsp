<%@ page import="java.util.*, model.Product" %>
<html>
<head><title>Manage Products</title></head>
<body>
<h2>Manage Product Details</h2>
<a href="add_product.jsp">+ Add New Product</a><br><br>

<table border="1">
<tr>
    <th>ID</th><th>Name</th><th>Category</th><th>Price</th><th>Tax</th><th>Edit</th>
</tr>
<%
    List<Product> list = (List<Product>) request.getAttribute("products");
    for (Product p : list) {
%>
<tr>
    <td><%= p.getProductId() %></td>
    <td><%= p.getName() %></td>
    <td><%= p.getCategory() %></td>
    <td>₹<%= p.getPrice() %></td>
    <td><%= p.getTaxRate() %>%</td>
    <td><a href="edit_product.jsp?id=<%= p.getProductId() %>">Edit</a></td>
</tr>
<% } %>
</table>
</body>
</html>
