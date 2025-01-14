import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Stack;
/**
 * Write a description of class InstructionScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InstructionScreen extends World
{
    private GreenfootImage background;
    private int index;
    private Stack<World> screenHistory; //Stack to keep track of screen history
    /**
     * Constructor for objects of class InstructionScreen.
     * 
     */
    public InstructionScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        GreenfootImage background = new GreenfootImage("better.png");
        
        GreenfootImage uryuImage = new GreenfootImage("uryuImage.png");
        uryuImage.scale(780, 400);
        background.drawImage(uryuImage, (getWidth() - uryuImage.getWidth()) / 2 + 170, (getHeight() - uryuImage.getHeight()) / 2 + 20);
        
        GreenfootImage instructionText1 = new GreenfootImage("instructionText1.png");
        instructionText1.scale(300, 300);
        instructionText1.setTransparency(230);
        background.drawImage(instructionText1, (getWidth() - instructionText1.getWidth()) / 2 - 120, (getHeight() - instructionText1.getHeight()) / 2);
        
        setBackground(background);
        //button that proceeds to stage
        addObject(new Button(this:: goScreenSelect, "backButton.png", "backButton.png"), 35,51);
        buttons();
    }
    
    public void buttons()
    {
        Button nextButton = new Button(this::nextInstruction, "backButton.png", "backButton.png");
        addObject(nextButton, 550, 360);
        
        Button prevButton = new Button(this::prevInstruction, "backButton.png", "backButton.png");
        addObject(prevButton, 50, 360);
    }
    
    public void nextInstruction()
    {
        GreenfootImage background = new GreenfootImage("better.png");
        
        GreenfootImage ichigoImage = new GreenfootImage("ichigoImage.png");
        ichigoImage.scale(700, 480);
        background.drawImage(ichigoImage, (getWidth() - ichigoImage.getWidth()) / 2 - 100, (getHeight() - ichigoImage.getHeight()) / 2 - 30);
        
        GreenfootImage instructionText2 = new GreenfootImage("instructionText2.png");
        instructionText2.scale(300, 300);
        instructionText2.setTransparency(230);
        background.drawImage(instructionText2, (getWidth() - instructionText2.getWidth()) / 2 + 170, (getHeight() - instructionText2.getHeight()) / 2);
        
        setBackground(background);
    }
    
    public void prevInstruction()
    {
        GreenfootImage background = new GreenfootImage("better.png");
        
        GreenfootImage uryuImage = new GreenfootImage("uryuImage.png");
        uryuImage.scale(780, 400);
        background.drawImage(uryuImage, (getWidth() - uryuImage.getWidth()) / 2 + 170, (getHeight() - uryuImage.getHeight()) / 2 + 20);
        
        GreenfootImage instructionText1 = new GreenfootImage("instructionText1.png");
        instructionText1.scale(300, 300);
        instructionText1.setTransparency(230);
        background.drawImage(instructionText1, (getWidth() - instructionText1.getWidth()) / 2 - 120, (getHeight() - instructionText1.getHeight()) / 2);
        
        setBackground(background);
    }
    
    public void goFightStage()
    {
        Greenfoot.setWorld(new FightWorld()); 
    }
    
    public void goScreenSelect()
    {
        Greenfoot.setWorld(new ScreenSelect());
    }
}
