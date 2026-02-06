/*
 * This file contains the StrayCatDeck class. 
 */

package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * A class representing the deck of stray cat cards.
 * 
 * @author Flora Campbell
 * @author Serena Davis
 * @author Emil Paul
 * @version 4-1-2025
 */
public class StrayCatDeck {
  /**
   * The cards in the deck.
   */
  private LinkedList<StrayCatCard> cards;
  /**
   * The extra stray cat cards not part of the actual deck.
   */
  private LinkedList<StrayCatCard> extras;
  /**
   * The number of cards in the deck for a one player game.
   */
  private static final int ONE_PLAYER_SIZE = 2;
  /**
   * The number of cards in the deck for a general game (>1 players).
   */
  private static final int GENERAL_SIZE = 3;

  /**
   * Class constructor adding cards the list of stray cat cards.
   */
  public StrayCatDeck(int numberOfPlayers){
       cards = new LinkedList<>();
       extras = new LinkedList<>();
       //Add stray cat cards
       cards.add(new StrayCatCard("LeVar Purrton", "W", new String[]{"tuna","chicken"},"4 VP."));
       cards.add(new StrayCatCard("Truffle", "B", new String[]{"tuna","tuna"},"2 VP."));
       cards.add(new StrayCatCard("Antoinette", "W", new String[]{"tuna","milk"},"2 VP for each white cat you feed."));
       cards.add(new StrayCatCard("Moonbeam", "W", new String[]{"tuna","chicken"},"3 VP."));
       cards.add(new StrayCatCard("Macak", "O", new String[]{"milk"},"2 VP."));
       cards.add(new StrayCatCard("Penny", "O", new String[]{"milk"},"1 VP for each toy you have."));
       cards.add(new StrayCatCard("Florence", "O", new String[]{"chicken","milk"},"2 VP for each orange cat you feed."));
       cards.add(new StrayCatCard("Hemingway", "B", new String[]{"milk"},"7P if you feed 4 or more cats, 3VP otherwise."));
       cards.add(new StrayCatCard("Waffle", "O", new String[]{"tuna"},"3 VP."));
       cards.add(new StrayCatCard("Eliot", "B", new String[]{"tuna","chicken"},"2 VP for each black cat you feed."));
       cards.add(new StrayCatCard("Cow", "W", new String[]{"milk"},"2 VP."));
       cards.add(new StrayCatCard("Zoroaster", "B", new String[]{"chicken","chicken"},"2 VP for each costume in your hand."));
       cards.add(new StrayCatCard("Sweetheart", "B+O+W", new String[]{"tuna","chicken"},"5 VP"));
       
       int deckSize;
       if(numberOfPlayers == 1){
         deckSize = ONE_PLAYER_SIZE;
       }
       else{
         deckSize = GENERAL_SIZE;
       }
       // Remove cards from the deck, only a small number of cards are used from this deck.
       for (int i=0;i<(13-deckSize);i++){
           Random rand = new Random();
           int rand_index = rand.nextInt(cards.size());
           extras.add(cards.remove(rand_index));
       }
  } 

    /**
     * Returns all of the stray cat cards currently in the deck.
     * 
     * @return A list of all the stray cat cards in the deck.
     */
    public List<StrayCatCard> getCards(){
      return cards;
    }

    /**
     * Returns whether the deck is empty.
     * 
     * @return True if there are no more cards in the deck, false otherwise.
     */
    public boolean isEmpty(){
      return cards.size() == 0;
    }

    /**
    * Returns the stray cat card specified by the current player and removes it from the deck.
    * 
    * @param name The requested cat's name.
    * @return The requested card from the deck. 
    */
    public StrayCatCard getCard(String name){
      if(!cards.isEmpty()){
        for(StrayCatCard card : cards){
          if(name.equalsIgnoreCase(card.getName())){
            cards.remove(card);
            return card;
          }
        }
      }
      return null;
    }

    /**
     * Returns a stray cat card that is not in the deck.
     * 
     * @return An extra stray cat card.
     */
    public StrayCatCard getExtraCard(){
      Random rand = new Random();
      int rand_index = rand.nextInt(extras.size());
      return extras.get(rand_index);
    }

}
    

