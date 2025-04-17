<html>
<head><title>Add Product</title></head>
<body>
<h2>Add New Product</h2>
<form action="../AddProductServlet" method="post">
    Name: <input type="text" name="name" required><br/>
    Description: <textarea name="description"></textarea><br/>
    Category: <input type="text" name="category"><br/>
    Price: <input type="number" step="0.01" name="price" required><br/>
    Tax Rate (%): <input type="number" step="0.01" name="tax" value="5"><br/>
    <input type="submit" value="Add Product">
</form>
</body>
</html>
