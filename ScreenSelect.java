import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ScreenSelect here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScreenSelect extends World
{

    /**
     * Constructor for objects of class ScreenSelect.
     * 
     */
    public ScreenSelect()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        
        //addObject(new Button(this::goInstructions, "instructions.png", "instructions.png"), 499,371);
    }
    private void goInstructions()
    {
        Greenfoot.setWorld(new InstructionScreen());
    }
}
