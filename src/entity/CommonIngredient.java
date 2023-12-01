package src.entity;

public class CommonIngredient implements Ingredient {
    private final int ingredientID;
    private final String name;

    private final String amount;

    public CommonIngredient(int ingredientID, String name, String amount){
        this.ingredientID = ingredientID;
        this.name = name;
        this.amount = amount;
    }

    public int getIngredientID() {
        return this.ingredientID;
    }

    public String getAmount() {
        return this.amount;
    }

    public String getName() {
        return this.name;
    }
}