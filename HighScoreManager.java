import java.util.HashMap;
//This class manages the high score
public class HighScoreManager
{
    private static HashMap<String, Integer> highScores = new HashMap<>();

    public static void updateHighScore(String key, int level)
    {
        // Check if the key already exists in the high scores
        if (highScores.containsKey(key))
        {
            int currentHighScore = highScores.get(key);
            
            // Update the score only if the new level is greater
            if (level > currentHighScore)
            {
                highScores.put(key, level);
            }
        }
        else
        {
            // If the key does not exist, add it with the given level
            highScores.put(key, level);
        }
    }

    public static int getHighScore(String key)
    {
        return highScores.getOrDefault(key, 0); // Return 0 if no high score exists
    }
}
