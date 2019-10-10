
/*
 * Report describes buffer occupancy in array per interval time
 */

package report;

/** 
 * Records the average buffer occupancy and its variance with format:
 * <p>
 * <Simulation time> <average buffer occupancy % [0..100]> <variance>
 * </p>
 * 
 * 
 */
import java.util.*;

import core.DTNHost;
import core.Settings;
import core.SimClock;
import core.UpdateListener;



public class BufferOccupancyArrayReport extends Report implements UpdateListener {

	
	/**
	 * Record occupancy every nth second -setting id ({@value}). 
	 * Defines the interval how often (seconds) a new snapshot of buffer
	 * occupancy is taken
	 */
	public static final String BUFFER_REPORT_INTERVAL = "occupancyInterval";
	/** Default value for the snapshot interval */
	public static final int DEFAULT_BUFFER_REPORT_INTERVAL1 = 3600;
	
	private double lastRecord = Double.MIN_VALUE;
	private int interval;
	
	private Map<DTNHost, List<Double>> bufferArray = new HashMap<DTNHost, List<Double>>();
	
	
	public BufferOccupancyArrayReport() {
		super();
		
		Settings settings = getSettings();
		if (settings.contains(BUFFER_REPORT_INTERVAL)) {
			interval = settings.getInt(BUFFER_REPORT_INTERVAL);
		} else {
			interval = -1; /* not found; use default */
		}
		
		if (interval < 0) { /* not found or invalid value -> use default */
			interval = DEFAULT_BUFFER_REPORT_INTERVAL1;
		}
	}
	
	
	public void updated(List<DTNHost> hosts) {
		if (isWarmup()) {
			return;
		}
		
		if (SimClock.getTime() - lastRecord >= interval) {
			lastRecord = SimClock.getTime();
			printLine(hosts);
			
		}
			
	}
	
	
	private void printLine(List<DTNHost> hosts) {
		
		for (DTNHost h : hosts ) {
			double temp = h.getBufferOccupancy();
			temp = (temp<=100.0)?(temp):(100.0);
			
			if (!bufferArray.containsKey(h)){
				
				List<Double> occupancy = new LinkedList<Double>();
				occupancy.add(temp);
				bufferArray.put(h,occupancy);
			}
						
			else {
				
				List<Double> occupancy = bufferArray.get(h);
				occupancy.add(temp);
				bufferArray.put(h,occupancy);
				
			}
						
		}
				
	}
	
	
	@Override
	public void done()
	{
		
		for (Map.Entry<DTNHost, List<Double>> entry : bufferArray.entrySet()) {
			
			DTNHost a = entry.getKey();
			Integer b = a.getAddress();
			
			write("" + b + ' ' + entry.getValue());
			
			
		}
		super.done();
	}
	
		
	
	
}
