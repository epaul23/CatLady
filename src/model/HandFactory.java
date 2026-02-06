/*
 * This file contains the HandFactory class.
 */

package model;

import java.util.List;

/**
 * A class that creates Hand objects.
 * 
 * @author Serena Davis
 * @version 1-4-2025
 */
public abstract class HandFactory {
    
    /**
     * Creates an array of Hands for the game based on the number of players.
     * 
     * @param numberOfPlayers The number of players.
     * @param strayCatDeck The stray cat deck.
     * @return An array of hands for the game.
     */
    public static Hand[] createHands(int numberOfPlayers, StrayCatDeck strayCatDeck){
        Hand[] hands;
        if(numberOfPlayers == 1){
            hands = new Hand[2];
            hands[0] = new Hand("player 1");
            hands[1] = createCatPlayerHand(strayCatDeck);
        }
        else {
            hands = new Hand[numberOfPlayers];
            for(int i = 0; i < numberOfPlayers; i++){
                hands[i] = new Hand("player "+(i+1));
            }
        }
        return hands;
    }

    /**
     * Creates a new Hand for a cat player by adding starting cards to give an advantage.
     * 
     * @param strayCatDeck The stray cat deck.
     * @return A new Hand for a cat player.
     */
    private static Hand createCatPlayerHand(StrayCatDeck strayCatDeck){
        Hand hand = new Hand("cat player");
        StartingCardDeck startingCardDeck = new StartingCardDeck();
        List<Card> cards = startingCardDeck.getCards(2);
        for(Card card: cards){
            if(((StartingCard) card).getTitle().equals("VP")){
                hand.addToken();
                hand.addToken();
            }
            else if(((StartingCard) card).getTitle().equals("Stray Cat")){
                hand.addCard(strayCatDeck.getExtraCard());
            }
            else if(((StartingCard) card).getTitle().equals("Catnip")){
                hand.addCard(new Card("Catnip card"));
            }
            else if(((StartingCard) card).getTitle().equals("Food")){
                hand.addCard(new FoodCard("Food card","wild"));
                hand.addCard(new FoodCard("Food card","wild"));
                hand.addCard(new FoodCard("Food card","wild"));
            }
            else if(((StartingCard) card).getTitle().equals("Costume")){
                hand.addCard(new Card("Costume card"));
                hand.addCard(new Card("Costume card"));
            }
            else if(((StartingCard) card).getTitle().equals("Toy")){
                hand.addCard(new ToyCard("Toy card","wild"));
            }
        }
        return hand;
    }
}
