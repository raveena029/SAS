<html>
<head><title>Sales Report</title></head>
<body>
<h2>Generate Sales Report</h2>
<form method="get" action="../ReportServlet">
    <label>Choose Period:</label>
    <select name="type">
        <option value="month">This Month</option>
        <option value="quarter">This Quarter</option>
    </select>
    <button type="submit">Generate</button>
</form>

<% 
    List<model.ReportItem> data = (List<model.ReportItem>) request.getAttribute("reportData");
    Double totalTax = (Double) request.getAttribute("totalTax");
    Double totalSales = (Double) request.getAttribute("totalSales");
    if (data != null) {
%>
    <h3>Report:</h3>
    <table border="1">
        <tr><th>Product</th><th>Quantity Sold</th><th>Total Revenue</th></tr>
        <% for (model.ReportItem item : data) { %>
        <tr>
            <td><%= item.getProductName() %></td>
            <td><%= item.getQuantitySold() %></td>
            <td>₹<%= item.getTotalRevenue() %></td>
        </tr>
        <% } %>
    </table>
    <p><strong>Total Tax Collected:</strong> ₹<%= totalTax %></p>
    <p><strong>Total Sales (excluding tax):</strong> ₹<%= totalSales %></p>
<% } %>
</body>
</html>
