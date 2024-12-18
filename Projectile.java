import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Projectile extends Actor 
{
    private int speed;

    public Projectile(boolean facingRight) 
    {
        setImage("blueArrow.png"); // Set the projectile image
        if (facingRight) 
        {
            this.speed = 8; // Move to the right
        } 
        else 
        {
            this.speed = -8; // Move to the left
            getImage().mirrorHorizontally(); // Flip the image for the left direction
        }
    }

    public void act() 
    {
        setLocation(getX() + speed, getY()); // Move the projectile

        if (isAtEdge()) // Remove projectile if it goes off-screen
        {
            getWorld().removeObject(this);
        }
    }
}
