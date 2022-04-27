package action;

import java.lang.reflect.Method;
import java.util.TreeSet;
import java.util.Vector;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import discreteBehaviorSimulator.LogFormatter;
import timer.Timer;

/**
 * @author Tiphaine Bulou (2016)
 * @author Flavien Vernier
 *
 */

// TODO must inherit from Action
public class DiscreteAction implements DiscreteActionInterface {
	private Object object;
	private Method method;


	private Timer timmer;				// timer provides new lapsTime
	private Integer lapsTime; 			// waiting time (null if never used)

	private Logger logger;

	// Constructor

	private DiscreteAction() {
		// Start logger
		this.logger = Logger.getLogger("DAS");
		this.logger.setLevel(Level.ALL);
		this.logger.setUseParentHandlers(true);
	}

	public DiscreteAction(Object o, String m, Timer timmer){
		this();
		this.object = o;
		try{
			this.method = o.getClass().getDeclaredMethod(m, new Class<?>[0]);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		this.timmer = timmer;
	}

	// ATTRIBUTION

	@Override
	public void spendTime(int t) {
		Integer old = this.lapsTime;
		if(this.lapsTime != null) {
			this.lapsTime -= t;
		}
		this.logger.log(Level.FINE, "[DA] operate spendTime on  " + this.getObject().getClass().getName() + ":" + this.getObject().hashCode() + ": old time " + old + " new time " + this.getCurrentLapsTime());
	}

	// RECUPERATION

	@Override
	public Method getMethod(){
		return method;
	}
	@Override
	public Integer getCurrentLapsTime(){
		return lapsTime;
	}
	@Override
	public Object getObject(){
		return object;
	}



	// COMPARAISON
	@Override
	public int compareTo(DiscreteActionInterface c) {
		if (this.lapsTime == null) { // no lapstime is equivalent to infinity
			return 1;
		}
		if (c.getCurrentLapsTime() == null) {// no lapstime is equivalent to infinity
			return -1;
		}
		if(this.lapsTime > c.getCurrentLapsTime()){
			return 1;
		}
		if(this.lapsTime < c.getCurrentLapsTime()){
			return -1;
		}
		if(this.lapsTime == c.getCurrentLapsTime()){
			return 0;
		}
		return 0;
	}

	@Override
	public String toString(){
		return "Object : " + this.object.getClass().getName() + "\n Method : " + this.method.getName()+"\n Stat. : "+ this.timmer + "\n delay: " + this.lapsTime;

	}

	@Override
	public DiscreteActionInterface next() {
		Integer old = this.lapsTime;
		this.lapsTime = this.timmer.next();
		this.logger.log(Level.FINE, "[DA] operate next on  " + this.getObject().getClass().getName() + ":" + this.getObject().hashCode() + ": old time " + old + " new time " + this.getCurrentLapsTime());
		return this;
	}

	@Override
	public boolean hasNext() {
		Boolean more=false;
		if (this.timmer != null && this.timmer.hasNext()) {
			more = true;
		}
		return more;
	}


}
