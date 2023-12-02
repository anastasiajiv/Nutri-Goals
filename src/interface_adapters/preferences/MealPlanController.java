package src.interface_adapters.preferences;

import src.use_case.mealplan.MealPlanInputBoundary;
import src.use_case.mealplan.MealPlanInputData;

public class MealPlanController {
    final MealPlanInputBoundary mealplaninteractor;

    public MealPlanController(MealPlanInputBoundary mealplaninteractor){
        this.mealplaninteractor = mealplaninteractor;
    }

    public void execute(int id){
        MealPlanInputData mealPlanInputData = new MealPlanInputData(id);

        mealplaninteractor.execute(mealPlanInputData);
    }







}
