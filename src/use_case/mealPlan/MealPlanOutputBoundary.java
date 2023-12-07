package src.use_case.mealPlan;

import src.use_case.mealPlan.MealPlanOutputData;

public interface MealPlanOutputBoundary {
   void prepareSuccessView(MealPlanOutputData mealplan);
   void prepareFailView(String error);
}
