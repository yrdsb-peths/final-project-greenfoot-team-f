import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the HighScoreScreen. When the user clicks the highScore button,
 * the screen will switch to this screen showing the amount of levels the 
 * user has completed.
 */
public class HighScoreScreen extends World
{
    public HighScoreScreen(int level)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        
        //Adding images to background.
        setBackground(new GreenfootImage("HighScore_1.png"));
        
        GreenfootImage textImage = new GreenfootImage("Highest Level Reached: " + level, 28, Color.WHITE, new Color(0, 0, 0, 0));
        getBackground().drawImage(textImage, (getWidth() - textImage.getWidth()) / 2, (getHeight() - textImage.getHeight()) / 2);
        
        GreenfootImage newRecordIcon = new GreenfootImage("newRecordIcon.png");
        getBackground().drawImage(newRecordIcon, (getWidth() - newRecordIcon.getWidth()) / 2 - 160, (getHeight() / 2) + 40);
        
        //Adding back button
        addObject(new Button(this:: goScreenSelect, "backButton.png", "backButton.png"),41,30);
    }
    
    public void goScreenSelect()
    {
        Greenfoot.setWorld(new ScreenSelect());
    }
}
