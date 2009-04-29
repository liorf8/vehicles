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
    private long time_elapsed_at_pause = 0;

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

    public void reset(){
    	this.startTime = System.currentTimeMillis();
    }
    
    public void stop() {
        this.stopTime = System.currentTimeMillis();
        this.running = false;
    }

    public void pause(){
    	time_elapsed_at_pause = System.currentTimeMillis() - this.startTime;
    }
    
    public void unPause(){
    	this.startTime = time_elapsed_at_pause;
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
    
    /**
     * A method to get the elapsed time in seconds
     * @return Elapsed time in seconds
     */
    public double getElapsedTimeDoubleSecs() {
        double elapsed;
        if (running) {
            elapsed = ((double)(System.currentTimeMillis() - startTime) / 1000);
        }
        else {
            elapsed = ((double)(stopTime - startTime) / 1000);
        }
        return elapsed;
    }
    
    public double getElapsedTimeAtPause(){
    	return (double)(this.time_elapsed_at_pause / 1000);
    }
}

