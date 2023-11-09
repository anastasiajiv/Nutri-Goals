package src.app;

//import interface_adapter.LoginViewModel;
//import interface_adapter.SignupViewModel;
//import interface_adapter.ViewManagerModel;
//import view.LoginView;
//import view.SignupView;
//import view.ViewManager;


import src.entity.CommonRecipe;
import src.entity.CommonUser;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        //User Entity Test

        HashMap<String, Boolean> weightGoalType = new HashMap<>();
        weightGoalType.put("Maintain", null);
        weightGoalType.put("Gain", null);
        weightGoalType.put("Lose", null);



        CommonUser user = new CommonUser(1, "Aarya Bhardawaj", "dario", LocalDateTime.now(),
                175, 63.5, 19, 3,
                new HashMap<>(),weightGoalType);
        System.out.println(user.getUserId());
        System.out.println(user.getName());
        System.out.println(user.getUserRestriction());
        System.out.println(user.getUserHeight());
        System.out.println(user.getUserAge());
        System.out.println(user.getUserWeight());
        System.out.println(user.getUserExcerciseLevel());
        System.out.println(user.getPassword());
        System.out.println(user.getCreationTime());
        System.out.println(user.getWeightGoalType());
        System.out.println(user); // -> data access save to csv


        // Recipe Entity Test
        System.out.println("------------------------------------------------------------");
        HashMap<String, Double> recipeIngredients = new HashMap<>();
        recipeIngredients.put("Eggs", 4.0);
        recipeIngredients.put("Bread", 2.0);

        HashMap<String, Boolean> recipeType = new HashMap<>();
        recipeType.put("Breakfeast", Boolean.TRUE);
        recipeType.put("Lunch", Boolean.FALSE);
        recipeType.put("Dinner", Boolean.FALSE);


        CommonRecipe recipe = new CommonRecipe(1234,"Omlette",
                recipeIngredients, "Insert instuctions", recipeType);


        System.out.println(recipe.getRecipeId());
        System.out.println(recipe.getRecipeName());
        System.out.println(recipe.getRecipeIngredients());
        System.out.println(recipe.getRecipeInstructions());
        System.out.println(recipe.recipeType());
        System.out.println(recipe.getRecipeType());



    }
}

