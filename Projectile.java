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
    
        // Check collision with Kisuke
        Kisuke kisuke = (Kisuke) getOneIntersectingObject(Kisuke.class);
        if (kisuke != null) 
        {
            kisuke.takeDamage(10); // Deal damage
            getWorld().removeObject(this); // Remove the projectile
            return; // Exit to prevent further execution
        }
    
        if (isAtEdge()) // Remove projectile if it goes off-screen
        {
            getWorld().removeObject(this);
        }
        
        Ichigo ichigo = (Ichigo) getOneIntersectingObject(Ichigo.class); 
        if(ichigo != null)
        {
            ichigo.takeDamage(10);
            getWorld().removeObject(this);
            return;
        }
    }

}
