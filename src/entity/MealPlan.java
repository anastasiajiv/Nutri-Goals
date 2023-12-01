package src.entity;

import java.util.ArrayList;
import java.util.HashMap;

public interface MealPlan {
    Recipe getBreakfast();

    Recipe getLunch();

    Recipe getDinner();

    HashMap<String, Double> getCumulativeNutritionalInfo();
}