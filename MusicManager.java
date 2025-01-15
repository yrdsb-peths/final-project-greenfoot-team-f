import greenfoot.GreenfootSound;
import greenfoot.*;

public class MusicManager
{
    public static GreenfootSound menuMusic = new GreenfootSound("menuMusic.mp3");
    public static GreenfootSound selectMusic = new GreenfootSound("Home Screen.mp3"); 
    public static GreenfootSound oneMusic = new GreenfootSound("stageOneMusic.mp3");
    public static GreenfootSound loseMusic = new GreenfootSound("loseMusic.mp3");
    public static GreenfootSound twoMusic  = new GreenfootSound("stage2music.mp3");
    public static GreenfootSound threeMusic = new GreenfootSound("stage3MusicNew.mp3");
    public static GreenfootSound fourMusic = new GreenfootSound("stage4PhaseOneNew.mp3");
    public static GreenfootSound fourMusicTwo = new GreenfootSound("stage4PhaseTwo.mp3");
    
    
    public static void playFourMusicTwo()
    {
        fourMusicTwo.setVolume(60);
        if(!fourMusicTwo.isPlaying())
        {
            fourMusicTwo.playLoop();
        }
    }
    
    public static void stopFourMusicTwo()
    {
        if(fourMusicTwo.isPlaying())
        {
            fourMusicTwo.pause();
        }
    }
    
    public static void playFourMusic()
    {
        if(!fourMusic.isPlaying()) 
        {
            fourMusic.setVolume(60); 
            fourMusic.playLoop();
        }
    }
    
    public static void stopFourMusic()
    {
        if(fourMusic.isPlaying())
        {
            fourMusic.pause();
        }
    }
    
    
    public static void playThreeMusic()
    {
        if(!threeMusic.isPlaying())
        {
            threeMusic.setVolume(70);
            threeMusic.playLoop();
        }
    }
    
    public static void stopThreeMusic()
    {
        if(threeMusic.isPlaying())
        {
            threeMusic.pause();
        }
    }
    
    public static void playTwoMusic()
    {
        if(!twoMusic.isPlaying())
        {
            twoMusic.setVolume(60);
            twoMusic.playLoop(); 
        }
    }
    
    public static void stopTwoMusic()
    {
        if(twoMusic.isPlaying())
        {
            twoMusic.pause();
      
        }
    }
    
    public static void playLoseMusic()
    {
        if(!loseMusic.isPlaying())
        {
            loseMusic.setVolume(60);
            loseMusic.playLoop();
        }
    }
    
    public static void stopLoseMusic()
    {
        if(loseMusic.isPlaying())
        {
            loseMusic.pause(); 
       
        }
    }
    
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
            oneMusic.pause();
           
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
            selectMusic.pause();
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
            menuMusic.pause();
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
