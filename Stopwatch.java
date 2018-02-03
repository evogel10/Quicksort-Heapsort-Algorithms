/**
 * This class functions a stopwatch to to the amount of time, in nanoseconds,
 * that it takes run each sort.
 * 
 * @version 28 April 2016
 * @author Eric Vogel
 *
 */
public class Stopwatch {
    private long startStopwatch;
    private long stopStopwatch;
    private long elapsedTime = 0;

    /**
     * Stopwatch constructor setting the values to zero.
     */
    public Stopwatch() {
        this.startStopwatch = 0;
        this.stopStopwatch = 0;
        this.elapsedTime = 0;
    }

    /**
     * Method used to start the stopwatch.
     */
    public void start() {
        startStopwatch = System.nanoTime();
    }

    /**
     * Method used to stop the stopwatch.
     */
    public void stop() {
        if (startStopwatch != 0) {
            stopStopwatch = System.nanoTime();
            elapsedTime = stopStopwatch - startStopwatch;
        }
    }

    /**
     * Method used to return the elapsed time.
     * 
     * @return
     */
    public long split() {
        return elapsedTime;
    }
}