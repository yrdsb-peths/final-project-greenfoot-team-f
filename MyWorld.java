import greenfoot.*;

public class MyWorld extends World {
    public MyWorld()
    {
        super(600, 400, 1);
        prepare(); 
    }
    
    private void prepare()
    {
        MainPlayer mainPlayer = new MainPlayer(); 
        addObject(mainPlayer, 200, 200); 
    }
}
