import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameOverScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOverScreen extends World
{
    
    public GameOverScreen()
    {    
    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        GreenfootImage background = new GreenfootImage("stage_1alt.png");
        
        GreenfootImage gameOverImage = new GreenfootImage("GameOver.png");
        gameOverImage.scale(600, 150);
        
        background.drawImage(gameOverImage, (getWidth() - gameOverImage.getWidth()) / 2, (getHeight() - gameOverImage.getHeight()) / 2 - 50);

        setBackground(background);
        
        addObject(new Button(this::goScreenSelect, "MenuButton.png", "MenuButton.png"), 301,300);
        
        addObject(new Button(this:: goFightStage, "PlayAgain.png", "PlayAgain.png"), 301,250);
        
        MusicManager.stopStageOneMusic();
        MusicManager.stopTwoMusic();
        MusicManager.playLoseMusic(); 

    }
    
    private void goScreenSelect()
    {
        MusicManager.stopLoseMusic(); 
        MusicManager.playMenuMusic(); 
        Greenfoot.setWorld(new MenuScreen());
    }

    private void goFightStage()
    {
        MusicManager.stopLoseMusic();
        Greenfoot.setWorld(new FightWorld()); 
    }
    
    public void stopped()
    {
         MusicManager.stopLoseMusic(); 
    }
    
    public void started()
    {
         MusicManager.playLoseMusic();  
    }
    
}
