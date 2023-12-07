package src.use_case.signup;


import src.entity.User;

import src.entity.UserFactory;

import java.time.LocalDateTime;


public class SignupInteractor implements SignupInputBoundary{
    final SignupUserDataAccessInterface userDataAccessObject;
    final SignupOutputBoundary userPresenter;
    final UserFactory userFactory;

    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        int createdUserID = userDataAccessObject.createUserID();
        LocalDateTime creationTime = LocalDateTime.now();
        if (userDataAccessObject.existByUserID(createdUserID)) { // TODO: implement createUserID methods in file userDAO and then change this method call
        //if (userDataAccessObject.existByName(signupInputData.getUsername())) {
            // check in case user already exists in accounts map.
            userPresenter.prepareFailView("This user already exits");// should be impossible
        }else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords do not match");
        }else {

            userDataAccessObject.saveUserSignUpData(createdUserID,
                    signupInputData.getUsername(),
                    signupInputData.getPassword(),
                    creationTime);

            SignupOutputData signupOutputData = new SignupOutputData(createdUserID,
                    signupInputData.getUsername(),
                    creationTime.toString(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }

    }


}// GIT WORKFLOW