import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class FadeOverlay extends Actor
{
    private int opacity = 0;
    private boolean fadingIn = false;
    private boolean fadingOut = false;
    private int fadeSpeed = 2;

    public FadeOverlay()
    {
        // Create a fully black image to cover the entire screen
        GreenfootImage fadeImage = new GreenfootImage(600, 400);
        fadeImage.setColor(Color.BLACK);
        fadeImage.fill();
        fadeImage.setTransparency(opacity);
        setImage(fadeImage);
    }

    public void act()
    {
        if (fadingIn)
        {
            handleFadeIn();
        }
        else if (fadingOut)
        {
            handleFadeOut();
        }
    }

    public void startFadeIn()
    {
        fadingIn = true;
        fadingOut = false;
        opacity = 255; // Start fully opaque
        getImage().setTransparency(opacity);
    }

    public void startFadeOut()
    {
        fadingOut = true;
        fadingIn = false;
        opacity = 0; // Start fully transparent
        getImage().setTransparency(opacity);
    }

    private void handleFadeIn()
    {
        if (opacity > 0)
        {
            opacity -= fadeSpeed;
            getImage().setTransparency(opacity);
        }
        else
        {
            fadingIn = false;
        }
    }

    private void handleFadeOut()
    {
        if (opacity < 255)
        {
            opacity = Math.min(255, opacity + fadeSpeed); // Ensure it doesn't exceed 255
            getImage().setTransparency(opacity);
        }
        else
        {
            fadingOut = false;
            Greenfoot.setWorld(new FightWorldTwo()); // Switch worlds after fade completes
        }
    }


}
