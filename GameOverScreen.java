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
        GreenfootImage background = new GreenfootImage("gameOverScreenNew.png");
        background.scale(700,400);
        GreenfootImage gameOverImage = new GreenfootImage("GameOver.png");
        gameOverImage.scale(350, 80);
        
        background.drawImage(gameOverImage, (getWidth() - gameOverImage.getWidth()) / 2 - 120, (getHeight() - gameOverImage.getHeight()) / 2);

        setBackground(background);
        
        addObject(new Button(this::goScreenSelect, "levelSelectButton.png", "levelSelectButton.png"), 170,280);
                
        MusicManager.stopStageOneMusic();
        MusicManager.stopTwoMusic();
        MusicManager.stopThreeMusic(); 
        MusicManager.playLoseMusic(); 

    }
    
    private void goScreenSelect()
    {
        MusicManager.stopLoseMusic(); 
        MusicManager.playMenuMusic(); 
        Greenfoot.setWorld(new ScreenSelect());
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
