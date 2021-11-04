package com.codecool.gladiator.view;

import java.util.Scanner;

/**
 * Basic console view implementation
 */
public class ConsoleView implements Viewable {

    @Override
    public void display(String text) {
        System.out.println(text);
    }

    @Override
    public int getNumberBetween(int min, int max) {
        Scanner scanner = new Scanner(System.in);
        int numberGiven;

        do {
            do{
                try{
                    System.out.printf("Provide a number between %s and %s: ", min, max);
                    numberGiven = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (NumberFormatException exception){
                    System.out.println("Provided input is not a number, try again!");
                }
            } while (true);

            if (numberGiven >= min && numberGiven <=max)
                break;

            System.out.printf("Provided number is out of range between %s and %s, try again!", min, max);
        } while (true);

        return numberGiven;
    }

}
