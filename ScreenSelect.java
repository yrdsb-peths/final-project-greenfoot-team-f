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
        
Scarlett
        addObject(new Button(this::goInstructions, "InstructionButton.png", "InstructionButton.png"), 499,371);

        // Instruction button
        addObject(new Button(this::goInstructions, "InstructionButton.png", "InstructionButton.png"), 499,371);
        
        //Fight/start game button
        addObject(new Button(this:: goFightStage, "fightButton.png", "fightButton.png"), 301,190);
main
    }
    private void goInstructions()
    {
        Greenfoot.setWorld(new InstructionScreen());
    }
    
    private void goFightStage()
    {
        Greenfoot.setWorld(new FightWorld()); 
    }
}
