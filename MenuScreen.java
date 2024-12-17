import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MenuScreen extends World
{
    private GreenfootImage pressSpaceImage; 
    private boolean growing = true; // Toggle for breathing animation
    private boolean spacePressed = false; // Track if space has been pressed
    
    private int originalWidth = 136; // Original width for the press space image
    private int originalHeight = 30; // Original height for the press space image
    private double scaleVar = 1.0; // Scale factor for breathing animation
    
    private int animationStep = 0; // Frame count for space bar animation
    private GreenfootSound selectSound = new GreenfootSound("menuSelectSound2.mp3"); // Selection sound
    
    private GreenfootSound backgroundMusic;
    
    
    public MenuScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels
        super(600, 400, 1); 
       
        backgroundMusic= new GreenfootSound("menuMusic.mp3");
        
        setBackground(new GreenfootImage("menuScreenbg.png")); // Set the background
        pressSpaceImage = new GreenfootImage("pressSpace2.png"); // Load the "Press Space" image
        getBackground().drawImage(pressSpaceImage, 235, 300); // Draw the "Press Space" image
    }
    
    /**
     * Handles animations and transitions in the menu screen
     */
    public void act()
    {
        if (!spacePressed) 
        {
            animateBreathingSpace(); // Handle breathing animation
            if (Greenfoot.isKeyDown("space")) // Detect space press
            {
                selectSound.play();
                spacePressed = true; // Mark that space was pressed

            }
        }
        else
        {
            animatePressedSpace(); // Handle transition animation
        }
    }
    
    public void stopped()
    {
        // pause music when the world stopped
        backgroundMusic.pause();
    }
    
    public void stopMus()
    {
        backgroundMusic.stop(); 
    }
    
    public void started()
    {
        // Resume music when the world started 
        backgroundMusic.playLoop();
    }
    
    
    private void animateBreathingSpace()
    {
        // Update scale factor based on growing/shrinking state
        if (growing)
        {
            scaleVar += 0.005; // Increase scale factor
            if (scaleVar >= 1.1) // Max scale reached
            {
                growing = false; // Switch to shrinking
            }
        }
        else
        {
            scaleVar -= 0.005; // Decrease scale factor
            if (scaleVar <= 0.9) // Min scale reached
            {
                growing = true; // Switch to growing
            }
        }
        
        // Calculate scaled dimensions
        int scaledWidth = (int)(originalWidth * scaleVar); 
        int scaledHeight = (int)(originalHeight * scaleVar); 
        
        // Create a scaled version of the "Press Space" image
        GreenfootImage scaledImage = new GreenfootImage(pressSpaceImage); 
        scaledImage.scale(scaledWidth, scaledHeight); 
        
        // Clear the current frame and redraw the background
        setBackground(new GreenfootImage("menuScreenbg.png"));
        
        // Calculate the position to center the scaled image
        int xPos = 235 - (scaledWidth - originalWidth) / 2; 
        int yPos = 300 - (scaledHeight - originalHeight) / 2; 
        getBackground().drawImage(scaledImage, xPos, yPos); // Draw the scaled image
    }

    /**
     * Animate the scaling and fading effect when space is pressed
     */
    private void animatePressedSpace()
    {
        if (animationStep < 60) // Perform animation for 1 second (60 frames)
        {
            // Calculate scaling and opacity
            double scaleVar = 1 + 0.02 * animationStep; // Scale up by 2% each frame
            int scaledWidth = (int)(originalWidth * scaleVar); 
            int scaledHeight = (int)(originalHeight * scaleVar); 
            int opacity = Math.max(0, 235 - (int)(4.25 * animationStep)); // Reduce opacity gradually
            
            // Create a scaled and transparent version of the image
            GreenfootImage scaledImage = new GreenfootImage(pressSpaceImage);
            scaledImage.scale(scaledWidth, scaledHeight);
            scaledImage.setTransparency(opacity);
            
            // Clear the current frame and redraw the background
            setBackground(new GreenfootImage("menuScreenbg.png"));
            
            // Calculate position to center the scaled image
            int xPos = 235 - (scaledWidth - originalWidth) / 2; 
            int yPos = 300 - (scaledHeight - originalHeight) / 2; 
            getBackground().drawImage(scaledImage, xPos, yPos); // Draw the scaled image
            
            animationStep++; // Move to the next frame
        }
        else
        {
            goFightStage(); // Transition to the next world after animation
        }
    }

    /**
     * Switch to the FightStage world
     */
    private void goFightStage()
    {
        stopMus();
        Greenfoot.setWorld(new FightStage()); // Switch to the fight stage
    }
}
