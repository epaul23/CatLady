/*
 * This file contains the Board class.
 */

package model;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing the game board. Stores the positions of cards on the board,
 * the cat figure, and the standard and stray cat decks.
 * 
 * @author Serena Davis
 * @author Flora Campbell
 * @version 4-1-2025
 */
public class Board {
    /**
     * The deck containing all cards, for refilling the board.
     */
    private StandardDeck standardDeck;
    /**
     * The deck containing stray cat cards.
     */
    private StrayCatDeck strayCatDeck;
    /**
     * Stores the locations of cards on the board.
     */
    private Card[][] board;
    /**
     * The row/column being blocked by the cat figure.
     */
    private String catPosition;
    /**
     * The length of each String in a column of the board.
     */
    private static final int COLUMN_STRING_LENGTH = 35;

    /**
     * Class constructor instantiating the decks and filling the board.
     */
    public Board(int numberOfPlayers){
        standardDeck = new StandardDeck(numberOfPlayers);
        strayCatDeck = new StrayCatDeck(numberOfPlayers);
        catPosition = null;
        board = new Card[3][3];
        for(int r = 0; r < 3; r++){
            for(int c = 0; c < 3; c++){
                board[r][c] = standardDeck.getCards(1).get(0);
            }
        }
    }

    /**
     * Returns the board.
     * 
     * @return A 2D array of cards representing the board.
     */
    public Card[][] getBoard(){
        return board;
    }

    /**
     * Returns the stray cat deck.
     * 
     * @return The stray cat deck.
     */
    public StrayCatDeck getStrayCatDeck(){
        return strayCatDeck;
    }

    /**
     * Returns the standard deck. 
     * 
     * @return The standard deck.
     */
    public StandardDeck getStandardDeck(){
        return standardDeck;
    }

    /**
     * Changes the current position of the cat figure to the row or column requested.
     * 
     * @param line A string representing the row or column the cat figure should be placed on.
     * @return void
     */
    public void moveCat(String line){
        catPosition = line;
    }

    /**
     * Returns the line currently blocked by the cat figure.
     * 
     * @return A String indicating the line the cat figure is on.
     */
    public String getCatPosition(){
        return catPosition;
    }

    /**
     * Returns a row or column of cards specified by the argument, replaces that row or
     * column with new cards, and updates the position of the cat figure. 
     * 
     * @param line A string representing the row or column of cards to return.
     * @return A list of 3 cards from the requested row or column.
     */
    public List<Card> takeLine(String line){
        List<Card> cards = new ArrayList<>();
        List<Card> newCards = standardDeck.getCards(3);
        int row = 0;
        int col = 0;
        if(line.equals("a") || line.equals("b") || line.equals("c")) {
            if(line.equals("b")) col = 1;
            if(line.equals("c")) col = 2;
            for(int i = 0; i < 3; i++){
                cards.add(board[i][col]);
                board[i][col] = newCards.get(i);
            }
        }
        else {
            if(line.equals("e")) row = 1;
            if(line.equals("f")) row = 2;
            for(int i = 0; i < 3; i++){
                cards.add(board[row][i]);
                board[row][i] = newCards.get(i);
            }
        }
        moveCat(line);
        return cards;
    }

    /**
     * Returns a card from the position on the board specified by the argument, and replaces
     * it with a new card from the deck.
     * 
     * @param position The position of the requested card.
     * @return The requested card.
     */
    public Card takeCard(String position){
        int row = 0;
        int col = 0;
        if(position.substring(0,1).equals("b")) col = 1;
        else if(position.substring(0,1).equals("c")) col = 2;
        if(position.substring(1).equals("e")) row = 1;
        else if(position.substring(1).equals("f")) row = 2;
        Card card = board[row][col];
        board[row][col] = standardDeck.getCards(1).get(0);
        return card;
    }

    /**
     * Returns the requested stray cat card from the stray cat deck.
     * 
     * @param card A string representing the requested card.
     * @return The requested card.
     */
    public Card getStrayCatCard(String card){
        return strayCatDeck.getCard(card);
    }

    /**
     * Returns a string to display all the board information.
     * 
     * @return A String of the board information.
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        //Add the stray cat cards if there are any left
        if(!strayCatDeck.isEmpty()){
            sb.append("\n");
            sb.append("These are the stray cat cards available: ");
            sb.append("\n\n");
            for(Card card : strayCatDeck.getCards()){
                sb.append(card.toString());
                sb.append("\n\n");
            }
            sb.append("\n");
        }

        //Add the column label
        sb.append(padString("A"));
        sb.append(padString("B"));
        sb.append(padString("C"));
        sb.append("\n\n");
        for(int row = 0; row < 3; row++){
            //Add the card type of each card
            for(int col = 0; col < 3; col++){
                sb.append(padString(board[row][col].getType()));
            }

            //Add the row label
            if(row == 0) sb.append("D");
            else if(row == 1) sb.append("E");
            else sb.append("F");
            sb.append("\n");

            //Add the second line of each card (if it is a food card, toy card, or cat card)
            for(int col = 0; col < 3; col++){
                if(board[row][col].getType().equals("Food card")){
                    sb.append(padString(((FoodCard) board[row][col]).getFoodType()));
                }
                else if(board[row][col].getType().equals("Toy card")){
                    sb.append(padString(((ToyCard) board[row][col]).getToyType()));
                }
                else if(board[row][col].getType().equals("Cat")){
                    sb.append(padString("Name: "+((CatCard) board[row][col]).getName()+", Colour: "+((CatCard) board[row][col]).getColour()));
                }
                else{
                    sb.append(padString(""));
                }
            }
            sb.append("\n");

            //Add the third line if it is a cat card
            for(int col = 0; col < 3; col++){
                if(board[row][col].getType().equals("Cat")){
                    String foodString = "Food: ";
                    String[] food = ((CatCard) board[row][col]).getFood();
                    for(int i = 0; i < food.length; i++){
                        foodString += food[i];
                        if(i < food.length-1){
                            foodString += ", ";
                        }
                    }
                    sb.append(padString(foodString));
                }
                else{
                    sb.append(padString(""));
                }
            }
            sb.append("\n");

            //Add the fourth line if it is a cat card
            for(int col = 0; col < 3; col++){
                if(board[row][col].getType().equals("Cat")){
                    sb.append(padString("Points: "+((CatCard) board[row][col]).getPoints()));
                }
                else{
                    sb.append(padString(""));
                }
            }
            sb.append("\n\n");
        }
        
        if(catPosition != null){
            sb.append("The cat figure is currently blocking line "+catPosition);
        }
        return sb.toString();
    }


    /**
     * Pads a string to a certain length with spaces for use in the toString() method.
     * 
     * @param inputString The string to be padded.
     * @return The padded string.
     */
    private String padString(String inputString){
        while(inputString.length() < COLUMN_STRING_LENGTH){
            inputString = inputString+" ";
        }
        return inputString;
    }

    /**
     * Returns a list of the columns and rows not blocked by the cat figure.
     * 
     * @return A list of row and column labels.
     */
    public List<String> getLineList(){
        ArrayList<String> lines = new ArrayList<>();
        lines.add("a");
        lines.add("b");
        lines.add("c");
        lines.add("d");
        lines.add("e");
        lines.add("f");
        lines.remove(catPosition);
        return lines;
    }
}
