package src.interface_adapters.mealplan;

import src.use_case.mealplan.MealPlanOutputBoundary;
import src.use_case.mealplan.MealPlanOutputData;

public class MealPlanPresenter implements MealPlanOutputBoundary {
    private final MealPlanViewModel mealPlanViewModel;
    private ViewManagermodel viewManagermodel;

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
