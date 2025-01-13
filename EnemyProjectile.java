import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class EnemyProjectile extends Actor 
{
    private int speed = 5;
    private boolean movingRight;
    private int damage; // New field for damage
    private GreenfootSound damageSfx;

    public EnemyProjectile(String imageName, boolean movingRight, int damage) 
    {
        setImage(imageName); // Set the projectile image
        this.movingRight = movingRight;
        this.damage = damage; // Assign the damage value
        damageSfx = new GreenfootSound("dmgSfx.mp3");

        if (!movingRight) 
        {
            getImage().mirrorHorizontally(); // Flip the image for the left direction
        }
    }

    public void act() 
    {
        // Move the projectile
        if (movingRight) 
        {
            setLocation(getX() + speed, getY()); // Move to the right
        } 
        else 
        {
            setLocation(getX() - speed, getY()); // Move to the left
        }

        // Check collision with MainPlayer
        MainPlayer player = (MainPlayer) getOneIntersectingObject(MainPlayer.class);
        if (player != null) 
        {
            damageSfx.play();
            player.takeDamage(damage); // Use the damage field
            getWorld().removeObject(this); // Remove the projectile
            return; // Exit to prevent further execution
        }

        // Remove projectile if it goes off-screen
        if (isAtEdge()) 
        {
            getWorld().removeObject(this);
        }
    }
}
