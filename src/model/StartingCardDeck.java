/*
 * This file contains the StartingCardDeck class.
 */

 package model;

import java.util.Collections;

/**
  * A class representing a deck of starting cards.
  *
  * @author Asher Lawrence
  * @version 3-30-2025
  */
 public class StartingCardDeck extends Deck {
 
     /**
      * Constructor to create the starting card deck.
      */
     public StartingCardDeck() {
         super();
         cards.add(new StartingCard("Starting card", "VP"));
         cards.add(new StartingCard("Starting card", "Stray Cat"));
         cards.add(new StartingCard("Starting card", "Catnip"));
         cards.add(new StartingCard("Starting card", "Food"));
         cards.add(new StartingCard("Starting card", "Costume"));
         cards.add(new StartingCard("Starting card", "Toy"));
         Collections.shuffle(cards);
     }
 }
 