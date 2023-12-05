package src.use_case.mealplan;

import src.use_case.mealplan.MealPlanOutputData;

public interface MealPlanOutputBoundary {
   void prepareSuccessView(MealPlanOutputData mealplan);
   void prepareFailView(String error);
}
