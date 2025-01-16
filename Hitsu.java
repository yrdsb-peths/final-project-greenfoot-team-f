import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Hitsu extends Actor implements Enemy 
{
    private final int gravity = 1; // Gravity value for downward acceleration
    private int velocity = 0; // Current vertical velocity
    private boolean isJumping = false; // Indicates if Hitsu is currently jumping
    private int jumpFrameIndex = 0; // Tracks the current frame of jump animation

    private int speed = 1; // Movement speed
    private int attackCooldown = 0; // Cooldown for attacks
    private int jumpCooldown = 0; // Cooldown for jumps

    //ArrayLists for frames
    private GreenfootImage[] idleFrames;
    private GreenfootImage[] walkFrames;
    private GreenfootImage[] attackFrames;
    private GreenfootImage[] jumpFrames;
    
    //Animation state tracking
    private int idleFrameIndex = 0;
    private int walkFrameIndex = 0;
    private int attackFrameIndex = 0;

    //Animation speed and delay
    private int animationDelay = 0;
    private final int animationSpeed = 6;

    private int walkAnimationDelay = 0;
    private final int walkAnimationSpeed = 3;

    private int idleCounter = 0;
    private final int idleDuration = 120;
    
    //Direction
    private boolean facingRight = false;
    private boolean isAttacking = false;
    private boolean isIdle = false;

    private MainPlayer player;
    private int health = 100;

    private GreenfootSound attackSound = new GreenfootSound("hitsuAttack.mp3");
    private static GreenfootSound awakenSound = new GreenfootSound("newAwakenSound.mp3");

    public Hitsu(MainPlayer player) 
    {
        this.player = player;

        // Load sprites
        idleFrames = loadFrames("hitsuIdle_", 6); // 6 frames (00 to 05)
        walkFrames = loadFrames("hitsuRun_", 6); // 6 frames (00 to 05)
        attackFrames = loadFrames("hitsuAttackNew_", 10); // 10 frames (00 to 09)
        jumpFrames = loadFrames("hitsuJump_", 7); // 7 frames (00 to 06)

        attackSound.setVolume(80);

        setImage(idleFrames[0]); // Set initial image to the first idle frame
    }

    //Controls for Character animation
    public void act() 
    {
        if (isJumping) 
        {
            fall();
            animate();
        } 
        else if (isIdle) 
        {
            handleIdle();
        } 
        else 
        {
            handleJump();
            if (!isJumping) 
            {
                fall();
                followPlayer();
                handleAttack();
                animate();

                if (!isAttacking && Greenfoot.getRandomNumber(150) < 1) 
                {
                    enterIdleState();
                }
            }
        }
    }

    //Enter idle state and set a counter for its duration
    private void enterIdleState() 
    {
        isIdle = true;
        idleCounter = idleDuration;
    }

    //Handles idle animation and state exit
    private void handleIdle() 
    {
        idleCounter--;

        //Change frame at the animation speed
        if (idleCounter % animationSpeed == 0) 
        {
            idleFrameIndex = (idleFrameIndex + 1) % idleFrames.length;
            setImage(flipIfNeeded(idleFrames[idleFrameIndex]));
        }

        //Exit idle state when counter reaches to 0
        if (idleCounter <= 0) 
        {
            isIdle = false;
        }
    }

    //Load frames for animations based on a base name and count
    private GreenfootImage[] loadFrames(String baseName, int count) 
    {
        GreenfootImage[] frames = new GreenfootImage[count];
        for (int i = 0; i < count; i++) 
        {
            String formattedIndex = String.format("%02d", i);
            frames[i] = new GreenfootImage(baseName + formattedIndex + ".png");
        }
        return frames;
    }

    //Move Hitsu towards the player's position
    public void followPlayer() 
    {
        if (!isAttacking && !isJumping && !isIdle) 
        {
            int playerX = player.getX();
            int deltaX = playerX - getX();

            if (deltaX < 0) 
            {
                facingRight = false;
                setLocation(getX() - speed, getY());
            } 
            else if (deltaX > 0) 
            {
                facingRight = true;
                setLocation(getX() + speed, getY());
            }
        }
    }

    //Check if Hitsu should jump
    private void handleJump() 
    {
        if (!isJumping && jumpCooldown <= 0 && isOnSolidGround()) 
        {
            if (Greenfoot.getRandomNumber(120) < 1) 
            {
                jump();
            }
        } 
        else if (jumpCooldown > 0) 
        {
            jumpCooldown--;
        }
    }
    
    //Trigger jump logic
    private void jump() 
    {
        velocity = -18;
        isJumping = true;
        jumpFrameIndex = 0;
        jumpCooldown = 30;
    }

    //Apply gravity
    private void fall() 
    {
        setLocation(getX(), getY() + velocity);

        if (isOnSolidGround()) 
        {
            velocity = 0;
            isJumping = false;
        } 
        else 
        {
            velocity += gravity;
        }
    }

    //Handling attacks
    private void handleAttack() 
    {
        if (!isAttacking && attackCooldown <= 0) 
        {
            if (Greenfoot.getRandomNumber(100) < 5) 
            {
                isAttacking = true;
                attackFrameIndex = 0;
            }
        } 
        else if (attackCooldown > 0) 
        {
            attackCooldown--;
        }
    }

    public void performAttack() 
    {
        int x = getX();
        int y = getY() - 20;

        if (facingRight) 
        {
            x += 40; //Offset for right-facing direction
        } 
        else 
        {
            x -= 40; //Offset for left-facing direction
        }

        EnemyProjectile projectile = new EnemyProjectile("hitsuProjectileOne.png", facingRight, 25);
        attackSound.play();
        getWorld().addObject(projectile, x, y);
    }

    //Animate based on the current state
    private void animate() 
    {
        animationDelay++;

        if (animationDelay >= animationSpeed) 
        {
            if (isAttacking) 
            {
                //Play attack animation
                if (attackFrameIndex < attackFrames.length) 
                {
                    setImage(flipIfNeeded(attackFrames[attackFrameIndex]));

                    if (attackFrameIndex == 5) 
                    {
                        performAttack(); // Fire projectile mid-attack
                    }

                    attackFrameIndex++;

                    if (attackFrameIndex == attackFrames.length) 
                    {
                        isAttacking = false;
                        attackCooldown = 60;
                    }
                }
            } 
            else if (isJumping) 
            {
                //Play jump animation
                if (jumpFrameIndex < jumpFrames.length) 
                {
                    setImage(flipIfNeeded(jumpFrames[jumpFrameIndex]));
                    jumpFrameIndex++;
                }
            } 
            else if (Math.abs(player.getX() - getX()) > 5) 
            {
                //Play walk animation if far from player
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
                // Play idle animation if close to the player
                idleFrameIndex = (idleFrameIndex + 1) % idleFrames.length;
                setImage(flipIfNeeded(idleFrames[idleFrameIndex]));
            }

            animationDelay = 0;
        }
    }

    private GreenfootImage flipIfNeeded(GreenfootImage frame) 
    {
        GreenfootImage image = new GreenfootImage(frame);
        if (!facingRight) 
        {
            image.mirrorHorizontally(); //Flip image
        }
        return image;
    }
    
    //Set direction and update image orientation
    public void setFacingRight(boolean facingRight) 
    {
        this.facingRight = facingRight;
        setImage(flipIfNeeded(getImage()));
    }

    //Check if Hitsu is on the ground
    public boolean isOnSolidGround() 
    {
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
    
    //Update health
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
    
            // Stop the current stage music
            MusicManager.stopFourMusic(); // Replace with the correct method for stage four music
            awakenSound.play();
            // Trigger the fade-out transition via the world
            if (getWorld() instanceof FightWorldFour) 
            {
                FightWorldFour currentWorld = (FightWorldFour) getWorld();
                currentWorld.startFadeOut(); // Use FightWorldFour's fade logic
            }
    
            // Remove Hitsu from the world
            getWorld().removeObject(this);
        }
    }

}
