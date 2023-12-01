package src.use_case.signup;
import java.util.ArrayList;
import java.util.List;


public class SignupInputData {

    final private String username;

    final private String password;

    final private String repeatPassword;

    private static List<Integer> generatedIDs = new ArrayList<>();


    public SignupInputData(String username, String password, String repeatPassword) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    String getUsername() {return username;}

    String getPassword() {return password;}

    String getRepeatPassword() {return repeatPassword;}

}
