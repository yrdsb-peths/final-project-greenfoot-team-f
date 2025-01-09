import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameCompleteScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameCompleteScreen extends World
{
    public GameCompleteScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        
        GreenfootImage background = new GreenfootImage("stage_1alt.png");
        
        GreenfootImage gameOverImage = new GreenfootImage("levelComplete.png");
        gameOverImage.scale(600, 130);
        
        background.drawImage(gameOverImage, (getWidth() - gameOverImage.getWidth()) / 2, (getHeight() - gameOverImage.getHeight()) / 2 - 50);

        setBackground(background);
        
        addObject(new Button(this::goScreenSelect, "menuButton.png", "menuButton.png"), 301,300);
        
        addObject(new Button(this:: goFightStage, "playAgain.png", "playAgain.png"), 301,250);
    }
    
    private void goScreenSelect()
    {
        Greenfoot.setWorld(new ScreenSelect());
    }

    private void goFightStage()
    {
        MusicManager.stopMenuMusic(); 
        Greenfoot.setWorld(new FightWorld()); 
    }
}
