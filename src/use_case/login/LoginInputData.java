package src.use_case.login;

public class LoginInputData {
    //final private int userID;
    final private String username;
    final private String password;

    public LoginInputData( String username, String password) {
        //this.userID = userID;
        this.username = username;
        this.password = password;
    }

    //int getUserID(){return userID;}

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

}
