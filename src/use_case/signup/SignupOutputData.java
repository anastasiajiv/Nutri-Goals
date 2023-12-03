package src.use_case.signup;

import java.time.LocalDateTime;

public class SignupOutputData {
    private  int createdUserID;

    private String username;

    private String creationTime;

    private boolean useCaseFailed;


    public SignupOutputData(int createdUserID, String username, String creationTime, boolean useCaseFailed) {
        this.createdUserID = createdUserID;
        this.username = username;
        this.creationTime = creationTime;
        this.useCaseFailed = useCaseFailed;
    }

    public int getCreationUserID() {
        return createdUserID;
    }

    public String getUsername() {
        return username;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }



}
