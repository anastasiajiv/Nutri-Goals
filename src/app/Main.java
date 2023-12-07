package src.app;

//import interface_adapter.LoginViewModel;
//import interface_adapter.SignupViewModel;
//import interface_adapter.ViewManagerModel;
//import view.LoginView;
//import view.SignupView;
//import view.ViewManager;


import org.json.JSONArray;
import src.data_access.FileUserDataAccessObject;
import src.entity.*;
import src.entity.UserFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        String csvmealplanpath = "mealplan1.csv";

        UserFactory userFactory = new CommonUserFactory();
        UserFactory userFactory1 = new CommonUserFactory();

        FileUserDataAccessObject userDAO = new FileUserDataAccessObject("help.csv", "mealplan.csv", userFactory);

        userDAO.loadUserDataFromCsv();


        System.out.println(userDAO.existByUserID(1));

        MealPlan mealplan = userDAO.getMealPlan(1);

        System.out.println(userDAO.mealplanaccounts);

        MealPlan mp = userDAO.mealplanuserid(1);

        System.out.println(mp.getBreakfast().getRecipeIngredients().get(0).getId());








        //FileUserDataAccessObject fileUserDataAccessObject = new FileUserDataAccessObject("example.csv", "mealPlan.csv", new CommonUserFactory());



//            User tesst_user = userDAO.getAccountByUserID(4);
//
//
//            User newUser = userFactory.createdDefaultUser(userDAO.createUserID(), "new");
//            newUser.setPassword("Dario");
//            newUser.setCreationTime(LocalDateTime.now());
//
//            System.out.println(userDAO.saveUserSignUpData(newUser.getUserId(),
//                    newUser.getName(),
//                    newUser.getPassword(),
//                    newUser.getCreationTime()));
//
//
//
//
//            HashMap<String, Boolean> gender = new HashMap<>();
//            gender.put("female", Boolean.FALSE);
//            gender.put("male", Boolean.TRUE);
//
//            double height = 175.5;
//            double weight = 65.5;
//            int age = 19;
//            int exerciseLvl = 1;
//            String paceType = "fast";
//
//
//            HashMap<String, Boolean> weightGoal = new HashMap<>();
//            weightGoal.put("maintainWeight", Boolean.TRUE);
//            weightGoal.put("gainWeight", Boolean.FALSE);
//            weightGoal.put("loseWeight", Boolean.FALSE);
//
//            newUser.setWeightGoalType(weightGoal);
//            newUser.setGender(gender);
//            newUser.setUserHeight(height);
//            newUser.setUserWeight(weight);
//            newUser.setUserAge(age);
//            newUser.setUserExerciseLvl(exerciseLvl);
//            newUser.setPaceType(paceType);
//
//
//
//            userDAO.saveWeightGoalData(newUser.getUserId(),
//                    newUser.getGender(),
//                    newUser.getUserHeight(),
//                    newUser.getUserWeight(),
//                    newUser.getUserAge(),
//                    newUser.getUserExerciseLevel(),
//                    newUser.getPaceType(),
//                    weightGoal);
//
//            HashMap<String, Boolean> dietary = new HashMap<>();
//            dietary.put("Vegetarian", Boolean.FALSE);
//            dietary.put("Vegan", Boolean.FALSE);
//            dietary.put("Pescatarian", Boolean.FALSE);
//            dietary.put("none1", Boolean.TRUE);
//
//            newUser.setDietary(dietary);
//            HashMap<String, Boolean> allergies = new HashMap<>();
//            allergies.put("Eggs", Boolean.FALSE);
//            allergies.put("Sesame", Boolean.FALSE);
//            allergies.put("Shellfish", Boolean.FALSE);
//            allergies.put("Wheat", Boolean.FALSE);
//            allergies.put("Peanut", Boolean.FALSE);
//            allergies.put("Seafood", Boolean.TRUE);
//            allergies.put("none", Boolean.TRUE);
//            allergies.put("Wheat", Boolean.FALSE);
//            allergies.put("TreeNut", Boolean.FALSE);
//            newUser.setAllergies(allergies);
//            HashMap<String, String> condition = new HashMap<>();
//            condition.put("Magnesium", "low");
//            condition.put("Iron", "average");
//            condition.put("Calcium", "high");
//            condition.put("VitaminD", "high");
//            condition.put("VitaminC", "average");
//            condition.put("Sugar", "low");
//            condition.put("Potassium", "average");
//
//            newUser.setConditions(condition);
//
//            userDAO.savePreferences(newUser.getUserId(), newUser.getDietary(), newUser.getAllergies(), newUser.getConditions());
//            String breakFast = userDAO.Breakfast(newUser.getUserId());
//            String lunch = userDAO.Lunch(newUser.getUserId());
//            String dinner = userDAO.Dinner(newUser.getUserId());
//            List<Ingredient> ing = userDAO.CreateIngredients(breakFast);
//            Recipe rec = userDAO.CreateRecipeBreakfast(ing, breakFast);
//            MealPlan mealplan = userDAO.getMealPlan(newUser.getUserId());

           // System.out.println(mealplan);

            //System.out.println(userDAO.mealplanaccounts);


        //System.out.println(userDAO.accounts.size());


        //System.out.println(dinner);
        //System.out.println(userDAO.computedRequiredCalories(newUser.getUserId()));
        //System.out.println(lunch_cals);
       // System.out.println(breakfast_cals);
        //System.out.println(lunch_cals + lunch_cals + breakfast_cals);*/
       // System.out.println(userDAO.getAccountByUserID(4).getConditions());
       // System.out.println(userDAO.Dinner(tesst_user.getUserId()));



    }


//        String csvFilePath = "final.csv";
//
//        HashMap<String, HashMap<String, Boolean>> weightPaceType = null;
//
//        UserFactory userFactory = new CommonUserFactory();
//
//        HashMap<String, Boolean> weightgoal = new HashMap<>();
//        weightgoal.put("maintainWeight", Boolean.TRUE);
//
//        try {
//            FileUserDataAccessObject userDAO = new FileUserDataAccessObject(csvFilePath, userFactory);
//            User user = new CommonUser(1,
//                    "Aarya",
//                    "Password",
//                    LocalDateTime.now(),
//                    new HashMap<>(),
//                    175,
//                    65.5,
//                    19,
//                    1,
//                    new HashMap<>(),
//                    new HashMap<>(),
//                    "normal", 0
//                    );
//
//            User testUser = userFactory.createdDefaultUser(1,
//                    "Aarya");
//
//            User user2 = new CommonUser(2,
//                    "Aarya",
//                    "Password",
//                    LocalDateTime.now(),
//                    new HashMap<>(),
//                    175,
//                    65.5,
//                    19,
//                    1,
//                    new HashMap<>(),
//                    new HashMap<>(),
//                    "normal",
//                    0
//                );
//
//
//            User user3 = new CommonUser(5,
//                    "Aarya",
//                    "Password",
//                    LocalDateTime.now(),
//                    new HashMap<>(),
//                    200000000.0,
//                    65.5,
//                    19,
//                    1,
//                    new HashMap<>(),
//                    weightgoal,
//                    "extreme",
//                    0
//            );
//
//            testUser.setUserExerciseLvl(1);
//            System.out.println(testUser.getUserExcerciseLevel());
//            testUser.setRequiredCalories(4000);
//            userDAO.saveWeightGoalData(testUser);
//
//
//
//            // Optional[String] : None
//            //userDAO.saveNewUser(user3);
//            //System.out.println(userDAO.existById(2));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }



