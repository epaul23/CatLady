/*
 * This file contains the LaserPointerObserver interface.
 */

package model;

/**
 * An interface that represents observers of the LaserPointerAction.
 * 
 * @author Serena Davis
 * @version 3-27-2025
 */
public interface LaserPointerObserver {
    /**
     * Recieve a notification when LaserPointerAction is used.
     * 
     * @return void
     */
    void notifyLaserUsed();
}
