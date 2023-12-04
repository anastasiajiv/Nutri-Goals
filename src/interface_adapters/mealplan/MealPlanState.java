package src.interface_adapters.mealplan;

public class MealPlanState {

    private String mealplan;

    private int id ;
    private String idError = null;

    public MealPlanState(MealPlanState copy ){
        this.id = copy.id;
        this.idError = copy.idError;
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
