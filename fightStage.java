import greenfoot.*;

public class fightStage extends World {
    public fightStage()
    {
        super(600, 400, 1);
        
        setBackground("stage_1.png");
        prepare(); 
    }
    
    private void prepare()
    {
        MainPlayer mainPlayer = new MainPlayer(); 
        addObject(mainPlayer, 200, 350); 
    }
}
