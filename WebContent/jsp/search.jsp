<%@ page import="java.util.*, model.Product" %>
<html>
<head>
    <title>Search Product - SNU Mart</title>
</head>
<body>
<h2>Search Products in Store</h2>
<form method="get" action="../SearchServlet">
    <input type="text" name="query" placeholder="Enter Product Name or ID" required />
    <button type="submit">Search</button>
</form>

<%
    List<Product> results = (List<Product>) request.getAttribute("results");
    if (results != null) {
        if (!results.isEmpty()) {
%>
    <h3>Search Results:</h3>
    <table border="1">
        <tr><th>ID</th><th>Name</th><th>Price</th><th>Category</th><th>Section</th></tr>
        <% for(Product p : results) { %>
        <tr>
            <td><%= p.getProductId() %></td>
            <td><%= p.getName() %></td>
            <td>₹<%= p.getPrice() %></td>
            <td><%= p.getCategory() %></td>
            <td><%= p.getSection() != null ? p.getSection() : "N/A" %></td>
        </tr>
        <% } %>
    </table>
<%  } else { %>
    <p style="color:red;">No matching product found.</p>
<%  }
} %>

</body>
</html>
