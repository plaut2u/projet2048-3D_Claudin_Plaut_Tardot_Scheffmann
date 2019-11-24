/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

/**
 * Interface ControlledScreen to use ScreensController
 * @author Gregoire
 */
public interface ControlledScreen {
    /**
    * Method to set the screen Parent of current screen.
    * @param screenPage the current screen
    */
    public void setScreenParent(ScreensController screenPage);
}
