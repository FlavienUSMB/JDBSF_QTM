package timer;

import java.util.Iterator;

public interface Timer extends Iterator<Integer>{
	/**
	 * return the delay time
	 * @see java.util.Iterator#next()
	 */
	@Override
	public Integer next();
	/**
	 * return the delay time
	 * @see java.util.Iterator#next()
	 */
}
