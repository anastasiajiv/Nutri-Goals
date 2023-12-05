package src.interface_adapters.mealplan;

import src.interface_adapters.logged_in.LoggedInViewModel;
import src.use_case.mealplan.MealPlanOutputBoundary;
import src.interface_adapters.ViewManagerModel;
import src.use_case.mealplan.MealPlanOutputData;

public class MealPlanPresenter implements MealPlanOutputBoundary {
    private final MealPlanViewModel mealPlanViewModel;

    private final LoggedInViewModel loggedInViewModel;
    private ViewManagerModel viewManagermodel;



    public MealPlanPresenter(MealPlanViewModel mealPlanViewModel, ViewManagerModel viewManagermodel, LoggedInViewModel loggedInViewModel ){
        this.mealPlanViewModel = mealPlanViewModel;
        this.viewManagermodel = viewManagermodel;
        this.loggedInViewModel = loggedInViewModel;
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
