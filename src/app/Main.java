package src.app;

//import interface_adapter.LoginViewModel;
//import interface_adapter.SignupViewModel;
//import interface_adapter.ViewManagerModel;
//import view.LoginView;
//import view.SignupView;
//import view.ViewManager;


import src.data_access.FileUserDataAccessObject;
import src.entity.CommonUser;
import src.entity.*;
import src.entity.UserFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {

        UserFactory userFactory = new CommonUserFactory();
        UserFactory userFactory1 = new CommonUserFactory();

        FileUserDataAccessObject userDAO = new FileUserDataAccessObject("new.csv", userFactory);

//        User newUser = userFactory.createdDefaultUser(2387, "Aarya");
//        newUser.setPassword("Dario");
//        newUser.setCreationTime(LocalDateTime.now());
//
//        userDAO.saveNewUser(newUser);

        User testSave = userDAO.getAccountByUserId(2387);
        HashMap<String, Boolean> gender = new HashMap<>();
        gender.put("female", Boolean.FALSE);
        gender.put("male", Boolean.TRUE);

        double height = 175.5;
        double weight = 165.5;
        int age = 19;
        int exerciseLvl = 3;
        String paceType = "typical";
        double reqCalories = 2300.45;

        HashMap<String, Boolean> weightGoal = new HashMap<>();
        weightGoal.put("maintainWeight", Boolean.FALSE);
        weightGoal.put("gainWeight", Boolean.TRUE);
        weightGoal.put("loseWeight", Boolean.FALSE);




        testSave.setWeightGoalType(weightGoal);
        testSave.setGender(gender);
        testSave.setUserHeight(height);
        testSave.setUserWeight(weight);
        testSave.setUserAge(age);
        testSave.setUserExerciseLvl(exerciseLvl);
        testSave.setPaceType(paceType);
        testSave.setRequiredCalories(reqCalories);

        userDAO.saveWeightGoalData(testSave.getUserId());



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
}

