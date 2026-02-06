/*
 * This file contains Hand class
 */

package model;

import java.util.ArrayList;
import java.util.List;

/**
  * This class represents a hand of cards.
  * 
  * @author Emil Paul
  * @author Serena Davis
  * @author Flora Campbell
  * @version 3-30-2025
  */
 public class Hand {
    /**
     * A list of cat cards.
     */
    private ArrayList<Cat> catCardList;
    /**
     * A list if food cards.
     */
    private ArrayList<FoodCard> foodCardList;
    /**
     * A list of toy cards.
     */
    private ArrayList<ToyCard> toyCardList;
    /**
     * A list of all general cards.
     */
    private ArrayList<Card> cardList;
    /**
     * The number of tokens in the hand.
     */ 
    private int tokenCount;
    /**
     * The type of the hand: "player "+number for normal players, "cat player" for the cat player.
     */
    private String type;

    /**
     * Constructor for Hand class.
     * Initializes empty lists and set all counts to zero.
     * 
     * @param type The type of hand.
     */
    public Hand(String type){
        this.catCardList = new ArrayList<>();
        this.foodCardList = new ArrayList<>();
        this.toyCardList = new ArrayList<>();
        this.cardList = new ArrayList<>();
        this.tokenCount = 0;
        this.type = type;
    }

    /**
     * Returns the type of hand.
     * 
     * @return "cat player" if it is the cat player's hand, "player" and the player's number otherwise.
     */
    public String getType(){
        return type;
    }

    /**
     * Adds a card to appropriate list based on its type.
     * 
     * @param card The card to be added.
     * @return void
     */
    public void addCard(Card card){
        String type = card.getType();
        if(type.equals("Cat") || type.equals("Stray cat")){
            catCardList.add((Cat) card);
        }
        else if(type.equals("Food card")){
            if(((FoodCard) card).getFoodType().substring(0,2).equals("2x")){
                foodCardList.add(new FoodCard("Food card",((FoodCard) card).getFoodType().substring(3)));
                foodCardList.add(new FoodCard("Food card",((FoodCard) card).getFoodType().substring(3)));
            }
            else{
                foodCardList.add((FoodCard) card);
            }
        }
        else if(type.equals("Toy card")){
            toyCardList.add((ToyCard) card);
        }
        else {
            cardList.add(card);
        }
    }

    /**
     * Removes a card specified by card type from the hand.
     * 
     * @param cardType The type of the card to remove.
     * @return void
     */
    public void removeCard(String cardType){
        boolean found = false;
        int index = 0;
        while(!found && index<cardList.size()){
            if(cardList.get(index).getType().equals(cardType)){
                cardList.remove(index);
                found = true;
            }
            else{
                index++;
            }
        }
    }

    /**
     * Method that converts everything in player's hand to String.
     * 
     * @return A String that contains contents of the hand.
     */
    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();

        sb.append("Your Hand contains:\n");
        sb.append("\nCat cards:\n");
        for(Cat cat:catCardList){
            sb.append(cat.toString()+"\n");
        }

        sb.append("\nFood cards: ");
        for(int i=0;i<foodCardList.size();i++){
            if (i < foodCardList.size()-1){
                sb.append(foodCardList.get(i).getFoodType()).append(", ");
            } else{
                sb.append(foodCardList.get(i).getFoodType());
            }
        }
        sb.append("\nToy cards: ");
        for(int i=0;i<toyCardList.size();i++){
            if (i < toyCardList.size()-1){
                sb.append(toyCardList.get(i).getToyType()).append(", ");
            } else{
                sb.append(toyCardList.get(i).getToyType());
            }
        }
        sb.append("\nOther cards: ");
        for(int i=0;i<cardList.size();i++){
            if (i < cardList.size()-1){
                sb.append(cardList.get(i).getType()).append(", ");
            } else{
                sb.append(cardList.get(i).getType());
            }
        }
        sb.append("\nTokens: ").append(tokenCount);
        sb.append("\n");
        return sb.toString();
    }
    
    /**
     * Increments the token count.
     * 
     * @return void
     */
    public void addToken(){
        tokenCount++;
    }

    /**
     * Method that returns the token count.
     * 
     * @return The current token count.
     */
    public int getTokenCount(){
        return tokenCount;
    }

    /**
     * Returns the number of a specified type of card in the hand.
     * 
     * @param cardType The card type to check the number of.
     * @return The number of cards of that type.
     */
    public int getNumberOf(String cardType){
        int count = 0;
        for(Card card: cardList){
            if(card.getType().equals(cardType)){
                count++;
            }
        }
        return count;
    }

    /**
     * Returns the list of cat cards.
     * 
     * @return The list of cat cards.
     */
    public ArrayList<Cat> getCatCards() {
        return catCardList;
    } 

    /**
     * Returns the list of toy cards.
     * 
     * @return The list of toy cards.
     */
    public ArrayList<ToyCard> getToyCards() {
        return toyCardList;
    }

    /**
     * Returns the list of food cards.
     * 
     * @return The list of food cards.
     */
    public ArrayList<FoodCard> getFoodCards() {
        return foodCardList;
    }

    /**
     * Returns the list of general cards.
     * 
     * @return The list of general cards.
     */
    public ArrayList<Card> getCards(){
        return cardList;
    }

    /**
     * Sets the list of cat cards.
     * 
     * @param cats The new list of cat cards.
     * @return void
     */
    public void setCatList(List<Cat> cats) {
        this.catCardList = new ArrayList<>(cats);
    }

    /**
     * Sets the list of food cards.
     * 
     * @param food The new list of food cards.
     * @return void
     */
    public void setFoodList(List<FoodCard> food) {
        this.foodCardList = new ArrayList<>(food);
    }
    
 }