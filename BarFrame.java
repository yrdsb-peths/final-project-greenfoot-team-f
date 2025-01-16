import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class BarFrame extends Actor
{
    public BarFrame(String imageName)
    {
        //this is an actor class that contains the differnt health bar frames of both player and enemy
        GreenfootImage healthFrame = new GreenfootImage(imageName);
        //healthFrame.scale(250,70);
        setImage(healthFrame); 
    }
    
}
