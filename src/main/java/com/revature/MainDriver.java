package com.revature;

import java.util.List;
import java.util.Scanner;

import com.revature.controller.MoonController;
import com.revature.controller.PlanetController;
import com.revature.controller.UserController;
import com.revature.models.Moon;
import com.revature.models.Planet;
import com.revature.models.User;
import com.revature.models.UsernamePasswordAuthentication;
import com.revature.repository.MoonDao;
import com.revature.repository.PlanetDao;
import com.revature.repository.UserDao;
import com.revature.service.MoonService;
import com.revature.service.PlanetService;
import com.revature.service.UserService;
import com.revature.utilities.ConnectionUtil;

public class MainDriver {

    public static int loggedInUserId = 0;

    public static UserDao userDao = new UserDao();
    public static UserService userService = new UserService(userDao);
    public static UserController userController = new UserController(userService);

    /*
        An example of how to get started with the registration business and software requirements has been
        provided in this code base. Feel free to use it yourself, or adjust it to better fit your own vision
        of the application. The affected classes are:
            MainDriver
            UserController
            UserService
     */
    public static void main(String[] args) {
        // TODO: implement main method to initialize layers and run the application

        // We are using a Try with Resources block to auto close our scanner when we are done with it
        try (Scanner scanner = new Scanner(System.in)){
            /*
                The userIsActive boolean is used to control our code loop. While the user is "active" the code
                will loop. When prompted, the user can enter "q" to quit the program
             */
            boolean logInScreen = true;
            boolean loggedIn = false;
            do {
                System.out.println("\n\nHello! Welcome to the Planetarium!\n" +
                        "\nEnter 1 to register an account, 2 to log in, q to quit");
                String userChoice = scanner.nextLine();
                if (userChoice.equals("1")){
                    // remind the user of the choice they made
                    System.out.println("\nYou chose to register an account!");

                    // get the prospective username of the new user
                    System.out.print("Please enter your desired username: ");
                    String potentialUsername = scanner.nextLine();

                    // get the prospective password of the new yser
                    System.out.print("Please enter your desired password: ");
                    String potentialPassword = scanner.nextLine();

                    // create a User object and provide it with the username and password
                    // keep in mind the id will be set by the database if the username and password
                    // are valid
                    User potentialUser = new User();
                    potentialUser.setUsername(potentialUsername);
                    potentialUser.setPassword(potentialPassword);

                    int potentialId = userController.register(potentialUser);
                    // pass the data into the service layer for validation
                    if(potentialId != -1){
                        logInScreen = false;
                        loggedIn = true;
                        loggedInUserId = potentialId;
                        System.out.println("User ID is: " + loggedInUserId);
                    }

                } else if (userChoice.equals("2")){
                    System.out.println("\nYou chose to log in!\n");
                    System.out.print("Please enter your username: ");
                    String username = scanner.nextLine();

                    System.out.print("Please enter your password: ");
                    String password = scanner.nextLine();

                    UsernamePasswordAuthentication credentials = new UsernamePasswordAuthentication();
                    credentials.setUsername(username);
                    credentials.setPassword(password);

                    int userId = userController.authenticate(credentials);
                    if(userId != -1){
                        logInScreen = false;
                        loggedIn = true;
                        loggedInUserId = userId;
                        //System.out.println("User ID is: " + loggedInUserId);
                    }
                } else if (userChoice.equals("q")) {
                    System.out.println("Goodbye!");
                    logInScreen = false;
                } else {
                    System.out.println("Invalid choice, please try again.");
                }
            } while (logInScreen);
            if(loggedIn){
                boolean active = true;
                PlanetDao planetDao = new PlanetDao();
                PlanetService planetService = new PlanetService(planetDao);
                PlanetController planetController = new PlanetController(planetService);

                MoonDao moonDao = new MoonDao();
                MoonService moonService = new MoonService(moonDao);
                MoonController moonController = new MoonController(moonService);

                do {
                    System.out.println("\nEnter 1 to work with Planets, 2 to work with Moons q to quit");
                    String userChoice = scanner.nextLine();
                    if (userChoice.equals("1")){
                        System.out.println("\nEnter: \n" +
                                "1 to display Planets you have added\n" +
                                "2 to display a Planet by Name\n" +
                                "3 to display a Planet by ID\n" +
                                "4 to register a Planet\n" +
                                "5 to Remove a Planet\n" +
                                "q to quit");
                        String selection = scanner.nextLine();
                        if (selection.equals("1")){
                            List<Planet> plantesRetrieved = planetController.getAllPlanets(loggedInUserId);
                            for(Planet planet : plantesRetrieved){
                                System.out.println(planet);
                            }
                        }
                        else if(selection.equals("2")){
                            System.out.println("Enter the name of the Planet you want to display.");
                            String planetName = scanner.nextLine();
                            planetController.getPlanetByName(loggedInUserId, planetName);
                        }
                        else if(selection.equals("3")){
                            System.out.println("Enter the id of the Planet you want to display.");
                            int planetId = Integer.parseInt(scanner.nextLine());
                            planetController.getPlanetByID(loggedInUserId, planetId);
                        }
                        else if (selection.equals("4")){
                            //register a Planet
                            System.out.println("REPLACE THIS WITH USER INPUT");
                            //TODO:
                            //mocking user input for a planet -- implement into a console
                            Planet planetToRegister = new Planet();
                            planetToRegister.setName("Test Planet 8");
                            planetToRegister.setOwnerId(loggedInUserId);
                            planetController.createPlanet(loggedInUserId, planetToRegister);
                        }
                        else if (selection.equals("5")){
                            System.out.println("Enter the ID of the Planet you want to remove.");
                            int planetId = Integer.parseInt(scanner.nextLine());
                            planetController.deletePlanet(planetId);
                        }
                        //TODO: add method for grabbing moons by a planet
                        else if (selection.equals("q")){
                            active = false;
                        }
                    }
                    else if (userChoice.equals("2")){
                        System.out.println("\nEnter: \n1 to display Moons you have added\n" +
                                "2 to display a Moon by Name\n" +
                                "3 to display a Moon by ID\n" +
                                "4 to register a Moon by Planet ID\n" +
                                "5 to Remove a Moon\n" +
                                "q to quit");
                        String selection = scanner.nextLine();

                        //TODO:Figure out what condition to pull the moons on
                        if (selection.equals("1")){
                            List<Moon> moonsRetrieved = moonController.getAllMoons(loggedInUserId);
                            for(Moon moon : moonsRetrieved){
                                System.out.println(moon);
                            }
                        }
                        else if(selection.equals("2")){
                            System.out.println("Enter the name of the Moon you want to display.");
                            String moonName = scanner.nextLine();
                            moonController.getMoonByName(loggedInUserId, moonName);
                        }
                        else if(selection.equals("3")){
                            System.out.println("Enter the id of the Moon you want to display.");
                            int moonId = Integer.parseInt(scanner.nextLine());
                            moonController.getMoonById(loggedInUserId, moonId);
                        }
                        else if (selection.equals("4")){
                            System.out.println("REPLACE THIS WITH USER INPUT");
                            //TODO:
                            //mocking user input for a moon -- implement into a console
                            Moon moonToRegister = new Moon();
                            moonToRegister.setName("Test Moon 2");
                            moonToRegister.setMyPlanetId(12);
                            moonController.createMoon(loggedInUserId, moonToRegister);
                        }
                        else if (selection.equals("5")){
                            System.out.println("Enter the ID of the Moon you want to remove.");
                            int moonId = Integer.parseInt(scanner.nextLine());
                            moonController.deleteMoon(moonId);
                        }
                        else if (selection.equals("q")){
                            active = false;
                        }
                    }
                    else if (userChoice.equals("q")){
                        active = false;
                    }
                    else {
                        System.out.println("Invalid choice, please try again");
                    }
                }while(active);
            }
        }
    }
}