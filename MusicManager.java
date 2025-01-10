import greenfoot.GreenfootSound;

public class MusicManager
{
    private static GreenfootSound menuMusic = new GreenfootSound("menuMusic.mp3");
    private static boolean isPlaying = false;
    private static GreenfootSound selectMusic = new GreenfootSound("Home Screen.mp3"); 
    private static GreenfootSound oneMusic = new GreenfootSound("stageOneMusic.mp3");
    
    public static void playStageOneMusic()
    {
        if (!isPlaying)
        {
            oneMusic.setVolume(50);
            oneMusic.playLoop();
            isPlaying = true;
        }
    }
    
    public static void stopStageOneMusic()
    {
        if (isPlaying)
        {
            oneMusic.stop();
            isPlaying = false;
        }
    }
    
    public static void pauseStageOneMusic()
    {
        if (isPlaying)
        {
            oneMusic.pause();
        }
    }
    
    
    public static void playSelectMusic()
    {
        if (!isPlaying)
        {
            selectMusic.setVolume(50);
            selectMusic.playLoop();
            isPlaying = true;
        }
    }
    
    
    public static void stopSelectMusic()
    {
        if (isPlaying)
        {
            selectMusic.stop();
            isPlaying = false;
        }
    }
    
    public static void pauseSelectMusic()
    {
        if (isPlaying)
        {
            selectMusic.pause();
        }
    }
    
    
    public static void playMenuMusic()
    {
        if (!isPlaying)
        {
            menuMusic.setVolume(50);
            menuMusic.playLoop();
            isPlaying = true;
        }
    }

    public static void stopMenuMusic()
    {
        if (isPlaying)
        {
            menuMusic.stop();
            isPlaying = false;
        }
    }

    public static void pauseMenuMusic()
    {
        if (isPlaying)
        {
            menuMusic.pause();
        }
    }

    public static void resumeMenuMusic()
    {
        if (!isPlaying)
        {
            menuMusic.playLoop();
            isPlaying = true;
        }
    }

    public static void resetMusicState()
    {
        if (menuMusic != null)
        {
            menuMusic.stop();
        }
        isPlaying = false;
    }
}
