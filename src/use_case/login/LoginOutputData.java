package src.use_case.login;

public class LoginOutputData {
    private final int userID;
    private final String username;
    private boolean useCaseFailed;

    public LoginOutputData(int userID, String username, boolean useCaseFailed) {
        this.userID = userID;
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

}
