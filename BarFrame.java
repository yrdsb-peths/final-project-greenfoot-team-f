import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BarFrame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BarFrame extends Actor
{
    public BarFrame(String imageName)
    {
        //this is an actor class that contains the differnt health bar frames of both player and enemy
        GreenfootImage healthFrame = new GreenfootImage(imageName);
        //healthFrame.scale(312,80);
        setImage(healthFrame); 
    }
    
}
