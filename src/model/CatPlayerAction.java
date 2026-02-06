/*
 * This file contains the CatPlayerAction class.
 */

package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Carries out the action of the cat player picking up cards on their turn.
 * 
 * @author Serena Davis
 * @author Emil Paul
 * @version 3-29-2025
 */
public class CatPlayerAction {
    /**
     * The deck of turn cards.
     */
    private TurnCardDeck turnCardDeck;
    
    
    /**
     * Class constructor instantiating the turn card deck.
     */
    public CatPlayerAction(){
        turnCardDeck = new TurnCardDeck();
    }

    /**
     * Decides which line the cat player should take next based on a turn card.
     * 
     * @param board The game board.
     * @return A list of possible options for which line the cat player should take.
     */
    public List<String> decideLine(Board board){
        TurnCard card = (TurnCard) turnCardDeck.getCards(1).get(0);
        if(turnCardDeck.isEmpty()) turnCardDeck = new TurnCardDeck();
        int[] rows = new int[3];
        int[] cols = new int[3];
        ArrayList<String> lineOptions = new ArrayList<>();

        //count the number of cards of the correct type in each row and column
        for(int i = 0; i < 3; i++){
            int rowCount = 0;
            int columnCount = 0;
            for(int j = 0; j < 3; j++){
                if(board.getBoard()[i][j].getType().equals(card.getDescription())){
                    if(card.getDescription().equals("Cat card")){
                        if(((CatCard) board.getBoard()[i][j]).getPoints() == 6) rowCount++;
                    }
                    else rowCount++;
                }
                if(board.getBoard()[j][i].getType().equals(card.getDescription())){
                    if(card.getDescription().equals("Cat card")){
                        if(((CatCard) board.getBoard()[i][j]).getPoints() == 6) columnCount++;
                    }
                    else columnCount++;
                }
                rows[i] = rowCount;
                cols[i] = columnCount;
            }
        }
        
        //if there are none of the required cards on the board, take the line indicated by the turn card
        if(rows[0] == 0 && rows[1] == 0 && rows[2] == 0 && cols[0] == 0 && cols[1] == 0 && cols[2] == 0){
            lineOptions.add(card.getLine());
            return lineOptions;
        }

        //find the row or column with the most of the required card
        int rowMax = 0;
        int colMax = 0;
        for(int i = 0; i < 3; i++){
            if(rows[i] > rowMax) rowMax = rows[i];
            if(cols[i] > colMax) colMax = cols[i];
        }

        //decide on a row or column based on the turn card, if they are tied
        if(rowMax == colMax){
            if(card.getDirection().equals("row")){
                colMax--;
            }
            else{
                rowMax--;
            }
        }

        //find all line options
        String[] lines;
        int[] values;
        int max;
        if(rowMax > colMax){
            lines = new String[]{"d","e","f"};
            values = rows;
            max = rowMax;
        }
        else{
            lines = new String[]{"a","b","c"};
            values = cols;
            max = colMax;
        }
        for(int i = 0; i < 3; i++){
            if(values[i] == max){
                lineOptions.add(lines[i]);
            }
        }
        return lineOptions;
    }

    /**
     * Draws cards from the board and adds them to the cat player's hand.
     * 
     * @param board The game board.
     * @param hand The cat player's hand.
     * @param line The line of cards to take.
     * @return void
     */
    public void performAction(Board board, Hand hand, String line){
        List<Card> cards = board.takeLine(line);
        for(Card card: cards){
            hand.addCard(card);
        }
    }

    /**
     * Sets the turn card deck for the cat player action.
     * 
     * This method is primarily used in testing to provide a custom deck of turn cards
     * for the cat player. It allows for controlled testing scenarios.
     *
     * @param customDeck The custom TurnCardDeck to be set for the cat player action.
     * @return void
     */
    public void setTurnCardDeck(TurnCardDeck customDeck) {
        this.turnCardDeck = customDeck;
    }

}