package vehicles.util;

/**
 * A stopwatch class used for the simulation to get running time
 * @author graysr
 *
 */
public class StopWatch {
    
    private long startTime = 0;
    private long stopTime = 0;
    private boolean running = false;

    /**
     * Emprty Constructor for a StopWatch
     */
    public StopWatch(){
    	
    }
    
    public void addSecond(){
    	this.startTime -= 1000;
    }
    
    public void start() {
        this.startTime = System.currentTimeMillis();
        this.running = true;
    }

    
    public void stop() {
        this.stopTime = System.currentTimeMillis();
        this.running = false;
    }

    /**
     * A method to get the elapsed time in seconds
     * @return Elapsed time in seconds
     */
    public long getElapsedTimeSecs() {
        long elapsed;
        if (running) {
            elapsed = ((System.currentTimeMillis() - startTime) / 1000);
        }
        else {
            elapsed = ((stopTime - startTime) / 1000);
        }
        return elapsed;
    }
}

