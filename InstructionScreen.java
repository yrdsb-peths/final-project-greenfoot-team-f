import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class InstructionScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InstructionScreen extends World
{
    private GreenfootImage background;
    
    /**
     * Constructor for objects of class InstructionScreen.
     * 
     */
    public InstructionScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        setBackground(new GreenfootImage("better.png")); // Set the background
        
        addObject(new Button(this:: goFightStage, "fightButton.png", "fightButton.png"), 497,380);
        //button that proceeds to stage
    }
    
    public void act()
    {
        
    }
    
    public void goFightStage()
    {
        Greenfoot.setWorld(new FightWorld()); 
    }
}
