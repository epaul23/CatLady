/*
 * This file contains the InputController class.
 */

package controller;

import java.util.List;
import java.util.Scanner;
import model.Action;
import model.Board;
import view.InputView;

/**
 * Handles requests for user input and ensures that the input is in the correct form.
 * 
 * @author Serena Davis
 * @version 3-31-2025
 */
public class InputController {
    /**
     * The view that takes user input.
     */
    private InputView inputView;
    
    /**
     * Class constructor instantiating the InputView.
     * 
     * @param scanner A Scanner for use in InputView.
     */
    public InputController(Scanner scanner) {
        inputView = new InputView(scanner);
    }

    /**
     * Returns the user's input after ensuring that it is one of the expected options.
     * 
     * @param allowed A list of allowed options for the input.
     * @param message A description for the user of what to enter.
     * @return The user's input.
     */
    public String getInput(List<String> allowed, String message){
        String input = inputView.getInput(message).toLowerCase();
        while(!allowed.contains(input)){
            input = inputView.getInput("That is not a valid option. Please try again.").toLowerCase();
        }
        return input;
    }

    /**
     * Returns the user's input after ensuring that it is one of the expected options.
     * 
     * @param allowed An array of allowed options for the input.
     * @param message A description for the user of what to enter.
     * @return The user's input.
     */
    public String getInput(String[] allowed, String message){
        String input = inputView.getInput(message).toLowerCase();
        boolean containsInput = false;
        for(String item: allowed){
            if(input.equals(item)) containsInput = true;
        }
        while(!containsInput){
            input = inputView.getInput("That is not a valid option. Please try again.").toLowerCase();
            for(String item: allowed){
                if(input.equals(item)) containsInput = true;
            }
        }
        return input;
    }

    /**
     * Returns the nessecary information to perform the Action given as an argument.
     * 
     * @param action The action the input is needed for.
     * @param board The game board. 
     * @return A String of the input required for the specified action. 
     */
    public String getActionInput(Action action, Board board){
        if(action.getActionName().equals("laser pointer")){
            return getSpecificCards(action.getValidInputs(board), action.getInputMessage());
        }
        else{
            return getInput(action.getValidInputs(board), action.getInputMessage());
        }
    }

    /**
     * Returns the player's 3 card choices for the laser pointer action.
     * 
     * @return A String indicating all 3 of the player's card choices.
     */
    public String getSpecificCards(List<String> lines, String message){
        StringBuilder sb = new StringBuilder();
        String input = inputView.getInput(message+"\nWhich card would you like to take first? Please enter the column then the row, for example 'AE'.").toLowerCase();
        while(!lines.subList(0,3).contains(input.substring(0,1)) || !lines.subList(3,6).contains(input.substring(1))){
            input = inputView.getInput("That is not a valid option. Please enter the column then the row.").toLowerCase();
        }
        sb.append(input);
        input = inputView.getInput("Which card would you like to take next? Please enter the column then the row.").toLowerCase();
        while(!lines.subList(0,3).contains(input.substring(0,1)) || !lines.subList(3,6).contains(input.substring(1)) || input.equals(sb.toString())){
            input = inputView.getInput("That is not a valid option. Please enter the column then the row, and you can not pick the same card twice.").toLowerCase();
        }
        sb.append(input);
        input = inputView.getInput("Which card would you like to discard? Please enter the column then the row.").toLowerCase();
        while(!lines.subList(0,3).contains(input.substring(0,1)) || !lines.subList(3,6).contains(input.substring(1)) || input.equals(sb.toString().substring(0,2)) || input.equals(sb.toString().substring(2))){
            input = inputView.getInput("That is not a valid option. Please enter the column then the row, and you can not pick the same card twice.").toLowerCase();
        }
        sb.append(input);
        return sb.toString();
    }
}
