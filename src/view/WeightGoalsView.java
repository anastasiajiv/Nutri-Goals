package src.view;

import src.interface_adapters.weightgoal.WeightGoalController;
import src.interface_adapters.weightgoal.WeightGoalViewModel;

public class WeightGoalsView {

    private final WeightGoalViewModel weightGoalViewModel;
    private final WeightGoalController weightGoalController;


    public WeightGoalsView(WeightGoalController weightGoalController,
                           WeightGoalViewModel weightGoalViewModel) {

        this.weightGoalViewModel = weightGoalViewModel;
        this.weightGoalController = weightGoalController;
    }
}
