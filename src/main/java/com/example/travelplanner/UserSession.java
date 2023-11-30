package com.example.travelplanner;

public class UserSession {
    private int user_id;
    private String username;
    private String password;
    private CartController cartController;

    private UserSession() {
    }


    private static final UserSession instance = new UserSession();

    public static UserSession getInstance() {
        return instance;
    }

    public void setCurrentUser(int user_id, String username, String password) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
    }

    public int getCurrentUserId() {
        return user_id;
    }

    public String getCurrentUsername() {
        return username;
    }

    public String getCurrentPassword() {
        return password;
    }
    public CartController getCartController() {
        return cartController;
    }

    public void setCartController(CartController cartController) {
        this.cartController = cartController;
    }



    public void clearSession() {
        this.user_id = 0;
        this.username = null;
        this.password = null;
    }

}
