package src.app;

import src.interface_adapters.ViewManagerModel;
import src.interface_adapters.logged_in.LoggedInViewModel;
import src.interface_adapters.weightgoal.WeightGoalController;
import src.interface_adapters.weightgoal.WeightGoalPresenter;
import src.interface_adapters.weightgoal.WeightGoalViewModel;
import src.use_case.weightgoal.WeightGoalInputBoundry;
import src.use_case.weightgoal.WeightGoalInteractor;
import src.use_case.weightgoal.WeightGoalOutputBoundry;
import src.use_case.weightgoal.WeightGoalUserDataInterface;
import src.view.WeightGoalsView;

import javax.swing.*;
import java.io.IOException;

public class WeightGoalUseCaseFactory {
    private WeightGoalUseCaseFactory() {}

    public static WeightGoalsView create (ViewManagerModel viewManagerModel,
                                          WeightGoalViewModel weightGoalViewModel,
                                          LoggedInViewModel loggedInViewModel,
                                          WeightGoalUserDataInterface weightGoalUserDataInterface
                                          ) {
        try

        { WeightGoalController weightGoalController = createWeightGoalUseCase(viewManagerModel,
                loggedInViewModel,
                weightGoalViewModel,
                weightGoalUserDataInterface);
            return new WeightGoalsView(weightGoalController, weightGoalViewModel);

    } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file");
        }

        return null;

    }

    private static WeightGoalController createWeightGoalUseCase(ViewManagerModel viewManagerModel,
                                                                LoggedInViewModel loggedInViewModel,
                                                                WeightGoalViewModel weightGoalViewModel,
                                                                WeightGoalUserDataInterface weightGoalUserDataInterface)
        throws IOException{
        WeightGoalOutputBoundry weightGoalOutputBoundry = new WeightGoalPresenter(viewManagerModel,
                loggedInViewModel,
                weightGoalViewModel);


        WeightGoalInputBoundry weightGoalInteractor = new WeightGoalInteractor(weightGoalUserDataInterface,
                weightGoalOutputBoundry);

        return new WeightGoalController(weightGoalInteractor);
    }



}
