import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class FadeOverlay extends Actor 
{
    private int opacity = 0;
    private boolean fadingOut = false;
    private int fadeSpeed = 2;
    private boolean fadeComplete = false;

    public FadeOverlay() 
    {
        GreenfootImage fadeImage = new GreenfootImage(600, 400);
        fadeImage.setColor(Color.BLACK);
        fadeImage.fill();
        fadeImage.setTransparency(opacity);
        setImage(fadeImage);
    }

    public void act() 
    {
        if (fadingOut) 
        {
            handleFadeOut();
        }
    }

    public void startFadeOut() 
    {
        fadingOut = true;
        opacity = 0; // Start fully transparent
        fadeComplete = false;
        getImage().setTransparency(opacity);
    }

    private void handleFadeOut() 
    {
        if (opacity < 255) 
        {
            opacity = Math.min(255, opacity + fadeSpeed); // Gradually increase opacity
            getImage().setTransparency(opacity);
        } 
        else 
        {
            fadingOut = false;
            fadeComplete = true; // Mark the fade as complete
        }
    }

    public boolean isFadeComplete() 
    {
        return fadeComplete;
    }
}
