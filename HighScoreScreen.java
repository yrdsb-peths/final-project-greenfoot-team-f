import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HighScoreScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HighScoreScreen extends World
{
    /**
     * Constructor for objects of class HighScoreScreen.
     * 
     */
    public HighScoreScreen(int level)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        
        setBackground(new GreenfootImage("HighScore_1.png"));
        
        GreenfootImage textImage = new GreenfootImage("Highest Level Reached: " + level, 28, Color.WHITE, new Color(0, 0, 0, 0));
        getBackground().drawImage(textImage, (getWidth() - textImage.getWidth()) / 2, (getHeight() - textImage.getHeight()) / 2);
        
        GreenfootImage newRecordIcon = new GreenfootImage("newRecordIcon.png");
        getBackground().drawImage(newRecordIcon, (getWidth() - newRecordIcon.getWidth()) / 2 - 160, (getHeight() / 2) + 40);
        
        addObject(new Button(this:: goScreenSelect, "backButton.png", "backButton.png"),41,30);
        
        
    }
    
    public void goScreenSelect()
    {
        Greenfoot.setWorld(new ScreenSelect());
    }
    
}
