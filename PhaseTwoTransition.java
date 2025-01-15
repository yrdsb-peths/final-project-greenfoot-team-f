import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class PhaseTwoTransition extends World
{
    private GreenfootImage[] images; // Array to store the images
    private int currentImageIndex = 0; // Current image being displayed
    private int fadeStep = 5; // How much to increase opacity per frame
    private int frameCounter = 0; // To count frames for timing
    private int fadeAlpha = 0; // Current alpha value for fading in
    private GreenfootImage currentImage; // Current image with applied alpha
    private int[] frameDurations = {120, 120, 180}; // Durations for each image in frames
    
    
    public PhaseTwoTransition()
    {    
        super(600, 400, 1); // Create a new world with dimensions 600x400
        
        // Load the images into the array
        images = new GreenfootImage[3];
        images[0] = new GreenfootImage("hitsuPhase_00.png");
        images[1] = new GreenfootImage("hitsuPhase_01.png");
        images[2] = new GreenfootImage("hitsuPhase_02.png");
        
        MusicManager.playFourMusicTwo();
        
        // Initialize the current image to the first one
        currentImage = new GreenfootImage(images[currentImageIndex]);
        updateBackground(); // Set initial background
    }

    public void act()
    {
        if (frameCounter < frameDurations[currentImageIndex]) 
        {
            // Handle the fading in
            fadeAlpha += fadeStep;
            if (fadeAlpha > 255) fadeAlpha = 255; // Clamp to max opacity
            updateBackground();
            frameCounter++;
        } 
        else 
        {
            // Move to the next image or transition to FightWorldFive
            currentImageIndex++;
            if (currentImageIndex < images.length) 
            {
                fadeAlpha = 0; // Reset fade alpha for the next image
                frameCounter = 0; // Reset frame counter
                currentImage = new GreenfootImage(images[currentImageIndex]);
                updateBackground(); // Set new background
            } 
            else 
            {
                // Transition to FightWorldFive
                Greenfoot.setWorld(new FightWorldFive());
            }
        }
    }

    /**
     * Updates the background with the current image and alpha level.
     */
    private void updateBackground()
    {
        GreenfootImage fadedImage = new GreenfootImage(currentImage);
        fadedImage.setTransparency(fadeAlpha);
        setBackground(fadedImage);
    }
}
