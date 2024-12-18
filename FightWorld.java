import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.GreenfootSound;
/**
 * Write a description of class FightWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FightWorld extends World
{
    private GreenfootSound backgroundMusic;
    
    public FightWorld()
    {    
        super(600, 400, 1);

        backgroundMusic = new GreenfootSound("stageOneMusic.mp3");

        // Uncomment to play background music when not in the initial world
        // backgroundMusic.playLoop();
        backgroundMusic.setVolume(50);

        setBackground("stage_1.png");
        prepare();
    }
    
    
    public void stopped()
    {
        // Pause music when the world is stopped
        backgroundMusic.pause();
    }
    
    public void started()
    {
        // Resume music when the world starts
        backgroundMusic.playLoop();
    }

    private void prepare()
    {
        MainPlayer mainPlayer = new MainPlayer();
        addObject(mainPlayer, 200, 350);

        // Add Kisuke enemy to the stage
        Kisuke kisukeEnemy = new Kisuke(mainPlayer);
        addObject(kisukeEnemy, 400, 350); // Position Kisuke on the stage
        
        kisukeEnemy.setFacingRight(false);
        
        //add platform
        Platform platform = new Platform();
        addObject(platform, 450, 260); 
    }
    
}
