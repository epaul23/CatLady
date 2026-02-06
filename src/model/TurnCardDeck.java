/*
 * This file contains the TurnCardDeck class.
 */

package model;

import java.util.Collections;
import java.util.List;

/**
 * A class representing a deck of turn cards.
 *
 * @author Asher Lawrence
 * @author Emil paul
 * @version 3-30-2025
 */
public class TurnCardDeck extends Deck{

    /**
     * Default constructor adding cards to the list of turn cards and shuffles them.
     */
     public TurnCardDeck() {
        super();
        initializeDeck();
        Collections.shuffle(cards);
    }

    /**
     * Constructor that accepts a list of custom TurnCards to initialize the deck used for Testing.
     * 
     * @param customCards A list of turn cards to be added to the deck.
     */
    public TurnCardDeck(List<TurnCard> customCards) {
        super();
        cards.addAll(customCards);
    }

    /**
     * Initializes the deck with a predefined set of turn cards.
     * 
     * @return void
     */
    private void initializeDeck() {
        cards.add(new TurnCard("Toy card","column","b"));
        cards.add(new TurnCard("Catnip card","column","c"));
        cards.add(new TurnCard("Catnip card","row","d"));
        cards.add(new TurnCard("Toy card","row","f"));
        cards.add(new TurnCard("Cat card","row","e"));
        cards.add(new TurnCard("Food card","column","a"));
        cards.add(new TurnCard("Food card","column","b"));
        cards.add(new TurnCard("Food card","row","e"));
        cards.add(new TurnCard("Cat card","column","a"));
    }

} 
