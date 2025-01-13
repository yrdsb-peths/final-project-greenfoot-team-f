import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class FadeOverlay extends Actor
{
    private int opacity = 0;
    private boolean fadingOut = false;
    private World targetWorld;
    private int fadeSpeed = 2;

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

    public void startFadeOut(World nextWorld)
    {
        fadingOut = true;
        opacity = 0; // Start fully transparent
        targetWorld = nextWorld; // Set the target world for the transition
        getImage().setTransparency(opacity);
    }

    private void handleFadeOut()
    {
        if (opacity < 255)
        {
            opacity = Math.min(255, opacity + fadeSpeed); // Increase opacity
            getImage().setTransparency(opacity);
        }
        else
        {
            fadingOut = false;
            Greenfoot.setWorld(targetWorld); // Switch to the target world
        }
    }
}

