package src.use_case.signup;

public class SignupInputData {

    //final private int userID;
    final private String username;
    final private String password;
    final private String repeatPassword;

    public SignupInputData(String username, String password, String repeatPassword) {
        //this.userID = userID;
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    //int getUserID(){return userID;}

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }
}
