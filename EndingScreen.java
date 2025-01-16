import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class EndingScreen extends World
{
    private GreenfootImage[] images; // Array to store the images
    private int currentImageIndex = 0; // Current image being displayed
    private int fadeStep = 5; // How much to increase opacity per frame
    private int frameCounter = 0; // To count frames for timing
    private int fadeAlpha = 0; // Current alpha value for fading in
    private GreenfootImage currentImage; // Current image with applied alpha
    private int[] frameDurations = {240, 240, 240}; // Durations for each image in frames (4 seconds per image)
    
    private static GreenfootSound endingSpeech  = new GreenfootSound("endingSpeechNew.mp3");
    
    public EndingScreen()
    {    
        super(600, 400, 1); // Create a new world with dimensions 600x400
        
        MusicManager.stopAllMusic(); 
        MusicManager.playEndingTheme();
        endingSpeech.play(); 
        // Load the images into the array
        images = new GreenfootImage[3];
        images[0] = new GreenfootImage("endingFrame_00.png");
        images[1] = new GreenfootImage("endingFrame_01.png");
        images[2] = new GreenfootImage("endingFrameNew_00.png");

        // Initialize the current image to the first one
        currentImage = new GreenfootImage(images[currentImageIndex]);
        updateBackground(); // Set initial background
    }

    public void act()
    {
        if (currentImageIndex < images.length) 
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
                // Move to the next image
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
                    // Add the menu button when all images are displayed
                    addObject(new Button(this::goMenuScreen, "MenuButton.png", "MenuButton.png"), 70, 30);
                }
            }
        }
    }

    //For button that switches current screen to menu screen.
    public void goMenuScreen()
    {
        MusicManager.stopEndingTheme();
        MusicManager.playMenuMusic(); 
        Greenfoot.setWorld(new MenuScreen()); 
    }

    //Updates the background with the current image and alpha level.
    private void updateBackground()
    {
        GreenfootImage fadedImage = new GreenfootImage(currentImage);
        fadedImage.setTransparency(fadeAlpha);
        setBackground(fadedImage);
    }
}
