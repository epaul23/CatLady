/*
 * This file contains the InputView class.
 */

package view;

import java.util.Scanner;

/**
 * A class that takes in input from the user. 
 * 
 * @author Serena Davis
 * @version 3-16-2025
 */
public class InputView {
    /**
     * A Scanner for use within the class.
     */
    private Scanner scanner;

    /**
     * Class constructor that sets the scanner. 
     */
    public InputView(Scanner scanner){
        this.scanner = scanner;
    }

    /**
     * Returns input from the user based on the message given as an argument. 
     * 
     * @param message A message to request a certain type of input from the user.
     * @return A String of the user's input. 
     */
    public String getInput(String message){
        System.out.println(message);
        String input = scanner.nextLine();
        return input;
    }
}
