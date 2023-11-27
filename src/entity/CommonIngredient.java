package src.entity;

public class CommonIngredient implements Ingredient{
    private final int id;

    private final String name;

    private final int amount;

    CommonIngredient(int id, String name, int amount){
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }
}
