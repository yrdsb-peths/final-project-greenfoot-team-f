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
    
    private int scrollX;    
    /**
     * Constructor for objects of class InstructionScreen.
     * 
     */
    public InstructionScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        showInstructions();
    }
    public void showInstructions()
    {
        //Text for instructions.
        String instructions = ("In this game, you must defeat the character\n"+
                                "in each stage to earn power ups and you have to conquer\n"+
                                "the stages to succeed. Plan your moves carefully,\n" +
                                "as every hit you take reduces your HP. If it runs out,\n" +
                                "you’ll die and have to restart from the very beginning.\n" +
                                "Use your power ups wisely to overcome tougher challenges\n"+ 
                                "as you progress through the stages!");
        
        //Calling the instruction screen.
        InstructionText instructionText = new InstructionText(instructions);
        addObject(instructionText, getWidth()/2, getHeight() / 2);
    }
}
