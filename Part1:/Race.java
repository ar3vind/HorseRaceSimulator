import java.util.concurrent.TimeUnit;

/**
 * A three-horse race, each horse running in its own lane
 * for a given distance
 * 
 * @author Aravind Binuraj
 * @version 1.1
 */
public class Race
{

    private int raceLength;
    private Horse lane1Horse;
    private Horse lane2Horse;
    private Horse lane3Horse;
    
    
    /**
     * Constructor for objects of class Race
     * Initially there are no horses in the lanes
     * 
     * @param distance the length of the racetrack (in metres/yards...)
     */
    public Race(int distance)
    {
        // initialise instance variables
        raceLength = distance;
        lane1Horse = null;
        lane2Horse = null;
        lane3Horse = null;
    }
    
    
    
    /**
     * Adds a horse to the race in a given lane
     * 
     * @param theHorse the horse to be added to the race
     * @param laneNumber the lane that the horse will be added to
     */
    public void addHorse(Horse theHorse, int laneNumber)
    {
        if (laneNumber == 1)
        {
            lane1Horse = theHorse;
        }
        else if (laneNumber == 2)
        {
            lane2Horse = theHorse;
        }
        else if (laneNumber == 3)
        {
            lane3Horse = theHorse;
        }
        else
        {
            System.out.println("Not possible to add horse to " + laneNumber);
        }
    }
    
    /**
     * Start the race
     * The horse are brought to the start and
     * then repeatedly moved forward until the 
     * race is finished
     */
    public void startRace()
{
    boolean finished = false; // tells user that race is finished
    Horse winningHorse = null;  // tracking the winner

    lane1Horse.goBackToStart();
    lane2Horse.goBackToStart();   // resetting everything
    lane3Horse.goBackToStart();
                  
    while (true)  // changed to true so I can use break statements
    {
        // move each horse
        moveHorse(lane1Horse);
        moveHorse(lane2Horse);
        moveHorse(lane3Horse);
                    
        printRace();
        
        if (allHorsesFallen()) {
            System.out.println("All the horses fell. No winner :(");
            printHorseConfidence(); // Print confidence after the race
            return;
        }

        // if any of the three horses has won the race is finished
        if (raceWonBy(lane1Horse) || raceWonBy(lane2Horse) || raceWonBy(lane3Horse))
        {
            finished = true;

            if (raceWonBy(lane1Horse))
            {
                winningHorse = lane1Horse;
            }
            else if (raceWonBy(lane2Horse))
            {
                winningHorse = lane2Horse;
            }
            else 
            {
                winningHorse = lane3Horse;
            }
        
            System.out.println("The winner is " + winningHorse.getName() + "!!!!!");
            printHorseConfidence(); // Print confidence after the race
            return;
        }

        // wait for 100 milliseconds
        try { 
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {}
    }
}

/**
 * Print the confidence of each horse after the race.
 */
private void printHorseConfidence()
{
    System.out.println("\nHorse Confidence Levels:");
    if (lane1Horse != null) {
        System.out.println("Lane 1 Horse (" + lane1Horse.getName() + "): " + lane1Horse.getConfidence());
    }
    if (lane2Horse != null) {
        System.out.println("Lane 2 Horse (" + lane2Horse.getName() + "): " + lane2Horse.getConfidence());
    }
    if (lane3Horse != null) {
        System.out.println("Lane 3 Horse (" + lane3Horse.getName() + "): " + lane3Horse.getConfidence());
    }
}
    
    private boolean allHorsesFallen(){                       // what if all horses fall
        return lane1Horse != null && lane1Horse.hasFallen() &&
            lane2Horse != null && lane2Horse.hasFallen() &&
            lane3Horse != null && lane3Horse.hasFallen();
    }
    
    /**
     * Randomly make a horse move forward or fall depending
     * on its confidence rating
     * A fallen horse cannot move
     * 
     * @param theHorse the horse to be moved
     */
    private void moveHorse(Horse theHorse)
    {

        if (theHorse == null) return; // attempting to skip and empty lane


        //if the horse has fallen it cannot move, 
        //so only run if it has not fallen
        if  (!theHorse.hasFallen())
        {
            //the probability that the horse will move forward depends on the confidence;
            if (Math.random() < theHorse.getConfidence())
            {
               theHorse.moveForward();
            }
            
            //the probability that the horse will fall is very small (max is 0.1)
            //but will also will depends exponentially on confidence 
            //so if you double the confidence, the probability that it will fall is *2
            if (Math.random() < (0.1*theHorse.getConfidence()*theHorse.getConfidence()))
            {
                theHorse.fall();
            }
        }
    }
        
    /** 
     * Determines if a horse has won the race
     *
     * @param theHorse The horse we are testing
     * @return true if the horse has won, false otherwise.
     */
    private boolean raceWonBy(Horse theHorse)
    {
        if (theHorse == null)
        {
            return false; // adding this to avoid null pointer exception
        }

        if (theHorse.getDistanceTravelled() == raceLength)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /***
     * Print the race on the terminal
     */
    private void printRace()
    {
        System.out.print('\u000C');  //clear the terminal window
        
        multiplePrint('=',raceLength+3); //top edge of track
        System.out.println();
        
        printLane(lane1Horse);
        System.out.println();
        
        printLane(lane2Horse);
        System.out.println();
        
        printLane(lane3Horse);
        System.out.println();
        
        multiplePrint('=',raceLength+3); //bottom edge of track
        System.out.println();    
    }
    
    /**
     * print a horse's lane during the race
     * for example
     * |           X                      |
     * to show how far the horse has run
     */
    private void printLane(Horse theHorse)
    {
        
        if (theHorse == null)
        {
            multiplePrint(' ', raceLength); // attempting to print empty lane if no horse is detected
            System.out.print('|');
            return; // 
        }
        //calculate how many spaces are needed before
        //and after the horse
        int spacesBefore = theHorse.getDistanceTravelled();
        int spacesAfter = raceLength - theHorse.getDistanceTravelled();
        
        //print a | for the beginning of the lane
        System.out.print('|');
        
        //print the spaces before the horse
        multiplePrint(' ',spacesBefore);
        
        //if the horse has fallen then print dead
        //else print the horse's symbol
        if(theHorse.hasFallen())
        {
            System.out.print('X');  // changed to X to show horse has fallen
        }
        else
        {
            System.out.print(theHorse.getSymbol());
        }
        
        //print the spaces after the horse
        multiplePrint(' ',spacesAfter);
        
        //print the | for the end of the track
        System.out.print('|');
    }
        
    
    /***
     * print a character a given number of times.
     * e.g. printmany('x',5) will print: xxxxx
     * 
     * @param aChar the character to Print
     */
    private void multiplePrint(char aChar, int times)
    {
        int i = 0;
        while (i < times)
        {
            System.out.print(aChar);
            i = i + 1;
        }
    }
}
