import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Kenny extends Actor implements Enemy
{
    // Constants and variables for gravity, velocity, and jumping
    private final int gravity = 1;  // Gravity value for downward acceleration
    private int velocity = 0;  // Current vertical velocity
    private boolean isJumping = false;  // Indicates if Kenny is currently jumping
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
     
    private int idleCounter = 0;     // Counter for idle duration
    private final int idleDuration = 120; // Idle duration in frames (2 seconds at 60 FPS)
    
    // Direction and state variables
    private boolean facingRight = false;  // Indicates if Kenny is facing right
    private boolean isAttacking = false;  // Indicates if Kenny is currently attacking
    private boolean isIdle = false; // Indicates if Kenny is idle
    
    private MainPlayer player;  // Reference to the main player
    private int health = 100; // enemy health
    
    private GreenfootSound attackSound = new GreenfootSound("kennyAttack.mp3");  // Change sound if necessary
    
    public Kenny(MainPlayer player) 
    {
        this.player = player;

        // Load all animation frames
        idleFrames = loadFrames("kennyIdle_", 8);  // 8 frames (00 to 07)
        walkFrames = loadFrames("kennyRun_", 4);  // 4 frames (00 to 03)
        attackFrames = loadFrames("kennyAttack_", 10);  // 10 frames (00 to 09)
        jumpFrames = loadFrames("kennyJump_", 7);  // 7 frames (00 to 06)
        
        attackSound.setVolume(80);
        
        setImage(idleFrames[0]);  // Set initial image to the first idle frame
    }

    public void act() 
    {
        if (isJumping) 
        {
            fall(); // Handle falling and reset jumping state when on the ground
            animate(); // Play jump animation
        } 
        else if (isIdle) 
        {
            handleIdle(); // Handle idle state logic
        } 
        else 
        {
            handleJump(); // Handle jumping (override other actions)
            if (!isJumping) // Only execute other actions if not jumping
            {
                fall(); // Apply gravity
                followPlayer(); // Follow the player's position
                handleAttack(); // Handle attacking behavior
                animate(); // Animate based on current state
    
                // Randomly trigger idle state (0.66% chance every frame)
                if (!isAttacking && Greenfoot.getRandomNumber(150) < 1) 
                {
                    enterIdleState();
                }
            }
        }
    }
    
    private void enterIdleState() 
    {
        isIdle = true;
        idleCounter = idleDuration; // Set counter to idle duration
    }
    
    private void handleIdle() 
    {
        idleCounter--;  // Decrease idle counter
    
        // Play idle animation during idle state
        if (idleCounter % animationSpeed == 0) 
        {
            idleFrameIndex = (idleFrameIndex + 1) % idleFrames.length;
            setImage(flipIfNeeded(idleFrames[idleFrameIndex]));
        }
    
        // Exit idle state when counter reaches zero
        if (idleCounter <= 0) 
        {
            isIdle = false;
        }
    }
    
    //method to load animation frames
    private GreenfootImage[] loadFrames(String baseName, int count) 
    {
        GreenfootImage[] frames = new GreenfootImage[count];
        for (int i = 0; i < count; i++) 
        {
            // Format the index with leading zeroes (e.g., "00", "01", etc.)
            String formattedIndex = String.format("%02d", i);
            frames[i] = new GreenfootImage(baseName + formattedIndex + ".png");
        }
        return frames;
    }

    // Method to make Kenny follow the player's position
    public void followPlayer() 
    {
        if (!isAttacking && !isJumping && !isIdle)  // Only follow if not attacking, jumping, or idle
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

    private void handleJump() 
    {
        if (!isJumping && jumpCooldown <= 0 && isOnSolidGround()) // Only jump if on solid ground
        {
            if (Greenfoot.getRandomNumber(120) < 1) // Adjust chance to jump
            {
                jump();
            }
        } 
        else if (jumpCooldown > 0) 
        {
            jumpCooldown--; // Decrease jump cooldown
        }
    }

    private void jump() 
    {
        velocity = -18;  // Initial upward velocity
        isJumping = true;  // Set jumping state to true
        jumpFrameIndex = 0;  // Reset jump animation frame
        jumpCooldown = 30; 
    }

    private void fall() 
    {
        setLocation(getX(), getY() + velocity); // Apply vertical velocity
    
        if (isOnSolidGround()) // Check if Kenny is on solid ground
        {
            velocity = 0; // Stop downward motion
            isJumping = false; // Reset jumping state
        } 
        else 
        {
            velocity += gravity; // Apply gravity
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

    public void performAttack() 
    {
        int x = getX();  // Start X position of the projectile
        int y = getY() - 20;  // Slightly higher than Kenny's position

        if (facingRight)  // Determine projectile's direction
        {
            x += 40;  // Offset to the right
        } 
        else 
        {
            x -= 40;  // Offset to the left
        }

        // Create and add the projectile to the world
        EnemyProjectile projectile = new EnemyProjectile("kennyProjectile_01.png", facingRight, 25);
        attackSound.play(); 
        getWorld().addObject(projectile, x, y);
    }

    private void animate() 
    {
        animationDelay++; // Increment the animation delay counter
    
        if (animationDelay >= animationSpeed) 
        {
            if (isAttacking) 
            {
                if (attackFrameIndex < attackFrames.length) 
                {
                    setImage(flipIfNeeded(attackFrames[attackFrameIndex]));
                    
                    // Trigger the projectile attack after the 5th frame
                    if (attackFrameIndex == 5) 
                    {
                        performAttack(); // Fire the projectile
                    }
    
                    attackFrameIndex++;
    
                    // Reset attack state after completing the animation
                    if (attackFrameIndex == attackFrames.length) 
                    {
                        isAttacking = false;
                        attackCooldown = 60; // Reset the attack cooldown
                    }
                }
            } 
            else if (isJumping) 
            {
                if (jumpFrameIndex < jumpFrames.length) 
                {
                    setImage(flipIfNeeded(jumpFrames[jumpFrameIndex]));
                    jumpFrameIndex++;
                }
            } 
            else if (Math.abs(player.getX() - getX()) > 5) 
            {
                walkAnimationDelay++;
                if (walkAnimationDelay >= walkAnimationSpeed) 
                {
                    walkFrameIndex = (walkFrameIndex + 1) % walkFrames.length;
                    setImage(flipIfNeeded(walkFrames[walkFrameIndex]));
                    walkAnimationDelay = 0;
                }
            } 
            else 
            {
                idleFrameIndex = (idleFrameIndex + 1) % idleFrames.length;
                setImage(flipIfNeeded(idleFrames[idleFrameIndex]));
            }
    
            animationDelay = 0; // Reset the animation delay counter
        }
    }



    private GreenfootImage flipIfNeeded(GreenfootImage frame) 
    {
        GreenfootImage image = new GreenfootImage(frame);
        if (!facingRight) 
        {
            image.mirrorHorizontally();
        }
        return image;
    }
    
    public void setFacingRight(boolean facingRight)
    {
        this.facingRight = facingRight;
        setImage(flipIfNeeded(getImage())); 
    }
    
    public boolean isOnSolidGround()
    {
        int imageWidth = getImage().getWidth();
        int imageHeight = getImage().getHeight();
    
        Actor platform = getOneObjectAtOffset(0, imageHeight / 2, Platform.class);
        if (platform != null)
        {
            setLocation(getX(), platform.getY() - platform.getImage().getHeight() / 2 - imageHeight / 2);
            return true;
        }

        if (getY() >= 370 - imageHeight / 2)
        {
            setLocation(getX(), 370 - imageHeight / 2);
            return true;
        }
    
        return false;
    }

    public int getHealth()
    {
        return health;
    }
    
    public void takeDamage(int damage)
    {
        health -= damage;
        if (health <= 0)
        {
            health = 0;
            MusicManager.stopThreeMusic();
            getWorld().removeObject(this);
        }
    }
}
