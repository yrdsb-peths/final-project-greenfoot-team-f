import greenfoot.GreenfootSound;

public class MusicManager
{
    private static GreenfootSound menuMusic = new GreenfootSound("menuMusic.mp3");
    private static boolean isPlaying = false;

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
