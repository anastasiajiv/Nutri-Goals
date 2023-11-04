package src.app;

//import interface_adapter.LoginViewModel;
//import interface_adapter.SignupViewModel;
//import interface_adapter.ViewManagerModel;
//import view.LoginView;
//import view.SignupView;
//import view.ViewManager;


import src.entity.CommonUser;
import src.entity.UserFactory;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        //System.out.println("hello world");
        CommonUser user = new CommonUser("Aarya Bhardawaj", "dario", LocalDateTime.now(),
                175, 63.5, 19, 3,
                new HashMap<>());
        System.out.println(user.getName());
        System.out.println(user.getUserRestriction());
        System.out.println(user.getUserHeight());
        System.out.println(user.getUserAge());
        System.out.println(user.getUserWeight());
        System.out.println(user.getUserExcerciseLevel());
        System.out.println(user.getPassword());
        System.out.println(user.getCreationTime());
        System.out.println(user); // -> data access save to csv

    }
}

