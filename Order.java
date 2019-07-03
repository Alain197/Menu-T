package com.train.thierry;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.APPEND;

public class Order {
    Scanner sc = new Scanner(System.in); //First Attribute
    String orderSummary = ""; //Second Attribute
    /**
     * Display all available menu in the restaurant
     */
    public void displayAvailableMenu() {
        System.out.print("Choice of menu :" + "\r\n");
        System.out.print("1 - Chicken" + "\r\n");
        System.out.print("2 - Beef" + "\r\n");
        System.out.print("3 - Vegetarain" + "\r\n");
        System.out.print(" Which menu do you want to choose ?\r\n");
    }

    /**
     * Display selected menu
     * @param nbMenu the selected menu
     */
    public void displaySelectedMenu(int nbMenu) {
        switch (nbMenu) {
            case 1:
                System.out.print("You'he selected Chicken as menu\r");
                break;
            case 2:
                System.out.print("You'he selected Beef as menu\r");
                break;
            case 3:
                System.out.print("You'he selected Vegetarian as menu\r");
                break;
            default:
                System.out.print("You'he not selected anything amongst proposed choices");
                break;
        }
    }
    /**
     * Run menu and asking for the selected menu
     */
    public String runMenu() {
        int nbSide = -1;
        int nbDrink = -1;
        int nbMenu = askMenu();
        switch (nbMenu){
            case 1:
                nbSide = askSide(true);
                nbDrink = askDrink();
                break;
            case 2:
                nbSide = askSide(true);
                break;
            case 3:
                nbSide = askSide(false);
                nbDrink = askDrink();
        }
        return nbMenu + "," + nbSide + "," + nbDrink + "%n";
    }
    /**
     * Display many selected menus
     */
    public void runMenus() {
        Path orderPath = Paths.get("kitchenSide.csv");
        System.out.println("How many menus do you want ?");
        int menuQuantity = -1;
        boolean responseIsGood;
        do {
            try {
                menuQuantity = sc.nextInt();
                responseIsGood = true;
            } catch (InputMismatchException e) {
                sc.next();
                System.out.println("You must enter a number which corresponds to menus you wish .");
                responseIsGood = false;
            }
        } while (!responseIsGood);
        orderSummary = "Summary of menus :%n";
        for (int i = 0; i < menuQuantity; i++) {
            orderSummary += "Menu " + (i + 1) + ":%n";
            String orderLine =runMenu();
            try {
                Files.write(orderPath, String.format(orderLine).getBytes(),APPEND);
            } catch (IOException e) {
                System.out.print("Oops an error occured. Please, retry later .");
                return;
            }
        }
        System.out.println("");
        System.out.println(String.format(orderSummary));
    }
    /**
     * Display All available accompaniments
     * @param allSideEnable
     * */
    public void displayAllSideEnable(boolean allSideEnable){
        System.out.print("Choice of accompaniment"+"\r\n");
        if(allSideEnable){
            System.out.print("1 - Fresh vegetarian\r\n");
            System.out.print("2 - Rice\r\n");
            System.out.print("3 - Fries\r\n");
        }else {
            System.out.print("1- Rice\r\n");
            System.out.print("2- No rice\r\n");
        }
        System.out.print("What would you want to choose as accompaniment ?\r\n");
    }
    /**
     * Display All available drinks
     * */
    public void displayAvailableDrink(){
        System.out.print("Choice of drink or beverage\r\n");
        System.out.print("1 - Still Water\r\n");
        System.out.print("2 - Sparkling Water\r\n");
        System.out.print("3 - Soda\r\n");
        System.out.print("What drink do you want ?\r\n");
    }

    /***
     * Display selected accompaniment
     * @param nbSide = the selected accompaniment
     * @param allSidesEnable = allowed accompaniments according to menu concerned
     */
    public void displaySelectedSide(int nbSide, boolean allSidesEnable){
        if(allSidesEnable){
            switch (nbSide){
                case 1:
                    System.out.print("You'he chosen as accompaniment : Fresh vegetarian\r");
                    break;
                case 2:
                    System.out.print("You'he chosen as accompaniment : Rice\r");
                    break;
                case 3:
                    System.out.print("You'he chosen as accompaniment : Fries\r");
                    break;
                default:
                    System.out.print("You'he chosen nothing amongst proposed choices.\r");
                    break;
            }

        }else {
            switch (nbSide){
                case 1:
                    System.out.print("You'he chosen as accompaniment : Rice\r\"");
                    break;
                case 2:
                    System.out.print("You'he chosen as accompaniment : No rice\r");
                    break;
                default:
                    System.out.print("You'he chosen nothing amongst proposed choices.\r");
                    break;
            }
        }

    }
    /***
     * Display selected drink
     * @param nbDrink = selected drink
     */
    public void displaySelecedDrink(int nbDrink){
        switch (nbDrink){
            case 1:
                System.out.print("You'he chosen as drink : Still Water\r");
                break;
            case 2:
                System.out.print("You'he chosen as drink : Sparking Water\r");
                break;
            case 3:
                System.out.print("You'he chosen as drink : Soda\r");
                break;
            default:
                System.out.print("You'he chosen nothing amongst proposed choices.\r");
                break;
        }
    }
    /**
     * askAnyQuestion function
     * @param category = the cathegory concerned
     * @param responses = responses concerned
     * */
    public int askAnyQuestion( String category, String [] responses){
        System.out.print(" Choice of " + category + "\r\n" );
        for (int i=1;i<= responses.length;i++){
            System.out.println(i + " - " + responses[i - 1]);
        }
        System.out.println(" What do you want as " + category + "?" + "\r\n");
        int nbResponse ;
        boolean responseIsGood ;
        do{
            nbResponse = sc.nextInt();
            responseIsGood = (nbResponse >= 1 && nbResponse <= responses.length);
            if(responseIsGood){
                String choice = "You'he chosen as " + category + " : " + responses[nbResponse - 1];
                orderSummary += choice + " %n";
                System.out.println(choice);
            }else {
                System.out.println("You'he not chosen anything as " + category + " amongst proposed choices" + "\r\n");
            }
        }while(!responseIsGood);
        return  nbResponse;
    }
    /**
     * askMenu
     * */
    public  int askMenu(){
        int res ;
        String [] menus ={"Chicken", "Beef", "Vegetarian"};
        res = askAnyQuestion("menu",menus);// the number of the chosen menu
        return  res;
    }

    /**
     * Display a question about side in the standard input, get response and display it
     */
    public int askSide(boolean allSideEnable){
        if(allSideEnable){
            String[] responsesAllSide = {"Fresh vegetables", "Fries", "Rice"};
            return askAnyQuestion("accompaniment", responsesAllSide);
        }else{
            String [] restOfResponses = {"Rice", "No Rice"};
            return askAnyQuestion("accompaniment", restOfResponses);
        }
    }
    /***
     * Display the selected drink
     * @return
     */
    public int askDrink(){
        String [] drinkRespones = {"Still Water", "Sparklin Water", "Soda"};
        return askAnyQuestion("Drink",drinkRespones);
    }

}
