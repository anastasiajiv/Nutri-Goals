package src.entity;
import java.util.HashMap;
public interface Recipe {

    int getRecipeId();

    String getRecipeName();

    HashMap<String, Double> getRecipeIngredients(); // Using java.lang DOUBLE -> object

    String getRecipeInstructions();

}
