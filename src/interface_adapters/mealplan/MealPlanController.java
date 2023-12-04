package src.interface_adapters.mealPlan;

import src.use_case.mealPlan.MealPlanInputBoundary;
import src.use_case.mealPlan.MealPlanInputData;

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
