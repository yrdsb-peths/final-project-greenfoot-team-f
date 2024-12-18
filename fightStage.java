import greenfoot.*;
import greenfoot.GreenfootSound;

public class FightStage extends World {
    
    private GreenfootSound backgroundMusic;
    
    public FightStage()
    {
        super(600, 400, 1);
        
        backgroundMusic = new GreenfootSound("stageOneMusic.mp3"); 
        
        //add back when not initial world
        //backgroundMusic.playLoop();
        backgroundMusic.setVolume(50); 
        
        setBackground("stage_1.png");
        prepare(); 
    }
    
    public void stopped()
    {
        // pause music when the world stopped
        backgroundMusic.pause();
    }
    
    public void started()
    {
        // Resume music when the world started 
        backgroundMusic.playLoop();
    }
    
   
    private void prepare()
    {
        MainPlayer mainPlayer = new MainPlayer(); 
        addObject(mainPlayer, 200, 350); 
    }
}
