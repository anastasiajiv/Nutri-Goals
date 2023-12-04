package src.interface_adapters.mealPlan;

import src.use_case.mealPlan.MealPlanOutputBoundary;
import src.interface_adapters.ViewManagerModel;
import src.use_case.mealPlan.MealPlanOutputData;

public class MealPlanPresenter implements MealPlanOutputBoundary {
    private final MealPlanViewModel mealPlanViewModel;
    private ViewManagerModel viewManagermodel;

    public MealPlanPresenter(MealPlanViewModel mealPlanViewModel){
        this.mealPlanViewModel = mealPlanViewModel;
    }


    @Override
    public void prepareSuccessView(MealPlanOutputData mealplan) {
        MealPlanState mealPlanState = mealPlanViewModel.getState();
        mealPlanState.setMealplan(mealplan.getMealplan());
        this.mealPlanViewModel.setState(mealPlanState);

    }

    @Override
    public void prepareFailView(String error) {

    }
}
