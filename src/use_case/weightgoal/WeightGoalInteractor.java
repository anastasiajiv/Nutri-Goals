package src.use_case.weightgoal;


import java.util.HashMap;
public class WeightGoalInteractor implements WeightGoalInputBoundry{

    final WeightGoalUserDataInterface userDataAccessObject;
    final WeightGoalOutputBoundry weightGoalPresenter;

    public WeightGoalInteractor(WeightGoalUserDataInterface userDataAccessInterface,
                                WeightGoalOutputBoundry weightGoalInputBoundry) {
        this.userDataAccessObject = userDataAccessInterface;
        this.weightGoalPresenter = weightGoalInputBoundry;
    }

    @Override
    public void execute(WeightGoalInputData weightGoalInputData) {

        // get attributes from input data
        int userId = weightGoalInputData.getUserId();

        HashMap<String, Boolean> gender = weightGoalInputData.getGender();

        HashMap<String, Boolean> weightGoal = weightGoalInputData.getWeightGoal();

        double height = weightGoalInputData.getHeight();

        double weight = weightGoalInputData.getWeight();

        int age = weightGoalInputData.getAge();

        int exerciseLvl = weightGoalInputData.getExerciseLvl();

        String paceType = weightGoalInputData.getPaceType();
        if (!userDataAccessObject.existByUserID(userId)) {
            weightGoalPresenter.prepareFailView("UserID cannot be found");
        } else {

            // save weightGoal data
            if (userDataAccessObject.saveWeightGoalData(userId,
                    gender,
                    height,
                    weight,
                    age,
                    exerciseLvl,
                    paceType,
                    weightGoal)) { // call the userDAO method to update the existing user data
                // for the given user with this new weightGoal data

                WeightGoalOutputData weightGoalOutputData = new WeightGoalOutputData(userId,
                        gender,
                        height,
                        weight,
                        age,
                        exerciseLvl,
                        paceType,
                        weightGoal,
                        false);
                weightGoalPresenter.prepareSuccessView(weightGoalOutputData);
            }else {
                weightGoalPresenter.prepareFailView("Error with saving weight goal data");
            }
        }

    }


}
