package com.train.thierry;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class OrderReader {


    /**
     *Read the content of the csv file called kitchenSide.csv
     */
    public void read() {

        Path order = Paths.get("kitchenSide.csv");
        List<String> lines = null;// contains nothing by default
        try {
            lines = Files.readAllLines(order);
        } catch (IOException e) {
            System.out.print("The file orders cannot be opened");
        }
        if (lines.size() < 2) {
            System.out.print("The orders file contains nothing");
        }

        String[] menus = {" Chiken menu ", " Beef menu ", " Vegetarian menu"};
        String[] side = {" with fresh vegetables ", " with fries ", " with rice "};
        String[] drink = {" and still water ", " and sparkling water ", " and soda "};
        String[] vegetarianSide = {" with rice ", " with no rice "};
        System.out.print("Summary of orders :\r\n");
        for (int i = 1; i < lines.size(); i++) {
            String[] sp = lines.get(i).split(",");
            int nbMen = Integer.valueOf(sp[0]);
            int nbSide = Integer.valueOf(sp[1]);
            int nbDrink = Integer.valueOf(sp[2]);
            String orderLine = menus[nbMen - 1];
            if (nbMen == 3) {
                orderLine += vegetarianSide[nbSide - 1];
            } else {
                orderLine += side[nbSide - 1];
            }
            if (nbDrink == -1) {
                orderLine += "and without drink";
            } else {
                orderLine += drink[nbDrink - 1];
            }
            System.out.print(orderLine + "\r\n");
        }
    }
    public static void main(String[] args) {

        OrderReader orderReader = new OrderReader();
        orderReader.read();
    }
}
