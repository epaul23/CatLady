/*
 * This file contains the ContinuedTurnObserver interface.
 */

package model;

/**
 * An interface that represents observers of a continued turn.
 * 
 * @author Serena Davis
 * @version 3-26-2025
 */
public interface ContinuedTurnObserver {
    /**
     * Recieve a notification when a turn continues past one action.
     * 
     * @return void
     */
    void continuedTurnNotify();
}
