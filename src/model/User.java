package model;

public class User {
    private int userId;
    private String username;
    private String role;

    public int getUserId() { return userId; }
    public void setUserId(int id) { this.userId = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
