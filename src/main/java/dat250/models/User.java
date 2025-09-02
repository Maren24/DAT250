package dat250.models;

public class User {

    private String userId; // Also serves as username, as username has to be unique.
    private String email;
    private String password;

    public User() {
    }
    public User(String userId, String email, String password) {
        this.userId = userId;
        this.email = email;
        this.password = password;
    }

    // UserID
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    // Email
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    // Password
    public String getPassword() {
        return password;
    }
    public void setPassword(String password)   {
        this.password = password;
    }

    // Just commented out old code, you can remove it yourself if you want
    // public static class UpdateRequest {
    //     private String email;
    //     private String password;

    //     // getters and setters
    //     public UpdateRequest() {}

    //     public void setEmail(String email) {
    //         this.email = email;
    //     }
    //     public void setPassword(String password) {
    //         this.password = password;
    //     }

    //     public String getEmail() {
    //         return email;
    //     }

    //     public String getPassword() {
    //         return password;
    //     }
    // }
}
