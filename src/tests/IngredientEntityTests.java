package src.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.entity.CommonIngredient;
import src.entity.Ingredient;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;


public class IngredientEntityTests {
    private Ingredient ingredient;

    @BeforeEach
    void init() {
        ingredient = new CommonIngredient(123, "Butter", "2.0 tablespoons");}



    @Test
    void geIDTest() {
        assertEquals(123, ingredient.getId());
    }

    @Test
    void getNameTest() {
        assertEquals("Butter", ingredient.getName());


    }

    @Test
    void getAmountTest() {
        assertEquals("2.0 tablespoons", ingredient.getAmount());




    }













}


