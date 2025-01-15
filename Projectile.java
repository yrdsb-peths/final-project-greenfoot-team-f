import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Projectile extends Actor 
{
    private int speed;
    
    private static GreenfootSound kennyHurt = new GreenfootSound("kennyHurt.mp3");
    
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
        
        if (getWorld() == null) {
            return; // Skip further actions if the projectile is no longer in the world
        }
        
        // Check collision with Kisuke
        Kisuke kisuke = (Kisuke) getOneIntersectingObject(Kisuke.class);
        if (kisuke != null) 
        {
            kisuke.takeDamage(10); // Deal damage
            getWorld().removeObject(this); // Remove the projectile
            return; // Exit to prevent further execution
        }
        
        Ichigo ichigo = (Ichigo) getOneIntersectingObject(Ichigo.class); 
        if(ichigo != null)
        {
            ichigo.takeDamage(10);
            getWorld().removeObject(this);
            return;
        }
        
        Kenny kenny = (Kenny) getOneIntersectingObject(Kenny.class);
        if(kenny != null)
        {
            kenny.takeDamage(10);
            kennyHurt.play(); 
            getWorld().removeObject(this);
            return;
            
        }
        
        Hitsu hitsu = (Hitsu) getOneIntersectingObject(Hitsu.class);
        if(hitsu != null)
        {
            hitsu.takeDamage(10); 
            
            getWorld().removeObject(this);
            return; 
        }
        
        
        if (isAtEdge()) // Remove projectile if it goes off-screen
        {
            getWorld().removeObject(this);
        }
        
        
        
    }

}
