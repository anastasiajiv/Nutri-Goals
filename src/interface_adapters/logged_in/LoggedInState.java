package src.interface_adapters.logged_in;

public class LoggedInState {
    private String username = "";
    private int userID = 0;

    public LoggedInState(LoggedInState copy) {
        userID = copy.userID;
        username = copy.username;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public LoggedInState() {}

    public String getUsername() {
        return username;
    }
    public int getUserID(){return userID;}
    public void setUsername(String username) {
        this.username = username;
    }

   public void setUserID(int userID){
        this.userID = userID;}
}
