package src.interface_adapters.preferences;

public class MealPlanState {

    private String mealplan;

    private int id ;
    private String idError = null;


    public MealPlanState(String mealplan, MealPlanState copy){
        this.mealplan = mealplan;
        id = copy.id;
    }

    public MealPlanState(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMealplan(){return this.mealplan;}

    public void setMealplan(String mealplan){this.mealplan = mealplan;}









}
