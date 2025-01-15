import java.util.HashMap;

public class HighScoreManager
{
    private static HashMap<String, Integer> highScores = new HashMap<>();

    public static void updateHighScore(String key, int level)
    {
        if (highScores.containsKey(key))
        {
            int currentHighScore = highScores.get(key);
            if (level > currentHighScore)
            {
                highScores.put(key, level);
            }
        }
        else
        {
            highScores.put(key, level);
        }
    }

    public static int getHighScore(String key)
    {
        return highScores.getOrDefault(key, 0); // Return 0 if no high score exists
    }
}
