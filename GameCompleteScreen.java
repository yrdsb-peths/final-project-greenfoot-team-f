import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class GameCompleteScreen extends World
{
    public GameCompleteScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        
        //Adding images to background
        GreenfootImage background = new GreenfootImage("stage_1alt.png");
        
        GreenfootImage gameOverImage = new GreenfootImage("levelComplete.png");
        gameOverImage.scale(600, 130);
        
        background.drawImage(gameOverImage, (getWidth() - gameOverImage.getWidth()) / 2, (getHeight() - gameOverImage.getHeight()) / 2 - 50);

        setBackground(background);
        
        //Add buttons
        addObject(new Button(this::goScreenSelect, "MenuButton.png", "MenuButton.png"), 301,300);
        
        addObject(new Button(this:: goFightStage, "PlayAgain.png", "PlayAgain.png"), 301,250);
    }
    
    private void goScreenSelect()
    {
        MusicManager.playMenuMusic();
        Greenfoot.setWorld(new MenuScreen());
    }

    private void goFightStage()
    { 
        Greenfoot.setWorld(new FightWorld()); 
    }
}
