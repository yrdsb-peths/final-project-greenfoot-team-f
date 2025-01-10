import greenfoot.GreenfootSound;
import greenfoot.*;

public class MusicManager
{
    public static GreenfootSound menuMusic = new GreenfootSound("menuMusic.mp3");
    public static GreenfootSound selectMusic = new GreenfootSound("Home Screen.mp3"); 
    public static GreenfootSound oneMusic = new GreenfootSound("stageOneMusic.mp3");
    
    public static void playStageOneMusic()
    {
        if (!oneMusic.isPlaying())
        {
            oneMusic.setVolume(50);
            oneMusic.playLoop();
        }
    }
    
    public static void stopStageOneMusic()
    {
        if (oneMusic.isPlaying())
        {
            oneMusic.stop();
        }
    }
    
    public static void pauseStageOneMusic()
    {
        if (oneMusic.isPlaying())
        {
            oneMusic.pause();
        }
    }
    
    
    public static void playSelectMusic()
    {
        if (!selectMusic.isPlaying())
        {
            selectMusic.setVolume(50);
            selectMusic.playLoop();
        }
    }
    //48
    
    public static void stopSelectMusic()
    {
        if (selectMusic.isPlaying())
        {
            selectMusic.stop();
        }
    }
    
    public static void pauseSelectMusic()
    {
        if (selectMusic.isPlaying())
        {
            selectMusic.pause();
        }
    }
    
    
    public static void playMenuMusic()
    {
        if (!menuMusic.isPlaying())
        {
            menuMusic.setVolume(50);
            menuMusic.playLoop();
        }
    }

    public static void stopMenuMusic()
    {
        if (menuMusic.isPlaying())
        {
            menuMusic.stop();
        }
    }

    public static void pauseMenuMusic()
    {
        if (menuMusic.isPlaying())
        {
            menuMusic.pause();
        }
    }


}
