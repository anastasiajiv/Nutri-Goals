package src.use_case.login;

public class LoginOutputData {
    //private final int userID;
    private final String username;
    private boolean useCaseFailed;

    //public LoginOutputData(String username, boolean useCaseFailed) {
    //    this.username = username;
    //    this.useCaseFailed = useCaseFailed;
    //}
    public LoginOutputData(String username, boolean useCaseFailed){
        this.username = username;
        //this.userID = userID;
        this.useCaseFailed = useCaseFailed;
    }

    //public int getUserID(){return userID;}
    public String getUsername() {
        return username;
    }

}
