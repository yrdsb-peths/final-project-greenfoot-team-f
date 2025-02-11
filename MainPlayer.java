import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MainPlayer extends Actor
{
    // Gravity value applied to simulate falling
    private final int gravity = 1;
    private int velocity;

    // Animation frame arrays
    private GreenfootImage[] idleFrames;
    private GreenfootImage[] runFrames;
    private GreenfootImage[] jumpFrames;
    private GreenfootImage[] attackFrames;

    private int currentFrame; // Tracks the current frame for animation
    private int animationCounter; // Controls the speed of animation

    private boolean facingRight; // Tracks the direction the player is facing

    // Attack animation variables
    private boolean isAttacking = false;
    private int attackFrameIndex = 0;

    // Jump animation variables
    private boolean isJumping;
    private int jumpFrameIndex;
    
    private int health = 100; //PLAYER HEALTH *****
    
    // Constructor to initialize player state and load animations
    public MainPlayer()
    {
        velocity = 0;
        currentFrame = 0;
        animationCounter = 0;

        //Load animation frames for various states
        idleFrames = loadFrames("playerIdle_", 4);
        runFrames = loadFrames("run_", 4);
        jumpFrames = loadFrames("jump_", 6);
        attackFrames = loadFrames("attack_", 8);

        facingRight = true; // Default direction
        setImage(idleFrames[0]);
    }

    // Act method is called every frame
    public void act()
    {
        fall();
        if (Greenfoot.isKeyDown("space") && isOnSolidGround())
        {
            jump(); // Initiate a jump if space is pressed and on solid ground
            Greenfoot.playSound("jumpSfx.mp3");
        }

        move();
        animate();
    }

    // Loads animation frames into an array
    private GreenfootImage[] loadFrames(String baseName, int count)
    {
        GreenfootImage[] frames = new GreenfootImage[count];
        for (int i = 0; i < count; i++)
        {
            frames[i] = new GreenfootImage(baseName + (i + 1) + ".png");
        }
        return frames;
    }

    // Handles the player's jump logic
    private void jump()
    {
        velocity = -18;
        isJumping = true;
        jumpFrameIndex = 0;
    }
    
    // Handles falling logic with gravity
    public void fall()
    {
        setLocation(getX(), getY() + velocity); // Update the player's vertical position

        if (isOnSolidGround())
        {
            velocity = 0;
        }
        else
        {
            velocity += gravity;
        }
    }
    
    // Handles horizontal movement and direction changes
    public void move()
    {
        int y = getY();// Keep the same vertical position
        int x = getX(); // Get the current horizontal position

        if (Greenfoot.isKeyDown("a")) // Move left
        {
            x -= 3;
            if (facingRight)
            {
                facingRight = false;
            }
        }

        if (Greenfoot.isKeyDown("d")) // Move right
        {
            x += 3;
            if (!facingRight)
            {
                facingRight = true;
            }
        }

        setLocation(x, y); // Update the player's position
    }

    // Checks if the player is standing on solid ground
    public boolean isOnSolidGround()
    {
        int imageWidth = getImage().getWidth();
        int imageHeight = getImage().getHeight();
    
        // Check if the player is standing on a platform
        Actor platform = getOneObjectAtOffset(0, imageHeight / 2, Platform.class);
        if (platform != null)
        {
            // Snap to the top of the platform
            setLocation(getX(), platform.getY() - platform.getImage().getHeight() / 2 - imageHeight / 2);
            return true;
        }
    
        // Check if the player is on the ground (at 370)
        if (getY() >= 370 - imageHeight / 2)
        {
            setLocation(getX(), 370 - imageHeight / 2); // Snap to ground height
            return true;
        }
    
        return false;
    }

    // Handles all animations for the player
    private void animate()
    {
        animationCounter++;

        // Handle attack animation
        if (Greenfoot.isKeyDown("p") && !isAttacking)
        {
            isAttacking = true;
            attackFrameIndex = 0;
        }

        if (isAttacking)
        {
            if (animationCounter >= 6)
            {
                setImage(getFlippedFrame(attackFrames[attackFrameIndex]));
                attackFrameIndex++;

                if (attackFrameIndex >= attackFrames.length)
                {
                    isAttacking = false;
                    attackFrameIndex = 0;
                    fireProjectile();
                }

                animationCounter = 0;
            }
        }
        // Handle jump animation
        else if (isJumping)
        {
            if (animationCounter >= 6)
            {
                setImage(getFlippedFrame(jumpFrames[jumpFrameIndex]));
                jumpFrameIndex++;
                if (jumpFrameIndex >= jumpFrames.length)
                {
                    isJumping = false;
                    jumpFrameIndex = 0;
                }
                animationCounter = 0;
            }
        }
        else if (Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("d"))
        {
            if (animationCounter >= 6)
            {
                setImage(getFlippedFrame(runFrames[currentFrame]));
                currentFrame = (currentFrame + 1) % runFrames.length;
                animationCounter = 0;
            }
        }
        else // Handle idle animation
        {
            if (animationCounter >= 6)
            {
                setImage(getFlippedFrame(idleFrames[currentFrame]));
                currentFrame = (currentFrame + 1) % idleFrames.length;
                animationCounter = 0;
            }
        }
    }

    // Flips a frame horizontally if the player is facing left
    private GreenfootImage getFlippedFrame(GreenfootImage frame)
    {
        GreenfootImage image = new GreenfootImage(frame);
        if (!facingRight)
        {
            image.mirrorHorizontally();
        }
        return image;
    }

    // Fires a projectile from the player's position
    private void fireProjectile()
    {
        int x;
        int y = getY() - 20; // Slightly higher than the player's position
        
        if (facingRight) {
            x = getX() + 40; // Offset to the right
        } else {
            x = getX() - 40; // Offset to the left
        }
        
        Greenfoot.playSound("shootSfx.mp3");
        Projectile projectile = new Projectile(facingRight);
        getWorld().addObject(projectile, x, y);

    }
    
    // Reduces player's health and handles game over
    public void takeDamage(int damage)
    {
        health -= damage;
        if (health <= 0)
        {
            MusicManager.stopAllMusic();
            health = 0;
            Greenfoot.setWorld(new GameOverScreen());
        }
    }
    
    public int getHealth()
    {
        return health;
    }
}