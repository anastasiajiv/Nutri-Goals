package src.entity;

public class CommonIngredient implements Ingredient{
    private final int id;

    private final String name;

    private final String amount;

    CommonIngredient(int id, String name, String amount){
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }
}
