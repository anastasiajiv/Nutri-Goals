package src.use_case.mealplan;

import src.entity.MealPlan;

public class MealPlanInteractor implements MealPlanInputBoundary {
    final MealPlanDataAccessInterface userDataAccessObject;
    //final MealPlanOutputBoundary mealplanPresenter;

    public MealPlanInteractor(MealPlanDataAccessInterface userDataAccessObject){
        this.userDataAccessObject = userDataAccessObject;
    }

    @Override
    public void execute(MealPlanInputData mealPlanInputData) {

        int id = mealPlanInputData.getId();

        if (!userDataAccessObject.existByUserID(id)){
            //mealplanPresenter.prepareFailView( id + ": Account does not exist.");



        } else{


            MealPlan mealplan = userDataAccessObject.getMealPlan(id);
            userDataAccessObject.saveMealPlantoCsv(id);
            String mealplanstring = userDataAccessObject.displayMealPlan(mealplan);
            MealPlanOutputData mealPlanOutputData = new MealPlanOutputData(mealplanstring);
            //mealplanPresenter.prepareSuccessView(mealPlanOutputData);







        }





    }
}
