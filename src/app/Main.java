package src.app;

//import interface_adapter.LoginViewModel;
//import interface_adapter.SignupViewModel;
//import interface_adapter.ViewManagerModel;
//import view.LoginView;
//import view.SignupView;
//import view.ViewManager;


import src.data_access.FileUserDataAccessObject;
import src.entity.*;
import src.entity.UserFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {

        String csvmealplanpath = "mealplan.csv";

        UserFactory userFactory = new CommonUserFactory();
        UserFactory userFactory1 = new CommonUserFactory();

        FileUserDataAccessObject userDAO = new FileUserDataAccessObject("plzwork.csv", "mealPlan.csv", userFactory);

        FileUserDataAccessObject fileUserDataAccessObject = new FileUserDataAccessObject("example.csv", "mealPlan.csv", new CommonUserFactory());

            User newUser = userFactory.createdDefaultUser(userDAO.createUserID(), "new");
            newUser.setPassword("Dario");
            newUser.setCreationTime(LocalDateTime.now());

            System.out.println(userDAO.saveUserSignUpData(newUser.getUserId(),
                    newUser.getName(),
                    newUser.getPassword(),
                    newUser.getCreationTime()));




            HashMap<String, Boolean> gender = new HashMap<>();
            gender.put("female", Boolean.FALSE);
            gender.put("male", Boolean.TRUE);

            double height = 175.5;
            double weight = 65.5;
            int age = 19;
            int exerciseLvl = 1;
            String paceType = "fast";


            HashMap<String, Boolean> weightGoal = new HashMap<>();
            weightGoal.put("maintainWeight", Boolean.TRUE);
            weightGoal.put("gainWeight", Boolean.FALSE);
            weightGoal.put("loseWeight", Boolean.FALSE);

            newUser.setWeightGoalType(weightGoal);
            newUser.setGender(gender);
            newUser.setUserHeight(height);
            newUser.setUserWeight(weight);
            newUser.setUserAge(age);
            newUser.setUserExerciseLvl(exerciseLvl);
            newUser.setPaceType(paceType);



            userDAO.saveWeightGoalData(newUser.getUserId(),
                    newUser.getGender(),
                    newUser.getUserHeight(),
                    newUser.getUserWeight(),
                    newUser.getUserAge(),
                    newUser.getUserExerciseLevel(),
                    newUser.getPaceType(),
                    weightGoal);

            HashMap<String, Boolean> dietary = new HashMap<>();
            dietary.put("Vegetarian", Boolean.TRUE);
            dietary.put("Vegan", Boolean.FALSE);
            dietary.put("Pescatarian", Boolean.FALSE);

            newUser.setDietary(dietary);
            HashMap<String, Boolean> allergies = new HashMap<>();
            allergies.put("Eggs", Boolean.TRUE);
            allergies.put("Sesame", Boolean.TRUE);
            allergies.put("Shellfish", Boolean.FALSE);
            allergies.put("Wheat", Boolean.FALSE);
            newUser.setAllergies(allergies);
            HashMap<String, String> condition = new HashMap<>();
            condition.put("Magnesium", "low");
            condition.put("Iron", "average");
            condition.put("Calcium", "high");
            condition.put("VitaminD", "high");
            newUser.setConditions(condition);

            userDAO.savePreferences(newUser.getUserId(), newUser.getDietary(), newUser.getAllergies(), newUser.getConditions());

        System.out.println(userDAO.accounts.size());



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



