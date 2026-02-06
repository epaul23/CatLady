/*
 * This file contains the StandardDeck class. 
 */

package model;

import java.util.Collections;
import java.util.Random;

/**
 * A class representing a standard deck of cards.
 * 
 * @author Flora Campbell
 * @author Serena Davis
 * @version 4-1-2025
 */
public class StandardDeck extends Deck{
    /**
     * The number of cards to remove for a 1-3 player game.
     */
    private static final int ONE_TO_THREE_PLAYERS = 6;
    /**
     * The number of cards to remove for a 4 player game.
     */
    private static final int FOUR_PLAYERS = 4;

    /**
     * Class constructor adding cards the list of standard cards.
     */
    public StandardDeck(int numberOfPlayers){
        super();
        // Add all the cat cards to the deck
        cards.add(new CatCard("Pablo Picatso", "B", new String[]{"tuna","tuna","tuna"}, 6));
        cards.add(new CatCard("Sox", "W", new String[]{"tuna","tuna","milk"}, 6));
        cards.add(new CatCard("Blackberry", "B", new String[]{"chicken","chicken"}, 3));
        cards.add(new CatCard("Sir Cuddleface", "W", new String[]{"milk","milk"}, 4));
        cards.add( new CatCard("Bell", "O", new String[]{"chicken"}, 1));
        cards.add(new CatCard("Pumpkin", "O", new String[]{"chicken","chicken","milk"}, 6));
        cards.add(new CatCard("Sparkle", "W", new String[]{"tuna","tuna"}, 3));
        cards.add(new CatCard("Jazz", "B", new String[]{"tuna"}, 1));
        cards.add(new CatCard("Keaton", "B", new String[]{"milk","milk"}, 4));
        cards.add(new CatCard("Zeus", "O", new String[]{"chicken","chicken"}, 3));
        cards.add(new CatCard("Bronte", "O", new String[]{"tuna","tuna"}, 3));
        cards.add(new CatCard("Gershon", "W", new String[]{"chicken"}, 1));
        cards.add(new CatCard("Snowflake", "W", new String[]{"milk"}, 2));
        cards.add(new CatCard("Chairman Meow", "O", new String[]{"chicken","chicken","chicken"}, 6));
        cards.add(new CatCard("Shadow", "B", new String[]{"tuna"}, 1));
        // Add the food cards to the deck
        for (int i=0;i<7;i++){
            cards.add(new FoodCard("Food card", "chicken")); 
        }
        for (int i=0;i<7;i++){
            cards.add(new FoodCard("Food card", "tuna")); 
        }
        for (int i=0;i<5;i++){
            cards.add(new FoodCard("Food card", "milk")); 
        }
        for (int i=0;i<6;i++){
            cards.add(new FoodCard("Food card", "treat")); 
        }
        cards.add(new FoodCard("Food card", "2x milk"));
        for (int i=0;i<2;i++){
            cards.add(new FoodCard("Food card", "2x chicken"));
            cards.add(new FoodCard("Food card", "2x tuna")); 
            cards.add(new FoodCard("Food card", "wild"));
        }
        // Add toy cards to the deck, two of each type.
        for (int i=0;i<2;i++){
            cards.add(new ToyCard("Toy card", "mouse toy"));
            cards.add(new ToyCard("Toy card", "yarn ball")); 
            cards.add(new ToyCard("Toy card", "scratching post"));
            cards.add(new ToyCard("Toy card", "cat tower")); 
            cards.add(new ToyCard("Toy card", "feather wand")); 
        }
        // Add costume cards.
        for (int i=0;i<7;i++){
            cards.add(new Card("Costume card")); 
        }
        // Add lost cat cards.
        for (int i=0;i<8;i++){
            cards.add(new Card("Lost cat card")); 
        }
        //Add spray bottle cards.
        for (int i=0;i<3;i++){
            cards.add(new Card("Spray bottle card")); 
        } 
        //Add catnip cards.
        for (int i=0;i<3;i++){
            cards.add(new Card("Catnip card")); 
        } 
        cards.add(new Card("Laser pointer card"));

        //Add cards for 3+ players
        if(numberOfPlayers > 2){
            cards.add(new CatCard("Dinah","O+W",new String[]{"chicken","milk"},4));
            cards.add(new CatCard("Lily","B",new String[]{"milk"},2));
            cards.add(new CatCard("Luna","B+W",new String[]{"tuna","chicken"},4));
            cards.add(new CatCard("Alvin","B+O",new String[]{"tuna","milk"},4));
            cards.add(new CatCard("Chester","O",new String[]{"tuna"},1));
            cards.add(new CatCard("Cooper","W",new String[]{"chicken"},1));
            cards.add(new FoodCard("Food card","milk"));
            cards.add(new FoodCard("Food card","milk"));
            cards.add(new FoodCard("Food card","2x chicken"));
            cards.add(new FoodCard("Food card","2x tuna"));
            cards.add(new Card("Costume card"));
            cards.add(new Card("Lost cat card"));
            cards.add(new Card("Lost cat card"));
            cards.add(new Card("Spray bottle card"));
            cards.add(new Card("Catnip card"));
        }

        //Add cards for 4 players
        if(numberOfPlayers > 3){
            cards.add(new CatCard("Henriette","B+O+W",new String[]{"tuna","milk","chicken"},5));
            cards.add(new FoodCard("Food card","chicken"));
            cards.add(new FoodCard("Food card","tuna"));
            cards.add(new FoodCard("Food card","milk"));
            cards.add(new FoodCard("Food card","wild"));
            cards.add(new ToyCard("Toy card", "mouse toy"));
            cards.add(new ToyCard("Toy card", "yarn ball")); 
            cards.add(new ToyCard("Toy card", "scratching post"));
            cards.add(new ToyCard("Toy card", "cat tower")); 
            cards.add(new ToyCard("Toy card", "feather wand"));
            cards.add(new Card("Costume card"));
            cards.add(new Card("Spray bottle card"));
            cards.add(new Card("Catnip card"));
        }

        // Shuffle the cards in the deck 
        Collections.shuffle(cards);

        // Remove cards when instantiated.
        int removeNumber;
        if(numberOfPlayers < 4) removeNumber = ONE_TO_THREE_PLAYERS;
        else removeNumber = FOUR_PLAYERS;
        Random rand = new Random();
        for(int i = 0; i < removeNumber; i++){
            int rand_index = rand.nextInt(70);
            cards.remove(rand_index);
        }
    } 

    /** 
    * Check if the deck is empty or almost empty.
    * 
    * @return True if there are less than 3 cards in the deck, false otherwise.
    */
    @Override
    public boolean isEmpty(){
        return cards.size() < 3;
    }
}
