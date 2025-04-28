
/**
 * Write a description of class Horse here.
 * 
 * @author (Aravind Binuraj) 
 * @version (1.0)
 */
public class Horse
{
    //Fields of class Horse
    private char symbol; //A single Unicode character that represents the horse
    private String name; //Name of the horse
    private double confidence; //Confidence of the horse
    private int distanceTravelled; //Distance travelled by the horse
    private boolean hasFallen; //Whether the horse has fallen or not
    
      
    //Constructor of class Horse
    /**
     * Constructor for objects of class Horse
     */
    public Horse(char horseSymbol, String horseName, double horseConfidence)
    {
        this.symbol = horseSymbol;
        this.name = horseName;
        this.confidence = horseConfidence;
        this.distanceTravelled = 0; // Because horse has not moved
        this.hasFallen = false; // Because horse has not fallen yet

    }
    
    
    
    //Other methods of class Horse
    public void fall()
    {
        hasFallen = true; // Sets horse to be fallen
    }
    
    public double getConfidence()
    {
        return confidence; // Returning confidence of the horse
    }
    
    public int getDistanceTravelled()
    {
        return distanceTravelled; // Returning distance travelled
    }
    
    public String getName()
    {
        return name; // Returning name
    }
    
    public char getSymbol()
    {
        return symbol; // Returning symbol shown on the track
    }
    
    public void goBackToStart()
    {
        distanceTravelled = 0; // Making the distance 0 which is the start
        hasFallen = false; // Resetting the fall of the horse
    }
    
    public boolean hasFallen()
    {
        return hasFallen; // returning if the horse has fallen or not
    }

    public void moveForward()
    {
        distanceTravelled ++; // If horse has not fallen keep moving forward
        
        
    }

    public void setConfidence(double newConfidence)
    {
        if (newConfidence < 0) {
            confidence = 0;
        } else if (newConfidence > 1) {
            confidence = 1;
        } else {
            confidence = newConfidence;
        }
    }
    
    public void setSymbol(char newSymbol)
    {
        symbol = newSymbol;
    }
    
}
