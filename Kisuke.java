import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Kisuke extends Actor 
{
    // Constants and variables for gravity, velocity, and jumping
    private final int gravity = 1;  // Gravity value for downward acceleration
    private int velocity = 0;  // Current vertical velocity
    private boolean isJumping = false;  // Indicates if Kisuke is currently jumping
    private int jumpFrameIndex = 0;  // Tracks the current frame of jump animation

    // Speed and cooldown variables
    private int speed = 1;  // Movement speed
    private int attackCooldown = 0;  // Cooldown for attacks
    private int jumpCooldown = 0;  // Cooldown for jumps

    // Animation frames and indices
    private GreenfootImage[] idleFrames;  
    private GreenfootImage[] walkFrames;  
    private GreenfootImage[] attackFrames;  
    private GreenfootImage[] jumpFrames;  
    private int idleFrameIndex = 0;  // Tracks the current frame of idle animation
    private int walkFrameIndex = 0;  // Tracks the current frame of walking animation
    private int attackFrameIndex = 0;  // Tracks the current frame of attack animation

    private int animationDelay = 0; // Counter to slow down animation
    private final int animationSpeed = 6; //frames to wait before updating
    
    private int walkAnimationDelay = 0; // Counter to control walking animation speed
    private final int walkAnimationSpeed = 3; // Delay for walking animation (higher = slower)
    
    
    // Direction and state variables
    private boolean facingRight = false;  // Indicates if Kisuke is facing right
    private boolean isAttacking = false;  // Indicates if Kisuke is currently attacking

    private MainPlayer player;  // Reference to the main player

    public Kisuke(MainPlayer player) 
    {
        this.player = player;

        // Load all animation frames
        idleFrames = loadFrames("kisukeIdle_", 6);
        walkFrames = loadFrames("kisukeWalk_", 4);
        attackFrames = loadFrames("kisukeAttack_", 10);
        jumpFrames = loadFrames("kisukeJump_", 7);

        setImage(idleFrames[0]);  // Set initial image to the first idle frame
    }

    // Main act method called each frame
    public void act() 
    {
        fall();            // Apply gravity and handle jumping
        followPlayer();    // Follow the player's position
        handleJump();      // Randomly handle jumping
        handleAttack();    // Handle attacking behavior
        animate();         // Animate based on current state
    }

    // Utility method to load animation frames
    private GreenfootImage[] loadFrames(String baseName, int count) 
    {
        GreenfootImage[] frames = new GreenfootImage[count];
        for (int i = 0; i < count; i++) 
        {
            frames[i] = new GreenfootImage(baseName + i + ".png");
        }
        return frames;
    }

    // Method to make Kisuke follow the player's position
    private void followPlayer() 
    {
        if (!isAttacking && !isJumping)  // Only follow if not attacking or jumping
        {
            int playerX = player.getX();  // Get player's X position
            int deltaX = playerX - getX();  // Calculate horizontal distance to player

            if (deltaX < 0)  // If player is to the left
            {
                facingRight = false;
                setLocation(getX() - speed, getY());  // Move left
            } 
            else if (deltaX > 0)  // If player is to the right
            {
                facingRight = true;
                setLocation(getX() + speed, getY());  // Move right
            }
        }
    }

    // Randomly decides if Kisuke should jump
    private void handleJump() 
    {
        if (!isJumping && jumpCooldown <= 0)  // Ready to jump
        {
            if (Greenfoot.getRandomNumber(100) < 5)  // 5% chance to jump
            {
                jump();  // Execute jump
            }
        } 
        else if (jumpCooldown > 0) 
        {
            jumpCooldown--;  // Decrease jump cooldown
        }
    }

    
    private void jump() 
    {
        velocity = -18;  // Initial upward velocity
        isJumping = true;  // Set jumping state to true
        jumpFrameIndex = 0;  // Reset jump animation frame
    }

    
    private void fall() 
    {
        setLocation(getX(), getY() + velocity);  // Apply vertical velocity

        if (getY() > getWorld().getHeight() - 50)  // If on the ground
        {
            velocity = 0;  // Stop downward motion
            setLocation(getX(), getWorld().getHeight() - 50);  // Snap to ground level
            isJumping = false;  // End jumping state
            jumpCooldown = 60;  // Set jump cooldown (1 second at 60 FPS)
        } 
        else 
        {
            velocity += gravity;  // Increase velocity for gravity effect
        }
    }

   
    private void handleAttack() 
    {
        if (!isAttacking && attackCooldown <= 0)  // Ready to attack
        {
            if (Greenfoot.getRandomNumber(100) < 5)  // 5% chance to attack
            {
                isAttacking = true;
                attackFrameIndex = 0;  // Reset attack animation frame
            }
        } 
        else if (attackCooldown > 0) 
        {
            attackCooldown--;  // Decrease attack cooldown
        }
    }

    // Performs the projectile attack
    private void performAttack() 
    {
        int x = getX();  // Start X position of the projectile
        int y = getY() - 20;  // Slightly higher than Kisuke's position

        if (facingRight)  // Determine projectile's direction
        {
            x += 40;  // Offset to the right
        } 
        else 
        {
            x -= 40;  // Offset to the left
        }

        // Create and add the projectile to the world
        EnemyProjectile projectile = new EnemyProjectile("kisukeProjectile2.png", facingRight);
        getWorld().addObject(projectile, x, y);
    }

    
    private void animate() 
    {
        animationDelay++; // Increment the animation delay counter
    
        // Only update the animation when delay exceeds animationSpeed
        if (animationDelay >= animationSpeed) 
        {
            if (isAttacking) // Attack animation
            {
                if (attackFrameIndex < attackFrames.length) 
                {
                    setImage(flipIfNeeded(attackFrames[attackFrameIndex]));
                    attackFrameIndex++;
    
                    if (attackFrameIndex == 5) // Fire projectile midway through attack animation
                    {
                        performAttack();
                    }
    
                    if (attackFrameIndex == attackFrames.length) // End attack animation
                    {
                        isAttacking = false;
                        attackCooldown = 60; // Set attack cooldown
                    }
                }
            } 
            else if (isJumping) // Jump animation
            {
                if (jumpFrameIndex < jumpFrames.length) 
                {
                    setImage(flipIfNeeded(jumpFrames[jumpFrameIndex]));
                    jumpFrameIndex++;
                }
            } 
            else if (Math.abs(player.getX() - getX()) > 5) // Walking animation
            {
                walkAnimationDelay++;
                
                if (walkAnimationDelay >= walkAnimationSpeed) // Update frame only if delay exceeds speed
                {
                    walkFrameIndex = (walkFrameIndex + 1) % walkFrames.length;
                    setImage(flipIfNeeded(walkFrames[walkFrameIndex]));
                    walkAnimationDelay = 0; // Reset the walking animation delay counter
                }
                
            } 
            else // Idle animation
            {
                if (animationDelay >= animationSpeed) 
                {
                    idleFrameIndex = (idleFrameIndex + 1) % idleFrames.length;
                    setImage(flipIfNeeded(idleFrames[idleFrameIndex]));
                    animationDelay = 0; // Reset the general animation delay counter
                }
            }
            
            // Reset the delay counter after updating the frame
            animationDelay = 0;
        }
    }


    // Flips a frame horizontally if Kisuke is facing left
    private GreenfootImage flipIfNeeded(GreenfootImage frame) 
    {
        GreenfootImage image = new GreenfootImage(frame);
        if (!facingRight) 
        {
            image.mirrorHorizontally();
        }
        return image;
    }
    
    // Allows setting Kisuke's facing direction
    public void setFacingRight(boolean facingRight)
    {
        this.facingRight = facingRight;
        setImage(flipIfNeeded(getImage())); // Flip the current image if needed
    }
}
