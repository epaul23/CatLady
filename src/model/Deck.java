/*
 * This file contains the Deck class. 
 */

package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * A class that represents a deck of cards.
 * 
 * @author Flora Campbell
 * @author Serena Davis
 * @version 3-30-2025
 */
public abstract class Deck {
    /**
     * Contains all the cards in the deck.
     */
    protected LinkedList<Card> cards;

    /**
     * Class constructor instantiating the list of cards.
     */
    public Deck(){
        cards = new LinkedList<>();
    }

    /**
    * Returns a specified number of cards from the deck. 
    * 
    * @param amount The number of cards to return.
    * @return A list of cards from the deck.
    */
    public List<Card> getCards(int amount){
        List<Card> returnCards = new ArrayList<>();
        for(int i = 0; i < amount; i++){
            try {
                Card card = cards.remove(0);
                returnCards.add(card);
            } catch (IndexOutOfBoundsException e) {
                return null;
            }
        }
        return returnCards;
    }

    /**
     * Checks whether the deck is empty.
     * 
     * @return True if the deck is empty, false otherwise.
     */
    public boolean isEmpty(){
        return cards.size() == 0;
    }

}
