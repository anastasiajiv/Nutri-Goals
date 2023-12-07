package src.app;

import src.interface_adapters.ViewManagerModel;
import src.interface_adapters.logged_in.LoggedInViewModel;
import src.interface_adapters.mealplan.MealPlanController;
import src.interface_adapters.mealplan.MealPlanPresenter;
import src.interface_adapters.mealplan.MealPlanViewModel;
import src.interface_adapters.weightgoal.WeightGoalController;
import src.interface_adapters.weightgoal.WeightGoalPresenter;
import src.interface_adapters.weightgoal.WeightGoalViewModel;
import src.use_case.mealPlan.MealPlanDataAccessInterface;
import src.use_case.mealPlan.MealPlanInputBoundary;
import src.use_case.mealPlan.MealPlanInteractor;
import src.use_case.mealPlan.MealPlanOutputBoundary;
import src.use_case.weightgoal.WeightGoalInputBoundry;
import src.use_case.weightgoal.WeightGoalInteractor;
import src.use_case.weightgoal.WeightGoalOutputBoundry;
import src.use_case.weightgoal.WeightGoalUserDataInterface;
import src.view.LoggedInView;
import src.view.MealPlanView;
import src.view.WeightGoalsView;

import javax.swing.*;
import java.io.IOException;

public class MealPlanUseCaseFactory {

    public static MealPlanView create(ViewManagerModel viewManagerModel, MealPlanViewModel mealPlanViewModel, LoggedInViewModel
            loggedInViewModel, MealPlanDataAccessInterface mealPlanDataAccessInterface){
        try

        { MealPlanController mealPlanController = createMealPlanUseCase(viewManagerModel,
                loggedInViewModel,mealPlanViewModel,
                mealPlanDataAccessInterface);
            return new MealPlanView(mealPlanController, mealPlanViewModel);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file");
        }

        return null;




    }


    private static MealPlanController createMealPlanUseCase(ViewManagerModel viewManagerModel, LoggedInViewModel
            loggedInViewModel, MealPlanViewModel mealPlanViewModel, MealPlanDataAccessInterface mealPlanDataAccessInterface)
        throws IOException{
        MealPlanOutputBoundary  mealPlanOutputBoundary = new MealPlanPresenter(mealPlanViewModel,viewManagerModel, loggedInViewModel);

        MealPlanInputBoundary mealplaninteractor = new MealPlanInteractor(mealPlanDataAccessInterface, mealPlanOutputBoundary);

        return new MealPlanController(mealplaninteractor);
    }




}
