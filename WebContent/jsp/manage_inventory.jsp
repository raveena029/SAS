
<%@ page import="java.util.*, model.InventoryItem" %>
<html>
<head><title>Manage Inventory</title></head>
<body>
<h2>Inventory Dashboard</h2>

<table border="1">
<tr><th>Product ID</th><th>Name</th><th>Stock</th><th>Threshold</th><th>Status</th><th>Action</th></tr>
<%
    List<InventoryItem> inv = (List<InventoryItem>) request.getAttribute("inventory");
    for (InventoryItem item : inv) {
        boolean low = item.getQuantity() < item.getThreshold();
%>
<tr>
    <td><%= item.getProductId() %></td>
    <td><%= item.getProductName() %></td>
    <td><%= item.getQuantity() %></td>
    <td><%= item.getThreshold() %></td>
    <td style="color:<%= low ? "red" : "green" %>"><%= low ? "LOW STOCK" : "Sufficient" %></td>
    <td><%= low ? "<button>Reorder</button>" : "-" %></td>
</tr>
<% } %>
</table>
</body>
</html>
